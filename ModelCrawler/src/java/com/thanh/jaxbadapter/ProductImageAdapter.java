/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.thanh.jaxbadapter;

import com.thanh.dto.ProductImageDTO;
import com.thanh.entity.ProductImage;
import javax.xml.bind.annotation.adapters.XmlAdapter;

/**
 *
 * @author T.Z.B
 */
public class ProductImageAdapter extends XmlAdapter<ProductImageDTO, ProductImage> {

    @Override
    public ProductImage unmarshal(ProductImageDTO v) throws Exception {
        ProductImage prodImage = new ProductImage();
        prodImage.setIdImage(v.getIdImage());
        prodImage.setImageUrl(v.getImageUrl());
        return prodImage;
    }

    @Override
    public ProductImageDTO marshal(ProductImage v) throws Exception {
        ProductImageDTO dto = new ProductImageDTO();
        dto.setIdImage(v.getIdImage());
        dto.setImageUrl(v.getImageUrl());
        return dto;
    }

}
