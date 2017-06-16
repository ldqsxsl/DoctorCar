package com.doctorcar.mobile.module.blog.presenter;

import com.alibaba.fastjson.JSON;
import com.doctorcar.mobile.R;
import com.doctorcar.mobile.common.baserx.RxSubscriber;
import com.doctorcar.mobile.common.commonutils.ToastUitl;
import com.doctorcar.mobile.module.blog.bean.ArticleResult;
import com.doctorcar.mobile.module.blog.contract.ArticleGetContract;
import com.doctorcar.mobile.utils.TLUtil;

/**
 * Created by dd on 2017/5/9.
 */

public class ArticleGetPresenter extends ArticleGetContract.Presenter{

    @Override
    public void getArticleListRequest(String user_id, Integer page, Integer page_size) {

        mRxManage.add(mModel.getArticleList(user_id,page, page_size).subscribe(new RxSubscriber<ArticleResult>(mContext) {
            @Override
            protected void _onNext(ArticleResult articleResult) {
                TLUtil.showLog(JSON.toJSONString(articleResult)+"-----");
                mView.returnArticleListData(articleResult);
            }
            @Override
            protected void _onError(String message) {
                ToastUitl.showToastWithImg(message, R.drawable.ic_apps_black_24dp);
            }
        }));
    }

    @Override
    public void deleteArticleRequest(Integer article_id) {
        mRxManage.add(mModel.deleteArticle(article_id).subscribe(new RxSubscriber<Object>(mContext) {
            @Override
            protected void _onNext(Object o) {
                mView.returnDeleteArticleData();
            }

            @Override
            protected void _onError(String message) {
                ToastUitl.showToastWithImg(message, R.drawable.ic_apps_black_24dp);
            }
        }));
    }
}
