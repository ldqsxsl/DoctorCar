package com.doctorcar.mobile.module.blog.contract;

import com.doctorcar.mobile.common.base.BaseModel;
import com.doctorcar.mobile.common.base.BasePresenter;
import com.doctorcar.mobile.common.base.BaseView;
import com.doctorcar.mobile.module.ask.bean.AnswerResult;
import com.doctorcar.mobile.module.ask.contract.AnswerContract;

import retrofit2.http.Field;
import rx.Observable;

/**
 * Created by dd on 2017/5/9.
 */

public interface ArticleContract {

    interface Model extends BaseModel {
        Observable<Object> addArticle(String user_id, String article_title, String article_content,String article_privacy);
    }

    interface View extends BaseView {
        void returnArticleData(Object object);
    }

    abstract static class Presenter extends BasePresenter<ArticleContract.View, ArticleContract.Model> {
        public abstract void addArticleRequest(String user_id, String article_title, String article_content,String article_privacy);
    }
}
