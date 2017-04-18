package com.doctorcar.mobile.module.ask.bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by dd on 2017/4/18.
 */

public class AnswerCommentResult implements Serializable{

    private List<AnswerCommentBean> list;

    public List<AnswerCommentBean> getList() {
        return list;
    }

    public void setList(List<AnswerCommentBean> list) {
        this.list = list;
    }
}
