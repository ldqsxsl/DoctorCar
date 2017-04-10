package com.doctorcar.mobile.module.ask.contract;

import com.doctorcar.mobile.bean.UploadImageResult;
import com.doctorcar.mobile.bean.User;
import com.doctorcar.mobile.common.base.BaseModel;
import com.doctorcar.mobile.common.base.BasePresenter;
import com.doctorcar.mobile.common.base.BaseView;
import com.doctorcar.mobile.module.login.contract.LoginContract;

import java.util.Map;

import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import rx.Observable;

/**
 * Created by dd on 2017/4/6.
 */

public interface AskContract {
    interface Model extends BaseModel {

        Observable<Object> submitAsk(Integer brand_id, Integer model_id,String problem_content,String problem_img);
        Observable<UploadImageResult> uploadImage(Map<String, RequestBody> map);
    }

    interface View extends BaseView {

        void returnAskData(Object object);
        void returnUploadImageData(UploadImageResult object);
    }
    abstract static class Presenter extends BasePresenter<AskContract.View, AskContract.Model> {
        public abstract void submitAskRequest(Integer brand_id, Integer model_id,String content,String img);
        public abstract void uploadImageRequest(Map<String, RequestBody> map);
    }

}
