package com.doctorcar.mobile.module.ask.bean;

import android.media.Image;

import java.io.Serializable;
import java.util.List;

/**
 * Created by dd on 2017/4/12.
 */

public class ProblemBean implements Serializable{
    private Integer problem_id;
    private String problem_title;
    private String problem_content;
    private String problem_time;
    private String problem_img;
    private Integer brand_id;
    private Integer model_id;
    private String user_id;
    private String problem_status;//问提 0 没被解决 1 被解决
    private Integer accept_answer_id;
    private List<ImageBean> list_image;

    public Integer getProblem_id() {
        return problem_id;
    }

    public void setProblem_id(Integer problem_id) {
        this.problem_id = problem_id;
    }

    public String getProblem_title() {
        return problem_title;
    }

    public void setProblem_title(String problem_title) {
        this.problem_title = problem_title;
    }

    public String getProblem_content() {
        return problem_content;
    }

    public void setProblem_content(String problem_content) {
        this.problem_content = problem_content;
    }

    public String getProblem_time() {
        return problem_time;
    }

    public void setProblem_time(String problem_time) {
        this.problem_time = problem_time;
    }

    public String getProblem_img() {
        return problem_img;
    }

    public void setProblem_img(String problem_img) {
        this.problem_img = problem_img;
    }

    public Integer getBrand_id() {
        return brand_id;
    }

    public void setBrand_id(Integer brand_id) {
        this.brand_id = brand_id;
    }

    public Integer getModel_id() {
        return model_id;
    }

    public void setModel_id(Integer model_id) {
        this.model_id = model_id;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public List<ImageBean> getList_image() {
        return list_image;
    }

    public void setList_image(List<ImageBean> list_image) {
        this.list_image = list_image;
    }

    public String getProblem_status() {
        return problem_status;
    }

    public void setProblem_status(String problem_status) {
        this.problem_status = problem_status;
    }

    public Integer getAccept_answer_id() {
        return accept_answer_id;
    }

    public void setAccept_answer_id(Integer accept_answer_id) {
        this.accept_answer_id = accept_answer_id;
    }
}
