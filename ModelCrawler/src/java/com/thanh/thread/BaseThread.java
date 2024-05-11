/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.thanh.thread;

import com.thanh.dao.CategoryDAO;
import com.thanh.entity.Category;

/**
 *
 * @author T.Z.B
 */
public class BaseThread extends Thread {

    private static BaseThread instance;
    private final static Object LOCK = new Object();
    private static boolean suspended = false;

    protected BaseThread() {

    }

    public static BaseThread getInstance() {
        synchronized (LOCK) {
            if (instance == null) {
                instance = new BaseThread();
            }
        }
        return instance;
    }

    public static boolean isSuspended() {
        return suspended;
    }

    public static void setSuspended(boolean suspended) {
        BaseThread.suspended = suspended;
    }

    public void suspendThread() {
        setSuspended(true);
    }

    public synchronized void resumeThread() {
        setSuspended(false);
        notifyAll();
    }

    protected Category createCategory(String categoryName) {
        synchronized (LOCK) {
            Category category;
            CategoryDAO dao = CategoryDAO.getInstance();
            category = dao.getCategoryByName(categoryName);
            // create new category if category does not exist
            if (category == null) {
                category = new Category();
                category.setCategory(categoryName);
                dao.create(category);
            }
            return category;
        }
    }

}
