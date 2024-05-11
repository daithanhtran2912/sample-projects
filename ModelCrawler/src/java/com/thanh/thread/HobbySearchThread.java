/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.thanh.thread;

import com.thanh.crawler.HobbySearchCategoriesCrawler;
import com.thanh.crawler.HobbySearchCrawler;
import com.thanh.entity.Category;
import com.thanh.utils.AppConstant;
import java.util.Map;
import javax.servlet.ServletContext;

/**
 *
 * @author T.Z.B
 */
public class HobbySearchThread extends BaseThread implements Runnable {

    private ServletContext context;

    public HobbySearchThread() {
    }

    public HobbySearchThread(ServletContext context) {
        this.context = context;
    }

    @Override
    public void run() {
        try {

            // get list category
            HobbySearchCategoriesCrawler categoriesCrawler = new HobbySearchCategoriesCrawler(context);
            Map<String, String> categories = categoriesCrawler.getCategories(AppConstant.HOBBY_SEARCH + AppConstant.HOBBY_SEARCH_CAR);

            if (categories != null && categories.size() > 0) {
                for (Map.Entry<String, String> entry : categories.entrySet()) {

                    Category category = createCategory(entry.getValue());
                    if (category == null) {
                        category.setCategory(entry.getValue());
                    }
                    Thread thread = new Thread(new HobbySearchCrawler(context, entry.getKey(), category));
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
