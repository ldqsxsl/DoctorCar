package com.doctorcar.mobile.module.mine.model;

import com.doctorcar.mobile.api.Api;
import com.doctorcar.mobile.api.HostType;
import com.doctorcar.mobile.common.basebean.BaseRespose;
import com.doctorcar.mobile.common.baserx.RxSchedulers;
import com.doctorcar.mobile.module.mine.contract.FeedBackContract;

import rx.Observable;
import rx.functions.Func1;

/**
 * Created by dd on 2017/5/19.
 */

public class FeedbackModel implements FeedBackContract.Model{

    @Override
    public Observable<Object> addFeedback(String user_id, String feedback_content) {
        return Api.getDefault(HostType.ADD_FEEDBACK).addFeedback(user_id,feedback_content).map(new Func1<BaseRespose<Object>, Object>() {
            @Override
            public Object call(BaseRespose<Object> objectBaseRespose) {
                return objectBaseRespose.data;
            }
        }).compose(RxSchedulers.<Object>io_main());
    }


}
