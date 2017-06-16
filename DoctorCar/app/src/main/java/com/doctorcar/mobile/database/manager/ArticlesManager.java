package com.doctorcar.mobile.database.manager;

import com.doctorcar.mobile.database.Articles;
import com.doctorcar.mobile.database.User;
import com.doctorcar.mobile.database.dao.ArticlesDao;
import com.doctorcar.mobile.database.dao.UserDao;

import java.util.List;

/**
 * Created by dd on 2017/5/5.
 */

public class ArticlesManager extends BaseDbManager{

    private static ArticlesManager articlesManager;

    public static ArticlesManager getArticlesManager(){
        if(articlesManager == null){
            articlesManager = new ArticlesManager();
            return articlesManager;
        }
        return articlesManager;
    }

    public ArticlesDao getArticlesDao(){
        return GreenDaoManager.getInstance().getNewSession().getArticlesDao();
    }

    public void addArticle(String title,String content,String privacy){
        Articles articles = new Articles(null,title,content,privacy);
        try {
            long l = getArticlesDao().insert(articles);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<Articles> getAllArticlesList(){
        List<Articles> articlesList = getArticlesDao().loadAll();
        return articlesList;
    }


}
