package com.doctorcar.mobile.module.ask.model;

import com.alibaba.fastjson.JSON;
import com.doctorcar.mobile.api.Api;
import com.doctorcar.mobile.api.HostType;
import com.doctorcar.mobile.common.basebean.BaseRespose;
import com.doctorcar.mobile.common.baserx.RxSchedulers;
import com.doctorcar.mobile.module.ask.bean.AnswerCommentResult;
import com.doctorcar.mobile.module.ask.contract.SearchMyAnswerContract;
import com.doctorcar.mobile.utils.TLUtil;

import rx.Observable;
import rx.functions.Func1;

/**
 * Created by dd on 2017/5/16.
 */

public class SearchMyAnswerModel implements SearchMyAnswerContract.Model{

    @Override
    public Observable<AnswerCommentResult> getMyAnswerCommentList(Integer problem_id, String user_id) {
        return Api.getDefault(HostType.GET_MY_ANSWER).getMyAnswer(problem_id,user_id).map(new Func1<BaseRespose<AnswerCommentResult>, AnswerCommentResult>() {
            @Override
            public AnswerCommentResult call(BaseRespose<AnswerCommentResult> answerCommentResultBaseRespose) {
                TLUtil.showLog(JSON.toJSON(answerCommentResultBaseRespose.data).toString());
                return answerCommentResultBaseRespose.data;
            }
        }).compose(RxSchedulers.<AnswerCommentResult>io_main());
    }

    @Override
    public Observable<Object> submitAnswerComment(Integer answer_id, String user_id, String comment_answer_content) {
        return Api.getDefault(HostType.ADD_COMMENT_ANSWER).submitCommentAnswer(answer_id,user_id,comment_answer_content).map(new Func1<BaseRespose<Object>, Object>() {
            @Override
            public Object call(BaseRespose<Object> objectBaseRespose) {
                return objectBaseRespose.data;
            }
        }).compose(RxSchedulers.<Object>io_main());
    }
}
