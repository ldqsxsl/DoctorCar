package com.doctorcar.mobile.module.ask.presenter;

import com.doctorcar.mobile.R;
import com.doctorcar.mobile.bean.User;
import com.doctorcar.mobile.common.baserx.RxSubscriber;
import com.doctorcar.mobile.common.commonutils.ToastUitl;
import com.doctorcar.mobile.module.ask.contract.AskContract;

/**
 * Created by dd on 2017/4/6.
 */

public class AskPresenter extends AskContract.Presenter {

    @Override
    public void submitAskRequest(String brand_id, String model_id, String content, String img) {
        mRxManage.add(mModel.submitAsk(brand_id,model_id,content,img).subscribe(new RxSubscriber<Object>(mContext) {
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
}
