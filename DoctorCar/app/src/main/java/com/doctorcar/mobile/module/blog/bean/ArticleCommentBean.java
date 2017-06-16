package com.doctorcar.mobile.module.blog.bean;

/**
 * Created by dd on 2017/6/16.
 */

public class ArticleCommentBean {

    private Integer comment_article_id;

    private Integer article_id;

    private String author_user_id;//写文章的人

    private String comment_user_id;//评论的人

    private String comment_article_content;

    private String comment_article_time;

    public Integer getComment_article_id() {
        return comment_article_id;
    }

    public void setComment_article_id(Integer comment_article_id) {
        this.comment_article_id = comment_article_id;
    }

    public Integer getArticle_id() {
        return article_id;
    }

    public void setArticle_id(Integer article_id) {
        this.article_id = article_id;
    }

    public String getAuthor_user_id() {
        return author_user_id;
    }

    public void setAuthor_user_id(String author_user_id) {
        this.author_user_id = author_user_id;
    }

    public String getComment_user_id() {
        return comment_user_id;
    }

    public void setComment_user_id(String comment_user_id) {
        this.comment_user_id = comment_user_id;
    }

    public String getComment_article_content() {
        return comment_article_content;
    }

    public void setComment_article_content(String comment_article_content) {
        this.comment_article_content = comment_article_content;
    }

    public String getComment_article_time() {
        return comment_article_time;
    }

    public void setComment_article_time(String comment_article_time) {
        this.comment_article_time = comment_article_time;
    }
}
