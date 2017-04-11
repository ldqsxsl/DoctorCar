package com.doctorcar.mobile.module.register.presenter;


import com.doctorcar.mobile.R;
import com.doctorcar.mobile.common.baserx.RxSubscriber;
import com.doctorcar.mobile.common.commonutils.ToastUitl;
import com.doctorcar.mobile.module.register.bean.RegisterResult;
import com.doctorcar.mobile.module.register.contract.RegisterContract;

/**
 * Created by dd on 2016/12/14.
 */

public class RegisterPresenter extends RegisterContract.Presenter {
    @Override
    public void registerRequest(String username, String password) {
        mRxManage.add(mModel.register(username, password).subscribe(new RxSubscriber<RegisterResult>(mContext) {
            @Override
            protected void _onNext(RegisterResult registerResult) {
                mView.returnRegisterData(registerResult);
            }
            @Override
            protected void _onError(String message) {
                ToastUitl.showToastWithImg(message, R.drawable.ic_apps_black_24dp);
            }
        }));
    }

}
