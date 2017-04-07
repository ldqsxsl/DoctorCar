package com.doctorcar.mobile.module.common.presenter;

import com.doctorcar.mobile.R;
import com.doctorcar.mobile.bean.User;
import com.doctorcar.mobile.common.baserx.RxSubscriber;
import com.doctorcar.mobile.common.commonutils.ToastUitl;
import com.doctorcar.mobile.module.common.contract.UploadImageContract;
import com.doctorcar.mobile.module.login.contract.LoginContract;

import java.util.Map;

import okhttp3.ResponseBody;

/**
 * Created by dd on 2016/12/14.
 */

public class UploadImagePresenter extends UploadImageContract.Presenter{
    @Override
    public void uploadImageRequest(Map<String, ResponseBody> map) {
        mRxManage.add(mModel.uploadImage(map).subscribe(new RxSubscriber<Object>(mContext) {
            @Override
            protected void _onNext(Object o) {
                mView.returnUploadImageData(o);
            }

            @Override
            protected void _onError(String message) {
                ToastUitl.showToastWithImg(message, R.drawable.ic_apps_black_24dp);
            }
        }));
    }
}
