package com.doctorcar.mobile.module.blog.presenter;

import com.doctorcar.mobile.R;
import com.doctorcar.mobile.common.baserx.RxSubscriber;
import com.doctorcar.mobile.common.commonutils.ToastUitl;
import com.doctorcar.mobile.module.blog.contract.ArticleContract;

/**
 * Created by dd on 2017/5/9.
 */

public class ArticlePresenter extends ArticleContract.Presenter{

    @Override
    public void addArticleRequest(String user_id, String article_title, String article_content, String article_privacy) {

        mRxManage.add(mModel.addArticle(user_id,article_title,article_content,article_privacy).subscribe(new RxSubscriber<Object>(mContext) {
            @Override
            protected void _onNext(Object o) {
                mView.returnArticleData(o);
            }
            @Override
            protected void _onError(String message) {
                ToastUitl.showToastWithImg(message, R.drawable.ic_apps_black_24dp);
            }
        }));

    }
}
