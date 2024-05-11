/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.thanh.dao;

import com.thanh.entity.Category;
import com.thanh.utils.DBUtils;
import java.util.List;
import javax.persistence.EntityManager;

/**
 *
 * @author T.Z.B
 */
public class CategoryDAO extends BaseDAO<Category, Integer> {

    private CategoryDAO() {

    }

    private static CategoryDAO instance;
    private static final Object LOCK = new Object();

    public static CategoryDAO getInstance() {
        synchronized (LOCK) {
            if (instance == null) {
                instance = new CategoryDAO();
            }
        }
        return instance;
    }

    public synchronized Category getCategoryByName(String categoryName) {
        EntityManager manager = DBUtils.getEntityManager();
        try {
            List<Category> result = manager.createNamedQuery("Category.findByCategory", Category.class)
                    .setParameter("category", categoryName)
                    .getResultList();
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
    
    public List<Category> getAll() {
        EntityManager manager = DBUtils.getEntityManager();
        try {
            List<Category> result = manager.createNamedQuery("Category.findAll", Category.class)
                    .getResultList();
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

}
