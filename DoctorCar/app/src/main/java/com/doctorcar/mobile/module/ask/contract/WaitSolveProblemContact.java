package com.doctorcar.mobile.module.ask.contract;

import com.doctorcar.mobile.common.base.BaseModel;
import com.doctorcar.mobile.common.base.BasePresenter;
import com.doctorcar.mobile.common.base.BaseView;
import com.doctorcar.mobile.module.ask.bean.ProblemResult;

import rx.Observable;

/**
 * Created by dd on 2017/5/18.
 */

public interface WaitSolveProblemContact {

    interface Model extends BaseModel {
        Observable<ProblemResult> getNoSolveProblemList(int page);
    }

    interface View extends BaseView {

        void returnNoSolveProblemListData(ProblemResult problemResult);
    }

    abstract static class Presenter extends BasePresenter<WaitSolveProblemContact.View, WaitSolveProblemContact.Model> {
        public abstract void getNoSolveProblemRequest(int page);
    }
}
