/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.thanh.dao;

import com.thanh.dto.ListProductDetailDTO;
import com.thanh.dto.ProductDTO;
import com.thanh.dto.ProductDetailDTO;
import com.thanh.dto.ProductImageDTO;
import com.thanh.dto.ProductListDTO;
import com.thanh.entity.Product;
import com.thanh.entity.ProductImage;
import com.thanh.utils.DBUtils;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

/**
 *
 * @author T.Z.B
 */
public class ProductDAO extends BaseDAO<Product, Integer> {

    private ProductDAO() {
    }

    private static ProductDAO instance;
    private static final Object LOCK = new Object();

    public static ProductDAO getInstance() {
        synchronized (LOCK) {
            if (instance == null) {
                instance = new ProductDAO();
            }
        }
        return instance;
    }

    public Product getProductByNameAndUrl(String productName, String productUrl) {
        EntityManager manager = DBUtils.getEntityManager();
        try {
            EntityTransaction transaction = manager.getTransaction();
            transaction.begin();
            List<Product> result = manager.createNamedQuery("Product.findByProductNameAndProductUrl", Product.class)
                    .setParameter("productName", productName)
                    .setParameter("productUrl", productUrl)
                    .getResultList();
            transaction.commit();
            if (result != null && !result.isEmpty()) {
                return result.get(0);
            }
        } finally {
            if (manager != null) {
                manager.close();
            }
        }
        return null;
    }

    public Collection<ProductImage> getListProductImage(Integer prodId) {
        EntityManager manager = DBUtils.getEntityManager();
        try {
            EntityTransaction transaction = manager.getTransaction();
            transaction.begin();
            Collection<ProductImage> result = manager.createNamedQuery("Product.findListProductImage", ProductImage.class)
                    .getResultList();
            transaction.commit();
            if (result != null && !result.isEmpty()) {
                return result;
            }
        } finally {
            if (manager != null) {
                manager.close();
            }
        }
        return null;
    }

    public synchronized boolean saveProduct(Product product) {
        Product findEntity = getProductByNameAndUrl(product.getProductName(), product.getProductUrl());
        Product result;
        if (findEntity == null) {
            result = create(product);
            return true;
        } else {
            findEntity.setProductImageCollection(product.getProductImageCollection());
            findEntity.setIdCategory(product.getIdCategory());
            findEntity.setProductCode(product.getProductCode());
            findEntity.setUpcCode(product.getUpcCode());
            findEntity.setPrice(product.getPrice());
            findEntity.setProductManufacturer(product.getProductManufacturer());
            findEntity.setProductName(product.getProductName());
            findEntity.setProductUrl(product.getProductUrl());
            findEntity.setScale(product.getScale());
            result = update(findEntity);
            return false;
        }
    }

    public List<ProductDTO> getListTopProduct(String category) {
        EntityManager manager = DBUtils.getEntityManager();
        try {
            EntityTransaction transaction = manager.getTransaction();
            transaction.begin();
            String query = "SELECT TOP 4 p.id_product, p.product_name, p.scale, p.product_manufacturer, p.price, p.upc_code, (SELECT TOP 1 PI.image_url FROM ProductImage PI WHERE PI.id_product=P.id_product) AS productImage "
                    + "FROM Product p INNER JOIN Category c ON p.id_category=c.id_category "
                    + "WHERE c.category = ? "
                    + "ORDER BY P.price";

            List<Object[]> result = manager.createNativeQuery(query)
                    .setParameter(1, category)
                    .getResultList();

            List<ProductDTO> listProdDto = new ArrayList<>();
            for (Object[] rs : result) {
                ProductDTO dto = new ProductDTO();
                dto.setIdProduct((Integer) rs[0]);              // prod Id
                dto.setProductName((String) rs[1]);             // prod name
                dto.setScale((String) rs[2]);                   // scale
                dto.setProductManufacturer((String) rs[3]);     // prod manufacturer
                dto.setPrice(((Integer) rs[4]) + "");           // price
                dto.setUpcCode((String) rs[5]);                 // upc code
                dto.setProductImage((String) rs[6]);            // image url
                listProdDto.add(dto);
            }
            transaction.commit();
            return listProdDto;
        } catch (Exception e) {
        } finally {
            if (manager != null) {
                manager.close();
            }
        }
        return null;
    }

