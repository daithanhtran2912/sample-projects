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
@XmlRootElement(name = "images")
@XmlAccessorType(XmlAccessType.FIELD)
public class ProductImageDTO implements Serializable {

    @XmlAttribute(name = "idImage")
    private Integer idImage;

    @XmlElement(name = "imageUrl")
    private String imageUrl;

    public ProductImageDTO(Integer idImage, String imageUrl) {
        this.idImage = idImage;
        this.imageUrl = imageUrl;
    }

    public ProductImageDTO() {
    }

    public Integer getIdImage() {
        return idImage;
    }

    public void setIdImage(Integer idImage) {
        this.idImage = idImage;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

}
