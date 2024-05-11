/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.thanh.dao;

import com.thanh.entity.Product;
import com.thanh.entity.ProductImage;

/**
 *
 * @author T.Z.B
 */
public class ProductImageDAO extends BaseDAO<ProductImage, Integer> {

    private ProductImageDAO() {
    }

    private static ProductImageDAO instance;
    private static final Object LOCK = new Object();

    public static ProductImageDAO getInstance() {
        synchronized (LOCK) {
            if (instance == null) {
                instance = new ProductImageDAO();
            }
        }
        return instance;
    }

    protected ProductImage createProductImage(Product product, String productImageUrl) {
        synchronized (LOCK) {
            ProductImage productImage = new ProductImage();
            productImage.setIdProduct(product);
            productImage.setImageUrl(productImageUrl);
            ProductImageDAO dao = ProductImageDAO.getInstance();
            return dao.create(productImage);
        }
    }

}
