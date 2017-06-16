package com.doctorcar.mobile.module.ask.presenter;

import com.doctorcar.mobile.R;
import com.doctorcar.mobile.common.baserx.RxSubscriber;
import com.doctorcar.mobile.common.commonutils.ToastUitl;
import com.doctorcar.mobile.module.ask.bean.ProblemResult;
import com.doctorcar.mobile.module.ask.contract.WaitSolveProblemContact;

/**
 * Created by dd on 2017/5/18.
 */

public class WaitSolveProblemPresenter extends WaitSolveProblemContact.Presenter{

    @Override
    public void getNoSolveProblemRequest(int page) {
        mRxManage.add(mModel.getNoSolveProblemList(page).subscribe(new RxSubscriber<ProblemResult>(mContext) {
            @Override
            protected void _onNext(ProblemResult problemResult) {
                mView.returnNoSolveProblemListData(problemResult);
            }

            @Override
            protected void _onError(String message) {
                ToastUitl.showToastWithImg(message, R.drawable.ic_apps_black_24dp);
            }
        }));
    }
}
