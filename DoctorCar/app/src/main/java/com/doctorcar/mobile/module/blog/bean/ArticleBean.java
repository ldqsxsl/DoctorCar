package com.doctorcar.mobile.module.blog.bean;

import java.io.Serializable;

/**
 * Created by dd on 2017/5/9.
 */

public class ArticleBean implements Serializable{
    private Integer article_id;

    private String user_id;

    private String article_title;

    private String article_content;

    private String article_privacy;

    private String article_time;


    public Integer getArticle_id() {
        return article_id;
    }

    public void setArticle_id(Integer article_id) {
        this.article_id = article_id;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getArticle_title() {
        return article_title;
    }

    public void setArticle_title(String article_title) {
        this.article_title = article_title;
    }

    public String getArticle_content() {
        return article_content;
    }

    public void setArticle_content(String article_content) {
        this.article_content = article_content;
    }

    public String getArticle_privacy() {
        return article_privacy;
    }

    public void setArticle_privacy(String article_privacy) {
        this.article_privacy = article_privacy;
    }

    public String getArticle_time() {
        return article_time;
    }

    public void setArticle_time(String article_time) {
        this.article_time = article_time;
    }
}
