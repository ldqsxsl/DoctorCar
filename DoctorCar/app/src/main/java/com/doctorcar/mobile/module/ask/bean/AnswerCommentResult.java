package com.doctorcar.mobile.module.ask.bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by dd on 2017/4/18.
 */

public class AnswerCommentResult implements Serializable{

    private List<AnswerCommentBean> list;
    private Integer answer_id;

    private String user_id;//回答用户id

    private Integer problem_id; //回答的那个问题

    private String answer_content;

    private String answer_time;

    private Integer answer_praise_number;

    private Integer answer_comment_number;

    private boolean my_praise;//我是否点赞

    private String user_phone;

    private String user_image;
    private String user_description;

    //"answer_praise_number":1,
    // "answer_content":"哦婆婆搜搜女女",
    // "list":[],
    // "my_praise":true,
    // "answer_time":"1494923921709",
    // "user_id":"402894d75b8a7b3b015b8a7e88e80001",
    // "answer_id":5,
    // "problem_id":2

    public List<AnswerCommentBean> getList() {
        return list;
    }

    public void setList(List<AnswerCommentBean> list) {
        this.list = list;
    }

    public Integer getAnswer_id() {
        return answer_id;
    }

    public void setAnswer_id(Integer answer_id) {
        this.answer_id = answer_id;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public Integer getProblem_id() {
        return problem_id;
    }

    public void setProblem_id(Integer problem_id) {
        this.problem_id = problem_id;
    }

    public String getAnswer_content() {
        return answer_content;
    }

    public void setAnswer_content(String answer_content) {
        this.answer_content = answer_content;
    }

    public String getAnswer_time() {
        return answer_time;
    }

    public void setAnswer_time(String answer_time) {
        this.answer_time = answer_time;
    }

    public Integer getAnswer_praise_number() {
        return answer_praise_number;
    }

    public void setAnswer_praise_number(Integer answer_praise_number) {
        this.answer_praise_number = answer_praise_number;
    }

    public Integer getAnswer_comment_number() {
        return answer_comment_number;
    }

    public void setAnswer_comment_number(Integer answer_comment_number) {
        this.answer_comment_number = answer_comment_number;
    }

    public boolean isMy_praise() {
        return my_praise;
    }

    public void setMy_praise(boolean my_praise) {
        this.my_praise = my_praise;
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

    public String getUser_description() {
        return user_description;
    }

    public void setUser_description(String user_description) {
        this.user_description = user_description;
    }
}
