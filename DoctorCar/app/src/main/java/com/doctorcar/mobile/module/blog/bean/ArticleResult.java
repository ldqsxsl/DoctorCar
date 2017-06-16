package com.doctorcar.mobile.module.blog.bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by dd on 2017/5/9.
 */

public class ArticleResult implements Serializable{

    private List<ArticleBean> list;

    public List<ArticleBean> getList() {
        return list;
    }

    public void setList(List<ArticleBean> list) {
        this.list = list;
    }
}
