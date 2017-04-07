package com.doctorcar.mobile.module.common.contract;

import com.doctorcar.mobile.common.base.BaseModel;
import com.doctorcar.mobile.common.base.BasePresenter;
import com.doctorcar.mobile.common.base.BaseView;

import java.util.Map;

import okhttp3.ResponseBody;
import rx.Observable;

/**
 * Created by dd on 2017/4/6.
 */

public interface UploadImageContract {
    interface Model extends BaseModel {
        //请求获取新闻
        Observable<Object> uploadImage(Map<String, ResponseBody> map);
    }

    interface View extends BaseView {
        //返回登陆数据
        void returnUploadImageData(Object object);
    }
    abstract static class Presenter extends BasePresenter<UploadImageContract.View, UploadImageContract.Model> {
        //发起获取单条新闻请求
        public abstract void uploadImageRequest(Map<String, ResponseBody> map);
    }

}
