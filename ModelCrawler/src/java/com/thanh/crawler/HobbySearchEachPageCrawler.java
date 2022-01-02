/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.thanh.crawler;

import com.thanh.dao.ProductDAO;
import com.thanh.dao.ProductImageDAO;
import com.thanh.entity.Category;
import com.thanh.entity.Product;
import com.thanh.entity.ProductImage;
import com.thanh.thread.BaseThread;
import com.thanh.utils.AppConstant;
import com.thanh.utils.JAXBUtils;
import com.thanh.utils.TrAXUtils;
import com.thanh.utils.XmlSyntaxChecker;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletContext;
import javax.xml.namespace.QName;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.events.Attribute;
import javax.xml.stream.events.StartElement;
import javax.xml.stream.events.XMLEvent;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

/**
 *
 * @author T.Z.B
 */
public class HobbySearchEachPageCrawler extends BaseCrawler implements Runnable {

    private String url;
    private Category category;

    public HobbySearchEachPageCrawler(ServletContext context, String url, Category category) {
        super(context);
        this.url = url;
        this.category = category;
    }

    @Override
    public void run() {
        BufferedReader reader = null;
        try {
            if (url != null) {
                reader = getBufferedReaderForURL(url);
                String line = "";
                String document = "<document>";
                boolean isStart = false;
                boolean isFound = false;

                if (reader != null) {
                    while ((line = reader.readLine()) != null) {
                        if (isStart && line.contains("<div id=\"masterBody_pnlItemExp")) {
                            break;
                        }
                        if (isStart) {
                            document += line.trim();
                        }
                        if (isFound && line.contains("<div class=\"right")) {
                            isStart = true;
                        }
                        if (line.contains("<div id=\"masterContent")) {
                            isFound = true;
                        }
                    }

                    document += "</document>";

                    try {
                        synchronized (BaseThread.getInstance()) {
                            while (BaseThread.isSuspended()) {
                                BaseThread.getInstance().wait();
                            }
                        }
                    } catch (InterruptedException ex) {
                    }

                    // well-formed html
                    XmlSyntaxChecker checker = new XmlSyntaxChecker();
                    document = checker.check(document);

                    // transform to xml
                    String realPath = this.getContext().getRealPath("/");
                    String xslFile = realPath + AppConstant.HOBBY_SEARCH_EACH_PAGE_STYLE_SHEET_FILE;
                    InputStream is = new ByteArrayInputStream(document.getBytes("UTF-8"));
                    ByteArrayOutputStream os = TrAXUtils.transform(is, xslFile);

                    // parse xml to DOM tree
                    Document dom = parseInputStreamToDOM(new ByteArrayInputStream(os.toByteArray()));

                    // traverse DOM tree to get model
                    XPathFactory factory = XPathFactory.newInstance();
                    XPath xPath = factory.newXPath();

                    String prodName = "";
                    String prodManufacturer = "";
                    String scale = "";
                    String price = "";
                    String productCode = "";
                    String upc = "";
                    List<String> listImage = null;
                    String productUrl = url;

                    // get product name
                    String exp = "/model/productName";
                    prodName = (String) xPath.evaluate(exp, dom, XPathConstants.STRING);

                    // get list node details
                    exp = "/model/details/detail";
                    NodeList listDetail = (NodeList) xPath.evaluate(exp, dom, XPathConstants.NODESET);
                    for (int i = 0; i < listDetail.getLength(); i++) {
                        Node item = listDetail.item(i);
                        String name = item.getChildNodes().item(0).getTextContent();
                        String value = item.getChildNodes().item(1).getTextContent();
                        switch (name) {
                            case "Manufacturer":
                                prodManufacturer = value;
                                break;
                            case "Scale":
                                scale = value;
                                break;
                            case "Sales Price":
                                String tmp = value.replaceAll("\\,", "");
                                price = tmp.replaceAll("yen", "");
                                break;
                            case "Item code":
                                productCode = value;
                                break;
                            case "JAN code":
                                upc = value;
                                break;
                        }
                    } // end for listDetail.getLength

                    // get list image from link
                    exp = "/model/imgLink";
                    String linkImage = AppConstant.HOBBY_SEARCH + xPath.evaluate(exp, dom);
                    listImage = getListImageFromLink(linkImage);

                    ProductDAO prodDao = ProductDAO.getInstance();
                    Product product = new Product();
                    product.setProductName(prodName);
                    product.setIdCategory(category);
                    product.setPrice(price);
                    product.setProductCode(productCode);
                    product.setUpcCode(upc);
                    product.setProductManufacturer(prodManufacturer);
                    product.setScale(scale);
                    product.setProductUrl(productUrl);

                    String xsdFilePath = realPath + "WEB-INF/product.xsd";
                    boolean isValidate = JAXBUtils.validateXml(xsdFilePath, product);

                    if (isValidate) {
                        boolean isNewProd = prodDao.saveProduct(product);

                        synchronized (product) {
                            if (isNewProd) {
                                ProductImageDAO proImgDao = ProductImageDAO.getInstance();
                                for (String imgUrl : listImage) {
                                    ProductImage productImage = new ProductImage();
                                    productImage.setIdProduct(product);
                                    productImage.setImageUrl(imgUrl);
                                    proImgDao.create(productImage);
                                }
                            }
                        }
                    } // end if product is validate
                } // end if reader is not null
            } // end if url is not null
        } catch (Exception ex) {
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException ex) {
                    Logger.getLogger(HobbySearchEachPageCrawler.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }

    private Document parseInputStreamToDOM(InputStream is) throws ParserConfigurationException, SAXException, IOException {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document domRs = builder.parse(is);

        return domRs;
    }

    private List<String> getListImageFromLink(String url) throws IOException, UnsupportedEncodingException, XMLStreamException {

        BufferedReader reader = null;
        try {
            if (url != null) {
                reader = getBufferedReaderForURL(url);
                String line = "";
                String document = "<document>";
                boolean isStart = false;
                if (reader != null) {
                    while ((line = reader.readLine()) != null) {
                        if (line.contains("<span class=\"imgbox2")) {
                            isStart = true;
                        }
                        if (isStart) {
                            document += line.trim();
                        }
                        if (isStart && line.contains("</span")) {
                            break;
                        }
                    } // end while reader.readLine
                    document += "</document>";

                    // well-formed html
                    XmlSyntaxChecker checker = new XmlSyntaxChecker();
                    document = checker.check(document);

                    // use StAX for getting list link image
                    document = document.trim();
                    XMLEventReader eventReader = parseStringToXMLEventReader(document);
                    List<String> result = new ArrayList<>();

                    while (eventReader.hasNext()) {
                        String tagName = "";
                        XMLEvent event = (XMLEvent) eventReader.next();
                        if (event.isStartElement()) {
                            StartElement startElement = event.asStartElement();
                            tagName = startElement.getName().getLocalPart();
                            if ("img".equals(tagName)) {
                                Attribute attrSrc = startElement.getAttributeByName(new QName("src"));
                                String src = attrSrc.getValue();
                                if (src != null) {
                                    String[] tmp = src.split("_s");
                                    src = tmp[0] + tmp[1];
                                    result.add(AppConstant.HOBBY_SEARCH + src);
                                }
                            }
                        } // end if startelement
                    } // end while event reader
                    eventReader.close();
                    return result;
                } // end if reader is not null
            } // end if url is not null
        } finally {
            if (reader != null) {
                reader.close();
            }
        }
        return null;
    }

}
