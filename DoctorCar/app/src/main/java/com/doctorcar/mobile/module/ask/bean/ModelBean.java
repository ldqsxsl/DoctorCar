package com.doctorcar.mobile.module.ask.bean;

import java.io.Serializable;

/**
 * Created by dd on 2017/3/9.
 */

public class ModelBean implements Serializable{
    private Integer model_id;
    private Integer brand_id;
    private String model_name;

    public Integer getModel_id() {
        return model_id;
    }

    public void setModel_id(Integer model_id) {
        this.model_id = model_id;
    }

    public Integer getBrand_id() {
        return brand_id;
    }

    public void setBrand_id(Integer brand_id) {
        this.brand_id = brand_id;
    }

    public String getModel_name() {
        return model_name;
    }

    public void setModel_name(String model_name) {
        this.model_name = model_name;
    }
}
