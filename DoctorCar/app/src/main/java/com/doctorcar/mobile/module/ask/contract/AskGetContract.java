package com.doctorcar.mobile.module.ask.contract;

import com.doctorcar.mobile.common.base.BaseModel;
import com.doctorcar.mobile.common.base.BasePresenter;
import com.doctorcar.mobile.common.base.BaseView;
import com.doctorcar.mobile.module.ask.bean.ProblemResult;

import rx.Observable;

/**
 * Created by dd on 2017/4/12.
 */

public interface AskGetContract {
    interface Model extends BaseModel {
        Observable<ProblemResult> getAsk(Integer page, Integer page_size);
    }
    interface View extends BaseView {
        void returnGetAskData(ProblemResult problemResult);
    }
    abstract static class Presenter extends BasePresenter<AskGetContract.View, AskGetContract.Model> {
        public abstract void getAskRequest(Integer page, Integer page_size);
    }
}
