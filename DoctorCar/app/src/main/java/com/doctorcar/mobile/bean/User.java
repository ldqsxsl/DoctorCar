package com.doctorcar.mobile.bean;

import java.io.Serializable;

/**
 * des:
 * Created by xsf
 * on 2016.09.9:54
 */
public class User implements Serializable{
    private String loginName;
    private String id;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

}
