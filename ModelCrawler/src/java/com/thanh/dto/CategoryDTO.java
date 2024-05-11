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
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement
public class CategoryDTO implements Serializable {

    @XmlAttribute(name = "id", required = true)
    private Integer idCategory;

    @XmlElement(required = true)
    private String category;

    public CategoryDTO() {
    }

    public CategoryDTO(Integer idCategory, String category) {
        this.idCategory = idCategory;
        this.category = category;
    }

    public Integer getIdCategory() {
        return idCategory;
    }

    public void setIdCategory(Integer idCategory) {
        this.idCategory = idCategory;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

}
