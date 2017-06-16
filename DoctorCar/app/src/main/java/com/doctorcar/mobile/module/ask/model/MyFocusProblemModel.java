package com.doctorcar.mobile.module.ask.model;

import com.alibaba.fastjson.JSON;
import com.doctorcar.mobile.api.Api;
import com.doctorcar.mobile.api.HostType;
import com.doctorcar.mobile.common.basebean.BaseRespose;
import com.doctorcar.mobile.common.baserx.RxSchedulers;
import com.doctorcar.mobile.module.ask.bean.AnswerCommentResult;
import com.doctorcar.mobile.module.ask.bean.ProblemResult;
import com.doctorcar.mobile.module.ask.contract.MyFocusProblemContact;
import com.doctorcar.mobile.utils.TLUtil;

import rx.Observable;
import rx.functions.Func1;

/**
 * Created by dd on 2017/5/18.
 */

public class MyFocusProblemModel implements MyFocusProblemContact.Model{

    @Override
    public Observable<ProblemResult> getMyFocusProblemList(String user_id, int page, int page_size) {
        return Api.getDefault(HostType.GET_MY_FOCUS_PROBLEM_PAGE_LIST).getMyFocusProblemPageList(user_id,page,page_size).map(new Func1<BaseRespose<ProblemResult>, ProblemResult>() {
            @Override
            public ProblemResult call(BaseRespose<ProblemResult> problemResultBaseRespose) {
                return problemResultBaseRespose.data;
            }
        }).compose(RxSchedulers.<ProblemResult>io_main());
    }
}
