package com.doctorcar.mobile.module.ask.presenter;

import com.doctorcar.mobile.R;
import com.doctorcar.mobile.common.baserx.RxSubscriber;
import com.doctorcar.mobile.common.commonutils.ToastUitl;
import com.doctorcar.mobile.module.ask.bean.ProblemResult;
import com.doctorcar.mobile.module.ask.contract.MyAskContract;

/**
 * Created by dd on 2017/5/10.
 */

public class MyAskPresenter extends MyAskContract.Presenter{

    @Override
    public void getMyAskRequest(String user_id) {

        mRxManage.add(mModel.getMyAsk(user_id).subscribe(new RxSubscriber<ProblemResult>(mContext) {
            @Override
            protected void _onNext(ProblemResult result) {
                mView.returnGetMyAskData(result);
            }

            @Override
            protected void _onError(String message) {
                ToastUitl.showToastWithImg(message, R.drawable.ic_apps_black_24dp);
            }
        }));

    }
}
