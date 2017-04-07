package com.doctorcar.mobile.module.ask.presenter;

import com.doctorcar.mobile.R;
import com.doctorcar.mobile.common.baserx.RxSubscriber;
import com.doctorcar.mobile.common.commonutils.ToastUitl;
import com.doctorcar.mobile.module.ask.bean.BrandModelBean;
import com.doctorcar.mobile.module.ask.contract.BrandContract;

/**
 * Created by dd on 2017/3/15.
 */

public class BrandPresenter extends BrandContract.Presenter{

    @Override
    public void getBrandRequest() {
        mRxManage.add(mModel.getBrandData().subscribe(new RxSubscriber<BrandModelBean>(mContext) {
            @Override
            protected void _onNext(BrandModelBean brandModelBean) {
                mView.returnBrandData(brandModelBean);
            }

            @Override
            protected void _onError(String message) {
                ToastUitl.showToastWithImg(message, R.drawable.ic_apps_black_24dp);
            }
        }));
    }
}
