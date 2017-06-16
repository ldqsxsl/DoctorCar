package com.doctorcar.mobile.module.ask.bean;

import java.io.Serializable;

/**
 * Created by dd on 2017/4/17.
 */

public class AnswerBean implements Serializable{

    private Integer answer_id;
    private String user_id;
    private Integer problem_id;
    private String answer_content;
    private String answer_time;
    private Integer answer_praise_number;
    private Integer answer_comment_number;
    private boolean is_my_praise;//我是否点赞

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

    public boolean is_my_praise() {
        return is_my_praise;
    }

    public void setIs_my_praise(boolean is_my_praise) {
        this.is_my_praise = is_my_praise;
    }
}
