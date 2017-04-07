package com.doctorcar.mobile.module.ask.contract;

import com.doctorcar.mobile.bean.User;
import com.doctorcar.mobile.common.base.BaseModel;
import com.doctorcar.mobile.common.base.BasePresenter;
import com.doctorcar.mobile.common.base.BaseView;
import com.doctorcar.mobile.module.login.contract.LoginContract;

import rx.Observable;

/**
 * Created by dd on 2017/4/6.
 */

public interface AskContract {
    interface Model extends BaseModel {
        //请求获取新闻
        Observable<Object> submitAsk(String brand_id, String model_id,String content,String img);
    }

    interface View extends BaseView {
        //返回登陆数据
        void returnAskData(Object object);
    }
    abstract static class Presenter extends BasePresenter<AskContract.View, AskContract.Model> {
        //发起获取单条新闻请求
        public abstract void submitAskRequest(String brand_id, String model_id,String content,String img);
    }

}
