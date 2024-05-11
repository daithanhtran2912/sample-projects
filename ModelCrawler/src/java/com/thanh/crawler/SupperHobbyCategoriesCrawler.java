/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.thanh.crawler;

import com.thanh.utils.AppConstant;
import com.thanh.utils.XmlSyntaxChecker;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Map;
import java.util.TreeMap;
import javax.servlet.ServletContext;
import javax.xml.namespace.QName;
import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.events.Attribute;
import javax.xml.stream.events.Characters;
import javax.xml.stream.events.StartElement;
import javax.xml.stream.events.XMLEvent;

/**
 *
 * @author T.Z.B
 */
public class SupperHobbyCategoriesCrawler extends BaseCrawler {

    public SupperHobbyCategoriesCrawler(ServletContext context) {
        super(context);
    }

    public Map<String, String> getCategories(String url) {
        BufferedReader reader = null;

        try {
            if (url != null) {
                reader = getBufferedReaderForURL(url);
                String line = "";
                String document = "<document>";
                boolean isStart = false;
                boolean isFound = false;

                if (reader != null) {
                    // get html fragment
                    while ((line = reader.readLine()) != null) {
                        if (isStart && line.contains("<div id=\"collapse_filters")) {
                            break;
                        }
                        if (isStart) {
                            document += line.trim();
                        }
                        if (isFound && line.contains("<div class=\"row")) {
                            isStart = true;
                        }
                        if (line.contains("<div id=\"collapse_category")) {
                            isFound = true;
                        }
                    }

                    document += "</document>";

                    // check well-formed html
                    XmlSyntaxChecker checker = new XmlSyntaxChecker();
                    document = checker.check(document);

                    // get map categories
                    return stAXParserForCategories(document);
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
        return null;
    }

    public Map<String, String> stAXParserForCategories(String document) throws UnsupportedEncodingException, XMLStreamException {
        document = document.trim();
        XMLEventReader eReader = parseStringToXMLEventReader(document);
        Map<String, String> categories = new TreeMap<>();

        while (eReader.hasNext()) {
            XMLEvent event = (XMLEvent) eReader.next();
            if (event.isStartElement()) {
                StartElement startElement = event.asStartElement();
                String tagName = startElement.getName().getLocalPart();

                // Get category with link
                if ("div".equals(tagName)) {
                    Attribute attrClass = startElement.getAttributeByName(new QName("class"));
                    if (attrClass != null && attrClass.getValue().contains("cat-tree-head-accordion")) {
                        event = (XMLEvent) eReader.next();
                        startElement = event.asStartElement();
                        tagName = startElement.getName().getLocalPart();
                        if ("a".equals(tagName)) {
                            Attribute attrHref = startElement.getAttributeByName(new QName("href"));
                            String link = attrHref.getValue();
                            if (link != null) {
                                event = (XMLEvent) eReader.next();
                                startElement = event.asStartElement();
                                tagName = startElement.getName().getLocalPart();
                                if ("span".equals(tagName)) {
                                    attrClass = startElement.getAttributeByName(new QName("class"));
                                    if (attrClass != null && attrClass.getValue().contains("cat-tree-name")) {
                                        event = (XMLEvent) eReader.next();
                                        Characters textContent = event.asCharacters();
                                        if (textContent != null) {
                                            String category = textContent.getData();
                                            if (category.contains("Car")) {
                                                categories.put(AppConstant.SUPPER_HOBBY + link, "Car");
                                            } else if (category.contains("Motor")) {
                                                categories.put(AppConstant.SUPPER_HOBBY + link, "Motorcycle");
                                            }
                                        }
                                    }
                                } // end if span elemnt
                            } // end if link is not null
                        } // end if a element
                    } // end if attribute is not null

                }
            }
        } // end while event reader has next
        eReader.close();
        return categories;
    }

}
