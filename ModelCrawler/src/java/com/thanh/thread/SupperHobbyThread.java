/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.thanh.thread;

import com.thanh.crawler.SupperHobbyCategoriesCrawler;
import com.thanh.crawler.SupperHobbyCrawler;
import com.thanh.entity.Category;
import com.thanh.utils.AppConstant;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletContext;

/**
 *
 * @author T.Z.B
 */
public class SupperHobbyThread extends BaseThread implements Runnable {

    private ServletContext context;

    public SupperHobbyThread() {
    }

    public SupperHobbyThread(ServletContext context) {
        this.context = context;
    }

    @Override
    public void run() {
        try {

            // get list category from catalog
            SupperHobbyCategoriesCrawler categoriesCrawler = new SupperHobbyCategoriesCrawler(context);
            Map<String, String> categories = categoriesCrawler.getCategories(AppConstant.SUPPER_HOBBY + AppConstant.SUPPER_HOBBY_CATALOG);

            if (categories != null && categories.size() > 0) {
                for (Map.Entry<String, String> entry : categories.entrySet()) {
                    Category category = createCategory(entry.getValue());
                    if (category == null) {
                        category.setCategory(entry.getValue());
                    }
                    Thread thread = new Thread(new SupperHobbyCrawler(context, entry.getKey(), category));
                    thread.start();

                    synchronized (BaseThread.getInstance()) {
                        while (BaseThread.isSuspended()) {
                            BaseThread.getInstance().wait();
                        }
                    }
                }
            }
        } catch (Exception ex) {
        }
    }

}
