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
public class HobbySearchCategoriesCrawler extends BaseCrawler {

    public HobbySearchCategoriesCrawler(ServletContext context) {
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
                        if (isStart && line.contains("<div class=\"submenu_bar ")) {
                            break;
                        }
                        if (isStart) {
                            document += line.trim();
                        }
                        if (isFound && line.contains("<div style=\"margin-top:32px;")) {
                            isStart = true;
                        }
                        if (line.contains("<div id=\"divLeft\"")) {
                            isFound = true;
                        }
                    }

                    document += "</document>";

                    // check well-formed html
                    XmlSyntaxChecker checker = new XmlSyntaxChecker();
                    document = checker.check(document);

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
        String mainCategory = "";

        while (eReader.hasNext()) {
            XMLEvent event = (XMLEvent) eReader.next();
            if (event.isStartElement()) {
                StartElement startElement = event.asStartElement();
                String tagName = startElement.getName().getLocalPart();

                // Get main-category
                if ("span".equals(tagName)) {
                    event = (XMLEvent) eReader.next();
                    Characters textContent = event.asCharacters();
                    if (textContent != null) {
                        mainCategory = textContent.getData();
                    }
                }

                // Get sub-category with link
                if ("a".equals(tagName)) {
                    Attribute href = startElement.getAttributeByName(new QName("href"));
                    String link = AppConstant.HOBBY_SEARCH + href.getValue();
                    event = (XMLEvent) eReader.next();
                    startElement = event.asStartElement();
                    tagName = startElement.getName().getLocalPart();
                    if ("div".equals(tagName)) {
                        event = (XMLEvent) eReader.next();
                        Characters textContent = event.asCharacters();
                        String subCategory = textContent.getData();
                        if (!subCategory.contains("are here") && !subCategory.contains("List")) {

                            if (mainCategory.contains("Resin")
                                    || mainCategory.contains("Accessory")
                                    || mainCategory.contains("Decal")) {

                                // put map accessory
                                categories.put(link, "Accessory");

                            } else if (mainCategory.contains("Motor")
                                    && !mainCategory.contains("Resin")
                                    && !mainCategory.contains("Accessory")) {

                                // put map Motorcycle
                                categories.put(link, "Motorcycle");

                            } else if ((mainCategory.contains("Car")
                                    || mainCategory.contains("Rally")
                                    || mainCategory.contains("Truck")
                                    || mainCategory.contains("Itasha"))
                                    && !mainCategory.contains("Resin")
                                    && !mainCategory.contains("Accessory")) {

                                // put map Car
                                categories.put(link, "Car");

                            }
                        }
                    }
                }
            }
        } // end while event reader has next
        eReader.close();
        return categories;
    }

}
