package com.doctorcar.mobile.module.ask.contract;

import com.doctorcar.mobile.common.base.BaseModel;
import com.doctorcar.mobile.common.base.BasePresenter;
import com.doctorcar.mobile.common.base.BaseView;
import com.doctorcar.mobile.module.ask.bean.AnswerCommentResult;
import com.doctorcar.mobile.module.ask.bean.ProblemResult;

import rx.Observable;

/**
 * Created by dd on 2017/5/18.
 */

public interface MyFocusProblemContact {

    interface Model extends BaseModel {
        Observable<ProblemResult> getMyFocusProblemList(String user_id,int page,int page_size);
    }

    interface View extends BaseView {

        void returnMyFocusProblemListData(ProblemResult problemResult);
    }

    abstract static class Presenter extends BasePresenter<MyFocusProblemContact.View, MyFocusProblemContact.Model> {
        public abstract void getMyFocusProblemRequest(String user_id,int page,int page_size);
    }
}
