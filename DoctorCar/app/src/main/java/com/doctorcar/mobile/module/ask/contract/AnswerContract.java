package com.doctorcar.mobile.module.ask.contract;

import com.doctorcar.mobile.bean.UploadImageResult;
import com.doctorcar.mobile.common.base.BaseModel;
import com.doctorcar.mobile.common.base.BasePresenter;
import com.doctorcar.mobile.common.base.BaseView;
import com.doctorcar.mobile.module.ask.bean.AnswerResult;

import java.util.Map;

import okhttp3.RequestBody;
import retrofit2.http.Field;
import rx.Observable;

/**
 * Created by dd on 2017/4/6.
 */

public interface AnswerContract {
    interface Model extends BaseModel {
        Observable<Object> submitAnswer(String user_id, Integer problem_id, String answer_content);
        Observable<AnswerResult> getAnswerList(Integer problem_id,Integer page, Integer page_size);
    }

    interface View extends BaseView {
        void returnAnswerData(Object object);
        void returnAnswerListData(AnswerResult answerResult);
    }

    abstract static class Presenter extends BasePresenter<AnswerContract.View, AnswerContract.Model> {
        public abstract void submitAnswerRequest(String user_id, Integer problem_id, String answer_content);
        public abstract void getAnswerRequest(Integer problem_id,Integer page, Integer page_size);
    }

}
