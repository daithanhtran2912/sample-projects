/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.thanh.crawler;

import com.thanh.entity.Category;
import com.thanh.thread.BaseThread;
import com.thanh.utils.AppConstant;
import com.thanh.utils.TrAXUtils;
import com.thanh.utils.XmlSyntaxChecker;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletContext;
import javax.xml.namespace.QName;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.events.Attribute;
import javax.xml.stream.events.Characters;
import javax.xml.stream.events.StartElement;
import javax.xml.stream.events.XMLEvent;
import javax.xml.transform.TransformerException;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

/**
 *
 * @author T.Z.B
 */
public class HobbySearchCrawler extends BaseCrawler implements Runnable {

    private String url;
    private Category category;

    public HobbySearchCrawler(ServletContext context, String url, Category category) {
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
                        if (line.contains("<div class=\"list_kensu02")) {
                            isStart = true;
                        }
                        if (isStart) {
                            document += line.trim();
                        }
                        if (isStart && line.contains("</div")) {
                            break;
                        }
                    } // end while reader.readLine
                    document += "</document>";

                    // well-formed html
                    XmlSyntaxChecker checker = new XmlSyntaxChecker();
                    document = checker.check(document);

                    // get last page
                    int lastPage = getLastPage(document);

                    String tmpUrl;
                    for (int i = 0; i < lastPage; i++) {
                        tmpUrl = url.substring(0, url.length() - 1) + (i + 1);
                        List<String> listProductLink = getListProductLink(tmpUrl);
                        for (String productUrl : listProductLink) {

                            Thread eachProdThread = new Thread(new HobbySearchEachPageCrawler(context, productUrl, category));
                            eachProdThread.start();
                            try {
                                synchronized (BaseThread.getInstance()) {
                                    if (BaseThread.isSuspended()) {
                                        BaseThread.getInstance().wait();
                                    }
                                }
                            } catch (InterruptedException ex) {
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

    private int getLastPage(String document) throws UnsupportedEncodingException, XMLStreamException {

        document = document.trim();
        XMLEventReader eventReader = parseStringToXMLEventReader(document);
        int lastPage = 1;
        while (eventReader.hasNext()) {
            XMLEvent event = (XMLEvent) eventReader.next();
            if (event.isStartElement()) {
                StartElement startElement = event.asStartElement();
                String tagName = startElement.getName().getLocalPart();
                if ("div".equals(tagName)) {
                    Attribute attrClass = startElement.getAttributeByName(new QName("class"));
                    if (attrClass != null) {
                        String attrClassText = attrClass.getValue();
                        if (attrClassText != null && attrClassText.contains("list_kensu02")) {
                            event = (XMLEvent) eventReader.next();
                            Characters textContent = event.asCharacters();
                            String totalItem = textContent.getData();
                            if (totalItem != null) {

                                // replace all non digit characters
                                totalItem = totalItem.replaceAll("\\D+", "");
                                double totalItemFloat = Double.parseDouble(totalItem.trim());
                                lastPage = (int) Math.ceil(totalItemFloat / AppConstant.HOBBY_SEARCH_EACH_PAGE_ITEM);
                            }
                        }
                    }
                }
            }
        }

        eventReader.close();
        return lastPage;
    }

    private List<String> getListProductLink(String url) throws IOException, FileNotFoundException, TransformerException, ParserConfigurationException, SAXException, XPathExpressionException {

        List<String> result = null;
        BufferedReader reader = null;

        try {

            if (url != null) {
                reader = getBufferedReaderForURL(url);
                String line = "";
                String document = "<document>";
                boolean isStart = false;

                if (reader != null) {
                    while ((line = reader.readLine()) != null) {
                        if (line.contains("<div id=\"masterBody_pnlList")) {
                            isStart = true;
                        }
                        if (isStart) {
                            document += line.trim();
                        }
                        if (isStart && line.contains("<div class=\"list_kensu00")) {
                            break;
                        }
                    }
                    document += "</document>";
                    // well-formed html
                    XmlSyntaxChecker checker = new XmlSyntaxChecker();
                    document = checker.check(document);

                    // transform to xml
                    String realPath = this.getContext().getRealPath("/");
                    String xslFile = realPath + AppConstant.HOBBY_SEARCH_LIST_PRODUCT_LINK_STYLE_SHEET_FILE;
                    InputStream is = new ByteArrayInputStream(document.getBytes("UTF-8"));
                    ByteArrayOutputStream os = TrAXUtils.transform(is, xslFile);

                    // parse xml to DOM tree
                    Document domTree = parseInputStreamToDOM(new ByteArrayInputStream(os.toByteArray()));

                    // use xpath to traverse DOM tree to get list product link
                    XPathFactory factory = XPathFactory.newInstance();
                    XPath xPath = factory.newXPath();

                    String exp = "//link";
                    NodeList nodeList = (NodeList) xPath.evaluate(exp, domTree, XPathConstants.NODESET);

                    result = new ArrayList<>();
                    String tmp;
                    for (int i = 0; i < nodeList.getLength(); i++) {
                        Node item = nodeList.item(i);
                        tmp = item.getTextContent();
                        result.add(AppConstant.HOBBY_SEARCH + tmp);
                    }
                } // end is reader is not null
            } // end if url is not null
        } finally {
            if (reader != null) {
                reader.close();
            }
        }
        return result;
    }

    private Document parseInputStreamToDOM(InputStream is) throws ParserConfigurationException, SAXException, IOException {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document domRs = builder.parse(is);

        return domRs;
    }

}
