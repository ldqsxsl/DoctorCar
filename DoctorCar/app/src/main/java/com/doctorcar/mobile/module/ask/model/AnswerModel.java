package com.doctorcar.mobile.module.ask.model;

import com.doctorcar.mobile.api.Api;
import com.doctorcar.mobile.api.HostType;
import com.doctorcar.mobile.bean.UploadImageResult;
import com.doctorcar.mobile.common.basebean.BaseRespose;
import com.doctorcar.mobile.common.baserx.RxSchedulers;
import com.doctorcar.mobile.module.ask.bean.AnswerResult;
import com.doctorcar.mobile.module.ask.contract.AnswerContract;
import com.doctorcar.mobile.module.ask.contract.AskContract;

import java.util.Map;

import okhttp3.RequestBody;
import rx.Observable;
import rx.functions.Func1;

/**
 * Created by dd on 2017/4/6.
 */

public class AnswerModel implements AnswerContract.Model {

    @Override
    public Observable<Object> submitAnswer(String user_id, Integer problem_id, String answer_content) {
        return Api.getDefault(HostType.ADD_ANSWER).submitAnswer(user_id, problem_id, answer_content).map(new Func1<BaseRespose<Object>, Object>() {
            @Override
            public Object call(BaseRespose<Object> objectBaseRespose) {
                return objectBaseRespose.data;
            }
        }).compose(RxSchedulers.<Object>io_main());
    }

    @Override
    public Observable<AnswerResult> getAnswerList(String user_id,Integer problem_id,Integer page, Integer page_size) {
        return Api.getDefault(HostType.GET_ANSWER_LIST).getAnswer(user_id,problem_id,page,page_size).map(new Func1<BaseRespose<AnswerResult>, AnswerResult>() {
            @Override
            public AnswerResult call(BaseRespose<AnswerResult> objectBaseRespose) {
                return objectBaseRespose.data;
            }
        }).compose(RxSchedulers.<AnswerResult>io_main());
    }

    @Override
    public Observable<Object> addProblemFocus(String user_id, Integer problem_id) {
        return Api.getDefault(HostType.FOCUS).addProblemFocus(user_id, problem_id).map(new Func1<BaseRespose<Object>, Object>() {
            @Override
            public Object call(BaseRespose<Object> objectBaseRespose) {
                return objectBaseRespose.data;
            }
        }).compose(RxSchedulers.<Object>io_main());
    }

    @Override
    public Observable<Object> deleteProblemFocus(String user_id, Integer problem_id) {
        return Api.getDefault(HostType.FOCUS).deleteProblemFocus(user_id, problem_id).map(new Func1<BaseRespose<Object>, Object>() {
            @Override
            public Object call(BaseRespose<Object> objectBaseRespose) {
                return objectBaseRespose.data;
            }
        }).compose(RxSchedulers.<Object>io_main());
    }


    @Override
    public Observable<Object> addAnswerPraise(Integer answer_id, String answer_user_id, String praise_user_id, Integer problem_id) {
        return Api.getDefault(HostType.ADD_ANSWER_PRAISE).addAnswerPraise(answer_id,answer_user_id,praise_user_id,problem_id).map(new Func1<BaseRespose<Object>, Object>() {
            @Override
            public Object call(BaseRespose<Object> objectBaseRespose) {
                return objectBaseRespose.data;
            }
        }).compose(RxSchedulers.<Object>io_main());
    }

    @Override
    public Observable<Object> deleteAnswerPraise(Integer answer_id,String praise_user_id) {
        return Api.getDefault(HostType.DELETE_ANSWER_PRAISE).deleteAnswerPraise(answer_id,praise_user_id).map(new Func1<BaseRespose<Object>, Object>() {
            @Override
            public Object call(BaseRespose<Object> objectBaseRespose) {
                return objectBaseRespose.data;
            }
        }).compose(RxSchedulers.<Object>io_main());
    }
}
