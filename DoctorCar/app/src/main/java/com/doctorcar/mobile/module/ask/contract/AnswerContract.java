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
        Observable<Object> addProblemFocus(String user_id, Integer problem_id);
        Observable<Object> deleteProblemFocus(String user_id, Integer problem_id);
        Observable<Object> addAnswerPraise(Integer answer_id ,String answer_user_id,String praise_user_id,Integer problem_id);
        Observable<Object> deleteAnswerPraise(Integer answer_id,String praise_user_id);
        Observable<AnswerResult> getAnswerList(String user_id,Integer problem_id,Integer page, Integer page_size);
    }

    interface View extends BaseView {
        void returnAnswerData(Object object);
        void returnAddProblemFocus(Object object);
        void returnDeleteProblemFocus(Object object);
        void returnAddAnswerPraise(Object object);
        void returnDeleteAnswerPraise(Object object);
        void returnAnswerListData(AnswerResult answerResult);
    }

    abstract static class Presenter extends BasePresenter<AnswerContract.View, AnswerContract.Model> {
        public abstract void submitAnswerRequest(String user_id, Integer problem_id, String answer_content);
        public abstract void addProblemFocusRequest(String user_id, Integer problem_id);
        public abstract void deleteProblemFocusRequest(String user_id, Integer problem_id);
        public abstract void getAnswerRequest(String user_id,Integer problem_id,Integer page, Integer page_size);
        public abstract void addAnswerPraiseRequest(Integer answer_id ,String answer_user_id,String praise_user_id,Integer problem_id);
        public abstract void deleteAnswerPraiseRequest(Integer answer_id,String praise_user_id);
    }

}
