package com.doctorcar.mobile.module.ask.bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by dd on 2017/4/17.
 */

public class AnswerResult implements Serializable{
    private List<AnswerBean> list;

    public List<AnswerBean> getList() {
        return list;
    }

    public void setList(List<AnswerBean> list) {
        this.list = list;
    }
}
