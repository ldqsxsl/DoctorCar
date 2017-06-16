package com.doctorcar.mobile.module.ask.presenter;

import com.doctorcar.mobile.R;
import com.doctorcar.mobile.common.baserx.RxSubscriber;
import com.doctorcar.mobile.common.commonutils.ToastUitl;
import com.doctorcar.mobile.module.ask.bean.AnswerCommentResult;
import com.doctorcar.mobile.module.ask.bean.ProblemResult;
import com.doctorcar.mobile.module.ask.contract.MyFocusProblemContact;

/**
 * Created by dd on 2017/5/18.
 */

public class MyFocusProblemPresenter extends MyFocusProblemContact.Presenter{

    @Override
    public void getMyFocusProblemRequest(String user_id, int page, int page_size) {

        mRxManage.add(mModel.getMyFocusProblemList(user_id,page,page_size).subscribe(new RxSubscriber<ProblemResult>(mContext) {
            @Override
            protected void _onNext(ProblemResult problemResult) {
                mView.returnMyFocusProblemListData(problemResult);
            }

            @Override
            protected void _onError(String message) {
                ToastUitl.showToastWithImg(message, R.drawable.ic_apps_black_24dp);
            }
        }));
    }
}
