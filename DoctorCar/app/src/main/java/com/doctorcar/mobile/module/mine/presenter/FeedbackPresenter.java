package com.doctorcar.mobile.module.mine.presenter;

import com.doctorcar.mobile.R;
import com.doctorcar.mobile.common.baserx.RxSubscriber;
import com.doctorcar.mobile.common.commonutils.ToastUitl;
import com.doctorcar.mobile.module.mine.contract.FeedBackContract;

/**
 * Created by dd on 2017/5/19.
 */

public class FeedbackPresenter extends FeedBackContract.Presenter{

    @Override
    public void addFeedbackRequest(String user_id, String feedback_content) {

        mRxManage.add(mModel.addFeedback(user_id, feedback_content).subscribe(new RxSubscriber<Object>(mContext) {
            @Override
            protected void _onNext(Object o) {
                mView.returnFeedbackData(o);
            }

            @Override
            protected void _onError(String message) {
                ToastUitl.showToastWithImg(message, R.drawable.ic_apps_black_24dp);
            }
        }));
    }
}
