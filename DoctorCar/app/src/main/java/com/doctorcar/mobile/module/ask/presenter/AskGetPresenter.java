package com.doctorcar.mobile.module.ask.presenter;

import com.alibaba.fastjson.JSON;
import com.doctorcar.mobile.R;
import com.doctorcar.mobile.common.baserx.RxSubscriber;
import com.doctorcar.mobile.common.commonutils.ToastUitl;
import com.doctorcar.mobile.module.ask.bean.ProblemResult;
import com.doctorcar.mobile.module.ask.contract.AskGetContract;
import com.doctorcar.mobile.utils.TLUtil;

/**
 * Created by dd on 2017/4/12.
 */

public class AskGetPresenter extends AskGetContract.Presenter{

    @Override
    public void getAskRequest(Integer page, Integer page_size) {
        mRxManage.add(mModel.getAsk(page,page_size).subscribe(new RxSubscriber<ProblemResult>(mContext) {
            @Override
            protected void _onNext(ProblemResult result) {
                TLUtil.showLog(JSON.toJSONString(result));
                mView.returnGetAskData(result);
            }

            @Override
            protected void _onError(String message) {
                ToastUitl.showToastWithImg(message, R.drawable.ic_apps_black_24dp);
            }
        }));
    }
}
