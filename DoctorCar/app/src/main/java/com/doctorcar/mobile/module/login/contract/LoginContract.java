package com.doctorcar.mobile.module.login.contract;

import com.doctorcar.mobile.bean.Result;
import com.doctorcar.mobile.bean.User;
import com.doctorcar.mobile.common.base.BaseModel;
import com.doctorcar.mobile.common.base.BasePresenter;
import com.doctorcar.mobile.common.base.BaseView;

import rx.Observable;

/**
 * Created by dd on 2016/12/14.
 */

public interface LoginContract {
    interface Model extends BaseModel {
        //请求获取新闻
        Observable<User> getLoginData(String username, String password);
    }

    interface View extends BaseView {
        //返回登陆数据
            void returnLoginData(User user);
    }
    abstract static class Presenter extends BasePresenter<View, Model> {
        //发起获取单条新闻请求
        public abstract void getLoginRequest(String username,String password);
    }
}
