package com.doctorcar.mobile.module.blog.contract;

import com.doctorcar.mobile.common.base.BaseModel;
import com.doctorcar.mobile.common.base.BasePresenter;
import com.doctorcar.mobile.common.base.BaseView;
import com.doctorcar.mobile.module.blog.bean.ArticleResult;

import rx.Observable;

/**
 * Created by dd on 2017/5/9.
 */

public interface ArticleGetContract {
    interface Model extends BaseModel {
        Observable<ArticleResult> getArticleList(String user_id,Integer page,Integer page_size);
        Observable<Object> deleteArticle(Integer article_id);
    }

    interface View extends BaseView {
        void returnArticleListData(ArticleResult articleResult);
        void returnDeleteArticleData();
    }

    abstract static class Presenter extends BasePresenter<ArticleGetContract.View, ArticleGetContract.Model> {
        public abstract void getArticleListRequest(String user_id,Integer page,Integer page_size);
        public abstract void deleteArticleRequest(Integer article_id);
    }
}
