package com.doctorcar.mobile.module.ask.model;

import com.doctorcar.mobile.api.Api;
import com.doctorcar.mobile.api.HostType;
import com.doctorcar.mobile.common.basebean.BaseRespose;
import com.doctorcar.mobile.common.baserx.RxSchedulers;
import com.doctorcar.mobile.module.ask.bean.ProblemResult;
import com.doctorcar.mobile.module.ask.contract.WaitSolveProblemContact;

import rx.Observable;
import rx.functions.Func1;

/**
 * Created by dd on 2017/5/18.
 */

public class WaitSolveProblemModel implements WaitSolveProblemContact.Model{

    @Override
    public Observable<ProblemResult> getNoSolveProblemList(int page) {
        return Api.getDefault(HostType.GET_NO_SOLVE_PROBLEM_PAGE_LIST).getNoSolveProblemPageList(page).map(new Func1<BaseRespose<ProblemResult>, ProblemResult>() {
            @Override
            public ProblemResult call(BaseRespose<ProblemResult> problemResultBaseRespose) {
                return problemResultBaseRespose.data;
            }
        }).compose(RxSchedulers.<ProblemResult>io_main());
    }
}
