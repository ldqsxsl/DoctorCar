package com.doctorcar.mobile.module.ask.bean;

import java.io.Serializable;

/**
 * Created by dd on 2017/4/18.
 */

public class AnswerCommentBean implements Serializable{

    private Integer comment_answer_id;

    private Integer answer_id;

    private String user_id;//回答人id

    private String comment_answer_content;

    private String comment_answer_time;

    public Integer getComment_answer_id() {
        return comment_answer_id;
    }

    public void setComment_answer_id(Integer comment_answer_id) {
        this.comment_answer_id = comment_answer_id;
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

    public String getComment_answer_content() {
        return comment_answer_content;
    }

    public void setComment_answer_content(String comment_answer_content) {
        this.comment_answer_content = comment_answer_content;
    }

    public String getComment_answer_time() {
        return comment_answer_time;
    }

    public void setComment_answer_time(String comment_answer_time) {
        this.comment_answer_time = comment_answer_time;
    }
}
