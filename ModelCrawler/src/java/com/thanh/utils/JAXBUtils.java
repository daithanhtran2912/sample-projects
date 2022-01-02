/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.thanh.utils;

import com.thanh.dto.ProductListDTO;
import com.thanh.entity.Product;
import java.io.File;
import java.io.IOException;
import java.io.StringWriter;
import javax.xml.XMLConstants;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.bind.util.JAXBSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;
import org.xml.sax.SAXException;

/**
 *
 * @author T.Z.B
 */
public class JAXBUtils {

    public static boolean validateXml(String filePath, Product product) {
        try {
            synchronized (product) {
                JAXBContext context = JAXBContext.newInstance(Product.class);
                Unmarshaller unmarshaller = context.createUnmarshaller();
                Marshaller marshaller = context.createMarshaller();
                SchemaFactory factory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
                Schema schema = factory.newSchema(new File(filePath));
                XmlValidatorHandler handler = new XmlValidatorHandler();

                unmarshaller.setSchema(schema);
                unmarshaller.setEventHandler(handler);

                StringWriter writer = new StringWriter();
                marshaller.marshal(product, writer);
//                String xml = writer.toString();

                marshaller.setEventHandler(handler);
                Validator validator = schema.newValidator();
                validator.validate(new JAXBSource(marshaller, product));
                return true;
            }
        } catch (JAXBException | SAXException | IOException ex) {
            return false;
        }
    }

    public static String marshallProductList(ProductListDTO listProduct) throws JAXBException {
        JAXBContext context = JAXBContext.newInstance(ProductListDTO.class);
        XmlValidatorHandler handler = new XmlValidatorHandler();
        Marshaller marshaller = context.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_ENCODING, "UTF-8");
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.FALSE);
        marshaller.setEventHandler(handler);
        StringWriter strWriter = new StringWriter();
        marshaller.marshal(listProduct, strWriter);
        return strWriter.toString();
    }

}
