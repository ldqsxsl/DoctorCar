package com.doctorcar.mobile.module.ask.model;

import com.doctorcar.mobile.api.Api;
import com.doctorcar.mobile.api.HostType;
import com.doctorcar.mobile.bean.UploadImageResult;
import com.doctorcar.mobile.common.basebean.BaseRespose;
import com.doctorcar.mobile.common.baserx.RxSchedulers;
import com.doctorcar.mobile.module.ask.bean.AnswerCommentResult;
import com.doctorcar.mobile.module.ask.contract.AnswerCommentContract;
import com.doctorcar.mobile.module.ask.contract.AskContract;

import java.util.Map;

import okhttp3.RequestBody;
import rx.Observable;
import rx.functions.Func1;

/**
 * Created by dd on 2017/4/6.
 */

public class AnswerCommentModel implements AnswerCommentContract.Model {
    @Override
    public Observable<Object> submitAnswerComment(Integer answer_id, String user_id, String comment_answer_content) {
        return Api.getDefault(HostType.ADD_COMMENT_ANSWER).submitCommentAnswer(answer_id,user_id,comment_answer_content).map(new Func1<BaseRespose<Object>, Object>() {
            @Override
            public Object call(BaseRespose<Object> objectBaseRespose) {
                return objectBaseRespose.data;
            }
        }).compose(RxSchedulers.<Object>io_main());
    }

    @Override
    public Observable<AnswerCommentResult> getAnswerCommentList(Integer answer_id,Integer page, Integer page_size) {
        return Api.getDefault(HostType.GET_COMMENT_ANSWER_LIST).getAnswerComment( answer_id,page,page_size).map(new Func1<BaseRespose<AnswerCommentResult>, AnswerCommentResult>() {
            @Override
            public AnswerCommentResult call(BaseRespose<AnswerCommentResult> answerCommentResultBaseRespose) {
                return answerCommentResultBaseRespose.data;
            }
        }).compose(RxSchedulers.<AnswerCommentResult>io_main());
    }



}