    public ListProductDetailDTO getProductDetail(String upcCode) {
        EntityManager manager = DBUtils.getEntityManager();
        try {
            EntityTransaction transaction = manager.getTransaction();
            transaction.begin();
            String query = "SELECT P.id_product, P.product_name, P.product_manufacturer, P.scale, P.price, P.product_code, P.product_url, C.category "
                    + "FROM Product P INNER JOIN Category C ON C.id_category=P.id_category "
                    + "WHERE P.upc_code = ? "
                    + "ORDER BY P.product_code";

            List<Object[]> result = manager.createNativeQuery(query)
                    .setParameter(1, upcCode)
                    .getResultList();

            List<ProductDetailDTO> listProdDto = new ArrayList<>();

            ListProductDetailDTO productDetail = new ListProductDetailDTO();

            String productManufacturer = "";
            String scale = "";
            String category = "";

            for (Object[] rs : result) {
                ProductDetailDTO dto = new ProductDetailDTO();
                dto.setIdProduct((Integer) rs[0]);                      // prod Id
                dto.setProductName(String.valueOf(rs[1]));              // prod name
                productManufacturer = String.valueOf(rs[2]);            // prod manufacturer
                scale = String.valueOf(rs[3]);                          // scale
                dto.setPrice(String.valueOf(rs[4]));                    // price
                dto.setProductCode(String.valueOf(rs[5]));              // product code
                dto.setProductUrl(String.valueOf(rs[6]));               // product url
                category = String.valueOf(rs[7]);                       // category
                listProdDto.add(dto);
            }

            if (listProdDto != null) {
                List<ProductImageDTO> tmpImage = new ArrayList();
                for (ProductDetailDTO dto : listProdDto) {
                    List<ProductImageDTO> listImage = getImageForProduct(dto.getIdProduct());
                    for (ProductImageDTO img : listImage) {
                        tmpImage.add(img);
                    }
                }
                productDetail.setListImage(tmpImage);
                if (tmpImage != null && tmpImage.size() > 0) {
                    productDetail.setProductImage(tmpImage.get(0).getImageUrl());
                }
            }

            productDetail.setListProductDetail(listProdDto);
            productDetail.setProductName(listProdDto.get(0).getProductName());
            productDetail.setProductManufacturer(productManufacturer);
            productDetail.setScale(scale);
            productDetail.setCategory(category);
            productDetail.setUpcCode(upcCode);
            transaction.commit();

            return productDetail;
        } finally {
            if (manager != null) {
                manager.close();
            }
        }
    }

    private List<ProductImageDTO> getImageForProduct(Integer idProduct) {
        EntityManager manager = DBUtils.getEntityManager();
        try {
            EntityTransaction transaction = manager.getTransaction();
            transaction.begin();
            String query = "SELECT PI.id_product, PI.image_url "
                    + "FROM ProductImage PI "
                    + "WHERE PI.id_product = ? ";

            List<Object[]> result = manager.createNativeQuery(query)
                    .setParameter(1, idProduct)
                    .getResultList();

            List<ProductImageDTO> listImage = new ArrayList<>();
            if (result != null) {
                for (Object[] rs : result) {
                    ProductImageDTO dto = new ProductImageDTO();
                    dto.setIdImage((Integer) rs[0]);
                    dto.setImageUrl((String) rs[1]);
                    listImage.add(dto);
                }
            }
            transaction.commit();
            return listImage;
        } finally {
            if (manager != null) {
                manager.close();
            }
        }
    }

