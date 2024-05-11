/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.thanh.jaxbadapter;

import com.thanh.dto.CategoryDTO;
import com.thanh.entity.Category;
import javax.xml.bind.annotation.adapters.XmlAdapter;

/**
 *
 * @author T.Z.B
 */
public class CategoryAdapter extends XmlAdapter<CategoryDTO, Category> {

    @Override
    public Category unmarshal(CategoryDTO v) throws Exception {
        Category category = new Category();
        category.setIdCategory(v.getIdCategory());
        category.setCategory(v.getCategory());
        return category;
    }

    @Override
    public CategoryDTO marshal(Category v) throws Exception {
        CategoryDTO dto = new CategoryDTO();
        dto.setIdCategory(v.getIdCategory());
        dto.setCategory(v.getCategory());
        return dto;
    }

}
