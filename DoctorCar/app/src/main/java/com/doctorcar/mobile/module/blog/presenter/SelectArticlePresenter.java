package com.doctorcar.mobile.module.blog.presenter;

import com.alibaba.fastjson.JSON;
import com.doctorcar.mobile.R;
import com.doctorcar.mobile.common.baserx.RxSubscriber;
import com.doctorcar.mobile.common.commonutils.ToastUitl;
import com.doctorcar.mobile.module.blog.bean.ArticleResult;
import com.doctorcar.mobile.module.blog.contract.SelectBlogContract;
import com.doctorcar.mobile.utils.TLUtil;

/**
 * Created by dd on 2017/6/16.
 */

public class SelectArticlePresenter extends SelectBlogContract.Presenter{

    @Override
    public void getSelectArticleListRequest(Integer page) {
        mRxManage.add(mModel.getSelectArticleList(page).subscribe(new RxSubscriber<ArticleResult>(mContext) {
            @Override
            protected void _onNext(ArticleResult articleResult) {
                TLUtil.showLog(JSON.toJSONString(articleResult)+"-----");
                mView.returnSelectArticleListData(articleResult);
            }
            @Override
            protected void _onError(String message) {
                ToastUitl.showToastWithImg(message, R.drawable.ic_apps_black_24dp);
            }
        }));
    }
}
