/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.thanh.dto;

import java.io.Serializable;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author T.Z.B
 */
@XmlRootElement(name = "listProduct")
@XmlAccessorType(XmlAccessType.FIELD)
public class ListProductDetailDTO implements Serializable {

    @XmlElement(name = "listProductDetail")
    private List<ProductDetailDTO> listProductDetail;

    @XmlElement(name = "productName")
    private String productName;

    @XmlElement(name = "productManufacturer")
    private String productManufacturer;

    @XmlElement(name = "scale")
    private String scale;

    @XmlElement(name = "category")
    private String category;

    @XmlElement(name = "upcCode")
    private String upcCode;

    @XmlElement(name = "productImage")
    private String productImage;

    @XmlElement(name = "listImage")
    private List<ProductImageDTO> listImage;

    public ListProductDetailDTO() {
    }

    public List<ProductDetailDTO> getListProductDetail() {
        return listProductDetail;
    }

    public void setListProductDetail(List<ProductDetailDTO> listProductDetail) {
        this.listProductDetail = listProductDetail;
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

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getUpcCode() {
        return upcCode;
    }

    public void setUpcCode(String upcCode) {
        this.upcCode = upcCode;
    }

    public List<ProductImageDTO> getListImage() {
        return listImage;
    }

    public void setListImage(List<ProductImageDTO> listImage) {
        this.listImage = listImage;
    }

    public String getProductImage() {
        return productImage;
    }

    public void setProductImage(String productImage) {
        this.productImage = productImage;
    }

}
