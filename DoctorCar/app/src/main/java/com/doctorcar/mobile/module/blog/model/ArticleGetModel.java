package com.doctorcar.mobile.module.blog.model;

import com.doctorcar.mobile.api.Api;
import com.doctorcar.mobile.api.HostType;
import com.doctorcar.mobile.common.basebean.BaseRespose;
import com.doctorcar.mobile.common.baserx.RxSchedulers;
import com.doctorcar.mobile.module.blog.bean.ArticleResult;
import com.doctorcar.mobile.module.blog.contract.ArticleGetContract;
import com.doctorcar.mobile.utils.TLUtil;

import rx.Observable;
import rx.functions.Func1;

/**
 * Created by dd on 2017/5/9.
 */

public class ArticleGetModel implements ArticleGetContract.Model{

    @Override
    public Observable<ArticleResult> getArticleList(String user_id, Integer page, Integer page_size) {

        return Api.getDefault(HostType.GET_ARTICLE_LIST).getArticleList(user_id,page,page_size).map(new Func1<BaseRespose<ArticleResult>, ArticleResult>() {
            @Override
            public ArticleResult call(BaseRespose<ArticleResult> articleResultBaseRespose) {
                return articleResultBaseRespose.data;
            }
        }).compose(RxSchedulers.<ArticleResult>io_main());
    }

    @Override
    public Observable<Object> deleteArticle(Integer article_id) {
        return Api.getDefault(HostType.DELETE_ARTICLE).deleteArticle(article_id).map(new Func1<BaseRespose<Object>, Object>() {
            @Override
            public Object call(BaseRespose<Object> objectBaseRespose) {
                return objectBaseRespose.data;
            }
        }).compose(RxSchedulers.<Object>io_main());
    }
}
