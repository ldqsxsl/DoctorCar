package com.doctorcar.mobile.module.ask.bean;

import com.doctorcar.mobile.view.indexbar.bean.BaseIndexPinyinBean;

import java.io.Serializable;

/**
 * Created by dd on 2017/3/8.
 */

public class BrandBean extends BaseIndexPinyinBean implements Serializable {

    private Integer brand_id;
    private String brand_name;
    private String brand_img;
    private boolean isTop;//是否是最上面的 不需要被转化成拼音的

    public BrandBean() {
    }

    public BrandBean(String brand_name) {
        this.brand_name = brand_name;
    }

    public Integer getBrand_id() {
        return brand_id;
    }

    public void setBrand_id(Integer brand_id) {
        this.brand_id = brand_id;
    }

    public String getBrand_name() {
        return brand_name;
    }

    public BrandBean setBrand_name(String brand_name) {
        this.brand_name = brand_name;
        return this;
    }

    public String getBrand_img() {
        return brand_img;
    }

    public void setBrand_img(String brand_img) {
        this.brand_img = brand_img;
    }

    @Override
    public String getTarget() {
        return brand_name;
    }

    public boolean isTop() {
        return isTop;
    }

    public BrandBean setTop(boolean top) {
        isTop = top;
        return this;
    }

    @Override
    public boolean isNeedToPinyin() {
        return !isTop;
    }
    @Override
    public boolean isShowSuspension() {
        return !isTop;
    }
}
