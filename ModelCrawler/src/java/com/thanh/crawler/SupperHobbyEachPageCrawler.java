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
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletContext;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
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
public class SupperHobbyEachPageCrawler extends BaseCrawler implements Runnable {

    private String url;
    private Category category;

    public SupperHobbyEachPageCrawler(ServletContext context, String url, Category category) {
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

                if (reader != null) {
                    while ((line = reader.readLine()) != null) {
                        if (line.contains("<div class=\"clearfix item-box")) {
                            isStart = true;
                        }
                        if (isStart) {
                            document += line.trim();
                        }
                        if (isStart && line.contains("<div class=\"_attachment")) {
                            break;
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
                    String xslFile = realPath + AppConstant.SUPPER_HOBBY_EACH_PAGE_STYLE_SHEET_FILE;
                    InputStream is = new ByteArrayInputStream(document.getBytes("UTF-8"));
                    ByteArrayOutputStream os = TrAXUtils.transform(is, xslFile);

                    // parse xml to DOM tree
                    Document dom = parseInputStreamToDOM(new ByteArrayInputStream(os.toByteArray()));

                    // traverse DOM tree to get model
                    XPathFactory factory = XPathFactory.newInstance();
                    XPath xPath = factory.newXPath();

                    String prodName;
                    String prodManufacturer;
                    String scale;
                    String price;
                    String productCode;
                    String upcCode;
                    List<String> listImage = new ArrayList<>();
                    String productUrl = url;

                    // get product name
                    String exp = "//productName";
                    prodName = (String) xPath.evaluate(exp, dom, XPathConstants.STRING);

                    // get product manufacturer
                    exp = "//manufacturer";
                    prodManufacturer = (String) xPath.evaluate(exp, dom, XPathConstants.STRING);

                    // get scale
                    exp = "//scale";
                    scale = (String) xPath.evaluate(exp, dom, XPathConstants.STRING);
                    scale = scale.replaceAll(":", "/");

                    // get price 
                    exp = "//price";
                    price = (String) xPath.evaluate(exp, dom, XPathConstants.STRING);
                    if (price != null && !price.isEmpty()) {
                        String[] tmp = price.split(";");
                        if (tmp[1].matches("\\d+\\.?\\d+")) {
                            double priceNumber = Double.parseDouble(tmp[1]) * AppConstant.EXCHANGE_RATE;
                            int priceInt = (int) Math.ceil(priceNumber);
                            price = String.valueOf(priceInt);
                        } else {
                            price = null;
                        }
                    }

                    // get itemcode
                    exp = "//itemCode";
                    productCode = (String) xPath.evaluate(exp, dom, XPathConstants.STRING);

                    // get upc code
                    exp = "//upcCode";
                    upcCode = (String) xPath.evaluate(exp, dom, XPathConstants.STRING);

                    // list image
                    // get list image from link
                    exp = "//listImage/link";
                    NodeList list = (NodeList) xPath.evaluate(exp, dom, XPathConstants.NODESET);
                    for (int i = 0; i < list.getLength(); i++) {
                        String imageUrl = list.item(i).getTextContent();
                        listImage.add(AppConstant.SUPPER_HOBBY + imageUrl);
                    }

                    // get list item for upc code
                    exp = "//item";
                    NodeList listNameValue = (NodeList) xPath.evaluate(exp, dom, XPathConstants.NODESET);
                    for (int i = 0; i < listNameValue.getLength(); i++) {
                        Node item = listNameValue.item(i);
                        if (item.getChildNodes().getLength() > 1) {
                            String name = item.getChildNodes().item(0).getTextContent();
                            String value = item.getChildNodes().item(1).getTextContent();
                            if ("Ean:".equals(name)) {
                                upcCode = value;
                            }
                        }
                    }

                    String itemCodeNumer = productCode.replaceAll("\\D+", "").trim();
                    String tmpProdName = prodName.replaceAll(prodManufacturer, "");
                    tmpProdName = tmpProdName.replaceAll(itemCodeNumer, "").trim();
                    prodName = tmpProdName;

                    ProductDAO prodDao = ProductDAO.getInstance();
                    Product product = new Product();
                    product.setProductName(prodName);
                    product.setIdCategory(category);
                    product.setPrice(price);
                    product.setProductCode(productCode);
                    product.setUpcCode(upcCode);
                    product.setProductManufacturer(prodManufacturer);
                    product.setScale(scale);
                    product.setProductUrl(productUrl);

                    String xsdFilePath = realPath + "WEB-INF/product.xsd";
                    boolean isValidate = JAXBUtils.validateXml(xsdFilePath, product);
                    if (isValidate) {
                        // if product is validate then insert to db
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
                    }

                } // end if reader is not null
            } // end if url is not null
        } catch (Exception ex) {
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException ex) {
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

}
