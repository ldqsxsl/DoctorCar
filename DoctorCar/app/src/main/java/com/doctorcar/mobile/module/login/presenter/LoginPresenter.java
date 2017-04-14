package com.doctorcar.mobile.module.login.presenter;

import com.doctorcar.mobile.R;
import com.doctorcar.mobile.bean.Result;
import com.doctorcar.mobile.bean.User;
import com.doctorcar.mobile.common.baserx.RxSubscriber;
import com.doctorcar.mobile.common.commonutils.ToastUitl;
import com.doctorcar.mobile.module.login.contract.LoginContract;

/**
 * Created by dd on 2016/12/14.
 */

public class LoginPresenter extends LoginContract.Presenter{
    @Override
    public void getLoginRequest(String username, String password) {

        mRxManage.add(mModel.getLoginData(username,password).subscribe(new RxSubscriber<User>(mContext) {
            @Override
            protected void _onNext(User result) {
                mView.returnLoginData(result);
            }

            @Override
            protected void _onError(String message) {
                ToastUitl.showToastWithImg(message, R.drawable.ic_apps_black_24dp);
            }
        }));
    }
}
