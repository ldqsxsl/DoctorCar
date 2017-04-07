package com.doctorcar.mobile.api;

/**
 * Created by dd on 2017/2/9.
 */

public class Teacher {
    private String name;
    private String sex;

    public void teacher(){
        System.out.print("我是你的老师");
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
