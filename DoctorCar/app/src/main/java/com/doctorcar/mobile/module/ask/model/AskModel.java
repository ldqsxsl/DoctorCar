package com.doctorcar.mobile.module.ask.model;

import com.doctorcar.mobile.api.Api;
import com.doctorcar.mobile.api.HostType;
import com.doctorcar.mobile.common.basebean.BaseRespose;
import com.doctorcar.mobile.common.baserx.RxSchedulers;
import com.doctorcar.mobile.module.ask.bean.BrandModelBean;
import com.doctorcar.mobile.module.ask.contract.AskContract;
import com.doctorcar.mobile.utils.TLUtil;

import rx.Observable;
import rx.functions.Func1;

/**
 * Created by dd on 2017/4/6.
 */

public class AskModel implements AskContract.Model {
    @Override
    public Observable<Object> submitAsk(String brand_id, String model_id, String content, String img) {
        return Api.getDefault(HostType.ADD_PROBLEM).submitAsk(Api.getCacheControl(),brand_id,model_id,content,img).map(new Func1<BaseRespose<Object>, Object>() {
            @Override
            public Object call(BaseRespose<Object> objectBaseRespose) {
                return objectBaseRespose.data;
            }
        }).compose(RxSchedulers.<Object>io_main());
    }
}
