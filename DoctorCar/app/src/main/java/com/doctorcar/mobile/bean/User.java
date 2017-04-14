package com.doctorcar.mobile.bean;

import java.io.Serializable;

/**
 * des:
 * Created by xsf
 * on 2016.09.9:54
 */
public class User implements Serializable{

    private String user_id;
    private String user_phone;
    private String user_image;

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getUser_phone() {
        return user_phone;
    }

    public void setUser_phone(String user_phone) {
        this.user_phone = user_phone;
    }

    public String getUser_image() {
        return user_image;
    }

    public void setUser_image(String user_image) {
        this.user_image = user_image;
    }
}
