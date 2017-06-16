package com.doctorcar.mobile.module.ask.contract;

import com.doctorcar.mobile.common.base.BaseModel;
import com.doctorcar.mobile.common.base.BasePresenter;
import com.doctorcar.mobile.common.base.BaseView;
import com.doctorcar.mobile.module.ask.bean.ProblemResult;

import rx.Observable;

/**
 * Created by dd on 2017/5/10.
 */

public interface MyAskContract {

    interface Model extends BaseModel {
        Observable<ProblemResult> getMyAsk(String user_id);
    }
    interface View extends BaseView {
        void returnGetMyAskData(ProblemResult problemResult);
    }
    abstract static class Presenter extends BasePresenter<MyAskContract.View, MyAskContract.Model> {
        public abstract void getMyAskRequest(String user_id);
    }
}
