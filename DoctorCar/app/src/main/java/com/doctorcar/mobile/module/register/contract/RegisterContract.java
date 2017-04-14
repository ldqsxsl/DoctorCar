package com.doctorcar.mobile.module.register.contract;

import com.doctorcar.mobile.bean.User;
import com.doctorcar.mobile.common.base.BaseModel;
import com.doctorcar.mobile.common.base.BasePresenter;
import com.doctorcar.mobile.common.base.BaseView;
import com.doctorcar.mobile.module.register.bean.RegisterResult;

import rx.Observable;

/**
 * Created by dd on 2016/12/14.
 */

public interface RegisterContract {
    interface Model extends BaseModel {
        Observable<RegisterResult> register(String username, String password);
    }

    interface View extends BaseView {
        void returnRegisterData(RegisterResult registerResult);
    }
    abstract static class Presenter extends BasePresenter<View, Model> {
        public abstract void registerRequest(String username,String password);
    }
}
