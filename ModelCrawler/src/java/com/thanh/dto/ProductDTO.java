/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.thanh.dto;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author T.Z.B
 */
@XmlRootElement(name = "productDetail")
@XmlAccessorType(XmlAccessType.FIELD)
public class ProductDTO implements Serializable {

    @XmlAttribute(name = "idProduct")
    private Integer idProduct;

    @XmlElement(name = "productName")
    private String productName;

    @XmlElement(name = "productManufacturer")
    private String productManufacturer;

    @XmlElement(name = "scale")
    private String scale;

    @XmlElement(name = "price")
    private String price;

    @XmlElement(name = "upcCode")
    private String upcCode;

    @XmlElement(name = "productImage")
    private String productImage;

    public ProductDTO() {
    }

    public ProductDTO(Integer idProduct, String productName, String productManufacturer, String scale, String price, String upcCode) {
        this.idProduct = idProduct;
        this.productName = productName;
        this.productManufacturer = productManufacturer;
        this.scale = scale;
        this.price = price;
        this.upcCode = upcCode;
    }

    public Integer getIdProduct() {
        return idProduct;
    }

    public void setIdProduct(Integer idProduct) {
        this.idProduct = idProduct;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductManufacturer() {
        return productManufacturer;
    }

    public void setProductManufacturer(String productManufacturer) {
        this.productManufacturer = productManufacturer;
    }

    public String getScale() {
        return scale;
    }

    public void setScale(String scale) {
        this.scale = scale;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getUpcCode() {
        return upcCode;
    }

    public void setUpcCode(String upcCode) {
        this.upcCode = upcCode;
    }

    public String getProductImage() {
        return productImage;
    }

    public void setProductImage(String productImage) {
        this.productImage = productImage;
    }

}