    public ProductListDTO getSessionProduct() {
        EntityManager manager = DBUtils.getEntityManager();
        try {
            EntityTransaction transaction = manager.getTransaction();
            transaction.begin();
            String query = "SELECT TOP 150 p.id_product, p.product_name, p.scale, p.product_manufacturer, p.price, p.upc_code, (SELECT TOP 1 PI.image_url FROM ProductImage PI WHERE PI.id_product=P.id_product ) AS productImage "
                    + "FROM Product p INNER JOIN Category c ON p.id_category=c.id_category "
                    + "WHERE product_manufacturer LIKE '%Aoshima%' "
                    + "OR product_manufacturer LIKE '%Tamiya%' "
                    + "OR product_manufacturer LIKE '%Hasegawa%' "
                    + "OR product_manufacturer LIKE '%Fujimi%' "
                    + "AND (scale LIKE '%24%' OR scale LIKE '%12%') "
                    + "ORDER BY RAND(), P.product_name DESC";

            List<Object[]> result = manager.createNativeQuery(query)
                    .getResultList();

            ProductListDTO productListDTO = new ProductListDTO();

            List<ProductDTO> list = new ArrayList<>();
            if (result != null) {
                for (Object[] rs : result) {
                    ProductDTO dto = new ProductDTO();
                    dto.setIdProduct((Integer) rs[0]);                          // prod id
                    dto.setProductName(String.valueOf(rs[1]));                  // prod Name
                    dto.setScale(String.valueOf(rs[2]));                        // scale
                    dto.setProductManufacturer(String.valueOf(rs[3]));          // manufacturer
                    dto.setPrice(String.valueOf(rs[4]));                        // price
                    dto.setUpcCode(String.valueOf(rs[5]));                      // upc code
                    dto.setProductImage(String.valueOf(rs[6]));                 // img url
                    list.add(dto);
                }
            }
            productListDTO.setListProduct(list);
            transaction.commit();
            return productListDTO;
        } finally {
            if (manager != null) {
                manager.close();
            }
        }
    }

    public ProductListDTO getProductLikeByNameOrManufacturerOrScale(String searchValue) {
        EntityManager manager = DBUtils.getEntityManager();
        try {
            EntityTransaction transaction = manager.getTransaction();
            transaction.begin();
            String query = "SELECT TOP 150 p.id_product, p.product_name, p.scale, p.product_manufacturer, p.price, p.upc_code, (SELECT TOP 1 PI.image_url FROM ProductImage PI WHERE PI.id_product=P.id_product ) AS productImage "
                    + "FROM Product p INNER JOIN Category c ON p.id_category=c.id_category "
                    + "WHERE product_manufacturer LIKE ? "
                    + "OR product_name LIKE ? "
                    + "OR scale LIKE ? "
                    + "ORDER BY RAND(), P.product_name DESC";

            List<Object[]> result = manager.createNativeQuery(query)
                    .setParameter(1, searchValue)
                    .setParameter(2, searchValue)
                    .setParameter(3, searchValue)
                    .getResultList();

            ProductListDTO productListDTO = new ProductListDTO();

            List<ProductDTO> list = new ArrayList<>();
            if (result != null) {
                for (Object[] rs : result) {
                    ProductDTO dto = new ProductDTO();
                    dto.setIdProduct((Integer) rs[0]);                          // prod id
                    dto.setProductName(String.valueOf(rs[1]));                  // prod Name
                    dto.setScale(String.valueOf(rs[2]));                        // scale
                    dto.setProductManufacturer(String.valueOf(rs[3]));          // manufacturer
                    dto.setPrice(String.valueOf(rs[4]));                        // price
                    dto.setUpcCode(String.valueOf(rs[5]));                      // upc code
                    dto.setProductImage(String.valueOf(rs[6]));                 // img url
                    list.add(dto);
                }
            }
            productListDTO.setListProduct(list);
            transaction.commit();
            return productListDTO;
        } finally {
            if (manager != null) {
                manager.close();
            }
        }
    }
}
