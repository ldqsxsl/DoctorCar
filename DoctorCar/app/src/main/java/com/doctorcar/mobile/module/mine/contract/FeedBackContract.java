package com.doctorcar.mobile.module.mine.contract;

import com.doctorcar.mobile.common.base.BaseModel;
import com.doctorcar.mobile.common.base.BasePresenter;
import com.doctorcar.mobile.common.base.BaseView;
import com.doctorcar.mobile.module.blog.contract.ArticleContract;

import rx.Observable;

/**
 * Created by dd on 2017/5/19.
 */

public interface FeedBackContract {

    interface Model extends BaseModel {
        Observable<Object> addFeedback(String user_id, String feedback_content);
    }

    interface View extends BaseView {
        void returnFeedbackData(Object object);
    }

    abstract static class Presenter extends BasePresenter<FeedBackContract.View, FeedBackContract.Model> {
        public abstract void addFeedbackRequest(String user_id, String feedback_content);
    }
}
