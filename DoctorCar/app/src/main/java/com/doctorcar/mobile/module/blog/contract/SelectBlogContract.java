package com.doctorcar.mobile.module.blog.contract;

import com.doctorcar.mobile.common.base.BaseModel;
import com.doctorcar.mobile.common.base.BasePresenter;
import com.doctorcar.mobile.common.base.BaseView;
import com.doctorcar.mobile.module.blog.bean.ArticleResult;

import rx.Observable;

/**
 * Created by dd on 2017/6/16.
 */

public interface SelectBlogContract {

    interface Model extends BaseModel {
        Observable<ArticleResult> getSelectArticleList(Integer page);
    }

    interface View extends BaseView {
        void returnSelectArticleListData(ArticleResult articleResult);
    }

    abstract static class Presenter extends BasePresenter<SelectBlogContract.View, SelectBlogContract.Model> {
        public abstract void getSelectArticleListRequest(Integer page);
    }

}
