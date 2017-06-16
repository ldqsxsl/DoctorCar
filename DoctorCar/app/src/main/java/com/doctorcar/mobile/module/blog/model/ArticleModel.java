package com.doctorcar.mobile.module.blog.model;

import com.doctorcar.mobile.api.Api;
import com.doctorcar.mobile.api.HostType;
import com.doctorcar.mobile.common.basebean.BaseRespose;
import com.doctorcar.mobile.common.baserx.RxSchedulers;
import com.doctorcar.mobile.module.blog.contract.ArticleContract;

import rx.Observable;
import rx.functions.Func1;

/**
 * Created by dd on 2017/5/9.
 */

public class ArticleModel implements ArticleContract.Model {
    @Override
    public Observable<Object> addArticle(String user_id, String article_title, String article_content, String article_privacy) {
        return Api.getDefault(HostType.ADD_ARTICLE).addArticle(user_id,article_title,article_content,article_privacy).map(new Func1<BaseRespose<Object>, Object>() {
            @Override
            public Object call(BaseRespose<Object> objectBaseRespose) {
                return objectBaseRespose.data;
            }
        }).compose(RxSchedulers.<Object>io_main());
    }
}
