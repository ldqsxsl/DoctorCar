package com.doctorcar.mobile.module.ask.bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by dd on 2017/4/17.
 */

public class AnswerResult implements Serializable{

    private Integer answer_number;
    private Integer focus_number;
    private boolean is_answer;
    private boolean is_focus_problem;

    private List<AnswerBean> list;

    public Integer getAnswer_number() {
        return answer_number;
    }

    public void setAnswer_number(Integer answer_number) {
        this.answer_number = answer_number;
    }

    public Integer getFocus_number() {
        return focus_number;
    }

    public void setFocus_number(Integer focus_number) {
        this.focus_number = focus_number;
    }

    public boolean is_answer() {
        return is_answer;
    }

    public void setIs_answer(boolean is_answer) {
        this.is_answer = is_answer;
    }

    public List<AnswerBean> getList() {
        return list;
    }

    public void setList(List<AnswerBean> list) {
        this.list = list;
    }

    public boolean is_focus_problem() {
        return is_focus_problem;
    }

    public void setIs_focus_problem(boolean is_focus_problem) {
        this.is_focus_problem = is_focus_problem;
    }
}
