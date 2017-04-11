package com.doctorcar.mobile.module.ask.presenter;

import com.doctorcar.mobile.R;
import com.doctorcar.mobile.bean.UploadImageResult;
import com.doctorcar.mobile.bean.User;
import com.doctorcar.mobile.common.baserx.RxSubscriber;
import com.doctorcar.mobile.common.commonutils.ToastUitl;
import com.doctorcar.mobile.module.ask.contract.AskContract;

import java.util.Map;

import okhttp3.RequestBody;
import okhttp3.ResponseBody;

/**
 * Created by dd on 2017/4/6.
 */

public class AskPresenter extends AskContract.Presenter {

    @Override
    public void submitAskRequest(String user_id,Integer brand_id, Integer model_id, String content, String img) {
        mRxManage.add(mModel.submitAsk(user_id,brand_id,model_id,content,img).subscribe(new RxSubscriber<Object>(mContext) {
            @Override
            protected void _onNext(Object result) {
                mView.returnAskData(result);
            }

            @Override
            protected void _onError(String message) {
                ToastUitl.showToastWithImg(message, R.drawable.ic_apps_black_24dp);
            }
        }));
    }

    @Override
    public void uploadImageRequest(Map<String, RequestBody> map) {
        mRxManage.add(mModel.uploadImage(map).subscribe(new RxSubscriber<UploadImageResult>(mContext) {
            @Override
            protected void _onNext(UploadImageResult o) {
                mView.returnUploadImageData(o);
            }

            @Override
            protected void _onError(String message) {
                ToastUitl.showToastWithImg(message, R.drawable.ic_apps_black_24dp);
            }
        }));
    }
}
