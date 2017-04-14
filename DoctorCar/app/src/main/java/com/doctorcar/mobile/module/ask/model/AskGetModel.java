package com.doctorcar.mobile.module.ask.model;

import com.alibaba.fastjson.JSON;
import com.doctorcar.mobile.api.Api;
import com.doctorcar.mobile.api.HostType;
import com.doctorcar.mobile.common.basebean.BaseRespose;
import com.doctorcar.mobile.common.baserx.RxSchedulers;
import com.doctorcar.mobile.module.ask.bean.ProblemResult;
import com.doctorcar.mobile.module.ask.contract.AskGetContract;
import com.doctorcar.mobile.utils.TLUtil;

import rx.Observable;
import rx.functions.Func1;

/**
 * Created by dd on 2017/4/12.
 */

public class AskGetModel implements AskGetContract.Model{
    @Override
    public Observable<ProblemResult> getAsk(Integer page, Integer page_size) {
        return Api.getDefault(HostType.GET_PROBLEM_LIST).getProblem(page,page_size).map(new Func1<BaseRespose<ProblemResult>, ProblemResult>() {
            @Override
            public ProblemResult call(BaseRespose<ProblemResult> objectBaseRespose) {
                TLUtil.showLog(JSON.toJSONString(objectBaseRespose));
                return objectBaseRespose.data;
            }
        }).compose(RxSchedulers.<ProblemResult>io_main());
    }
}
