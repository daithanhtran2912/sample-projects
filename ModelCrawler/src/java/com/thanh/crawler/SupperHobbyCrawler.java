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
import java.util.Collections;
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
public class SupperHobbyCrawler extends BaseCrawler implements Runnable {

    private String url;
    private Category category;

    public SupperHobbyCrawler(ServletContext context, String url, Category category) {
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
                        if (line.contains("<ul class=\"pagination")) {
                            isStart = true;
                        }
                        if (isStart) {
                            document += line.trim();
                        }
                        if (isStart && line.contains("<script")) {
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
                        tmpUrl = url + "?start=" + i;
                        List<String> listProductLink = getListProductLink(tmpUrl);
                        for (String productUrl : listProductLink) {

                            Thread eachProdThread = new Thread(new SupperHobbyEachPageCrawler(context, productUrl, category));
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
        List<Integer> listPage = new ArrayList<>();
        while (eventReader.hasNext()) {
            XMLEvent event = (XMLEvent) eventReader.next();
            if (event.isStartElement()) {
                StartElement startElement = event.asStartElement();
                String tagName = startElement.getName().getLocalPart();
                if ("a".equals(tagName)) {
                    Attribute attrHref = startElement.getAttributeByName(new QName("href"));
                    if (attrHref != null) {
                        event = (XMLEvent) eventReader.next();
                        if (event.isCharacters()) {
                            Characters textContent = event.asCharacters();
                            if (textContent != null) {
                                String tmpPage = textContent.getData();
                                if (!tmpPage.isEmpty() || tmpPage.length() > 0) {
                                    if (tmpPage.matches("[0-9]+")) {
                                        int page = Integer.parseInt(tmpPage);
                                        listPage.add(page);
                                    }
                                } // end if tmpPage is not empty
                            }
                        }
                    } // end if href
                } // end if a element
            } // end if start element
        } // end while

        if (listPage.size() > 0) {
            Collections.sort(listPage);
            lastPage = listPage.get(listPage.size() - 1);
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
                        if (line.contains("<div class=\"clearfix products")) {
                            isStart = true;
                        }
                        if (isStart) {
                            document += line.trim();
                        }
                        if (isStart && line.contains("hidden-print")) {
                            break;
                        }
                    }
                    document += "</document>";
                    // well-formed html
                    XmlSyntaxChecker checker = new XmlSyntaxChecker();
                    document = checker.check(document);

                    // transform to xml
                    String realPath = this.getContext().getRealPath("/");
                    String xslFile = realPath + AppConstant.SUPPER_SEARCH_LIST_PRODUCT_LINK_STYLE_SHEET_FILE;
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
                        result.add(AppConstant.SUPPER_HOBBY + tmp);
                    }
                } // end if reader is not null
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
