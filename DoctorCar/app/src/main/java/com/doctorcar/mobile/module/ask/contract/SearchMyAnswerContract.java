package com.doctorcar.mobile.module.ask.contract;

import com.doctorcar.mobile.common.base.BaseModel;
import com.doctorcar.mobile.common.base.BasePresenter;
import com.doctorcar.mobile.common.base.BaseView;
import com.doctorcar.mobile.module.ask.bean.AnswerCommentResult;

import rx.Observable;

/**
 * Created by dd on 2017/5/16.
 */

public interface SearchMyAnswerContract {

    interface Model extends BaseModel {
        Observable<Object> submitAnswerComment(Integer answer_id,String user_id, String comment_answer_content);
        Observable<AnswerCommentResult> getMyAnswerCommentList(Integer problem_id,String user_id);
    }

    interface View extends BaseView {
        void returnAnswerCommentData(Object object);
        void returnMyAnswerCommentListData(AnswerCommentResult answerCommentResult);
    }

    abstract static class Presenter extends BasePresenter<SearchMyAnswerContract.View, SearchMyAnswerContract.Model> {
        public abstract void submitAnswerCommentRequest(Integer answer_id,String user_id, String comment_answer_content);
        public abstract void getMyCommentAnswerRequest(Integer problem_id,String user_id);
    }

}
