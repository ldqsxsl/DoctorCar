package com.doctorcar.mobile.module.ask.model;

import com.doctorcar.mobile.api.Api;
import com.doctorcar.mobile.api.HostType;
import com.doctorcar.mobile.bean.UploadImageResult;
import com.doctorcar.mobile.common.basebean.BaseRespose;
import com.doctorcar.mobile.common.baserx.RxSchedulers;
import com.doctorcar.mobile.module.ask.bean.BrandModelBean;
import com.doctorcar.mobile.module.ask.contract.AskContract;
import com.doctorcar.mobile.utils.TLUtil;

import java.util.Map;

import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import rx.Observable;
import rx.functions.Func1;

/**
 * Created by dd on 2017/4/6.
 */

public class AskModel implements AskContract.Model {
    @Override
    public Observable<Object> submitAsk(String user_id,Integer brand_id, Integer model_id, String content, String img) {
        return Api.getDefault(HostType.ADD_PROBLEM).submitAsk(user_id,brand_id,model_id,content,img).map(new Func1<BaseRespose<Object>, Object>() {
            @Override
            public Object call(BaseRespose<Object> objectBaseRespose) {
                return objectBaseRespose.data;
            }
        }).compose(RxSchedulers.<Object>io_main());
    }

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
