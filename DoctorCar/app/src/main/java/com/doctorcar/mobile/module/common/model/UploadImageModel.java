package com.doctorcar.mobile.module.common.model;

import com.doctorcar.mobile.api.Api;
import com.doctorcar.mobile.api.HostType;
import com.doctorcar.mobile.bean.UploadImageResult;
import com.doctorcar.mobile.common.basebean.BaseRespose;
import com.doctorcar.mobile.common.baserx.RxSchedulers;
import com.doctorcar.mobile.module.ask.contract.AskContract;
import com.doctorcar.mobile.module.common.contract.UploadImageContract;

import java.util.Map;

import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import rx.Observable;
import rx.functions.Func1;

/**
 * Created by dd on 2017/4/6.
 */

public class UploadImageModel implements UploadImageContract.Model {
    @Override
    public Observable<UploadImageResult> uploadImage(Map<String, RequestBody> map) {
        return Api.getDefault(HostType.UPLOAD).uploadImage(map).map(new Func1<BaseRespose<UploadImageResult>, UploadImageResult>() {
            @Override
            public UploadImageResult call(BaseRespose<UploadImageResult> objectBaseRespose) {
                return objectBaseRespose.data;
            }
        }).compose(RxSchedulers.<UploadImageResult>io_main());
    }
}
