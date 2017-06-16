package com.doctorcar.mobile.module.ask.model;

import com.doctorcar.mobile.api.Api;
import com.doctorcar.mobile.api.HostType;
import com.doctorcar.mobile.common.basebean.BaseRespose;
import com.doctorcar.mobile.common.baserx.RxSchedulers;
import com.doctorcar.mobile.module.ask.bean.ProblemResult;
import com.doctorcar.mobile.module.ask.contract.MyAskContract;

import rx.Observable;
import rx.functions.Func1;

/**
 * Created by dd on 2017/5/10.
 */

public class MyAskModel implements MyAskContract.Model{

    @Override
    public Observable<ProblemResult> getMyAsk(String user_id) {

        return Api.getDefault(HostType.GET_MY_PROBLEM_LIST).getMyProblemList(user_id).map(new Func1<BaseRespose<ProblemResult>, ProblemResult>() {
            @Override
            public ProblemResult call(BaseRespose<ProblemResult> problemResultBaseRespose) {
                return problemResultBaseRespose.data;
            }
        }).compose(RxSchedulers.<ProblemResult>io_main());
    }
}
