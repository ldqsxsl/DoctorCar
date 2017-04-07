package com.doctorcar.mobile.module.ask.bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by dd on 2017/3/15.
 */

public class BrandModelBean implements Serializable{

    private List<BrandBean> brandList;
    private List<ModelBean> modelList;

    public List<BrandBean> getBrandList() {
        return brandList;
    }

    public void setBrandList(List<BrandBean> brandList) {
        this.brandList = brandList;
    }

    public List<ModelBean> getModelList() {
        return modelList;
    }

    public void setModelList(List<ModelBean> modelList) {
        this.modelList = modelList;
    }
}
