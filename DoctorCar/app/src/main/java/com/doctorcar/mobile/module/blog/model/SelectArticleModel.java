package com.doctorcar.mobile.module.blog.model;

import com.doctorcar.mobile.api.Api;
import com.doctorcar.mobile.api.HostType;
import com.doctorcar.mobile.common.basebean.BaseRespose;
import com.doctorcar.mobile.common.baserx.RxSchedulers;
import com.doctorcar.mobile.module.blog.bean.ArticleResult;
import com.doctorcar.mobile.module.blog.contract.SelectBlogContract;

import rx.Observable;
import rx.functions.Func1;

/**
 * Created by dd on 2017/6/16.
 */

public class SelectArticleModel implements SelectBlogContract.Model{
    @Override
    public Observable<ArticleResult> getSelectArticleList(Integer page) {
        return Api.getDefault(HostType.GET_SELECT_BLOG_LIST).getSelectArticleList(page).map(new Func1<BaseRespose<ArticleResult>, ArticleResult>() {
            @Override
            public ArticleResult call(BaseRespose<ArticleResult> articleResultBaseRespose) {
                return articleResultBaseRespose.data;
            }
        }).compose(RxSchedulers.<ArticleResult>io_main());
    }
}
