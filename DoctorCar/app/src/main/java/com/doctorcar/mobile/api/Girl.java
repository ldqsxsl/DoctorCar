package com.doctorcar.mobile.api;

/**
 * Created by dd on 2017/2/9.
 */

public class Girl {
    private String name;
    private String sex;


    public void friends(){
        System.out.print("我是你的女朋友");
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }
}
