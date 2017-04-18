package com.doctorcar.mobile.module.ask.contract;

import com.doctorcar.mobile.bean.UploadImageResult;
import com.doctorcar.mobile.common.base.BaseModel;
import com.doctorcar.mobile.common.base.BasePresenter;
import com.doctorcar.mobile.common.base.BaseView;
import com.doctorcar.mobile.module.ask.bean.AnswerCommentResult;
import com.doctorcar.mobile.module.ask.bean.AnswerResult;

import java.util.Map;

import okhttp3.RequestBody;
import rx.Observable;

/**
 * Created by dd on 2017/4/6.
 */

public interface AnswerCommentContract {
    interface Model extends BaseModel {
        Observable<Object> submitAnswerComment(Integer answer_id,String user_id, String comment_answer_content);
        Observable<AnswerCommentResult> getAnswerCommentList(Integer answer_id,Integer page, Integer page_size);
    }

    interface View extends BaseView {
        void returnAnswerCommentData(Object object);
        void returnAnswerCommentListData(AnswerCommentResult answerCommentResult);
    }
    abstract static class Presenter extends BasePresenter<AnswerCommentContract.View, AnswerCommentContract.Model> {
        public abstract void submitAnswerCommentRequest(Integer answer_id,String user_id, String comment_answer_content);
        public abstract void getCommentAnswerRequest(Integer answer_id,Integer page, Integer page_size);
    }

}
