package com.doctorcar.mobile.module.ask.contract;

import com.doctorcar.mobile.bean.User;
import com.doctorcar.mobile.common.base.BaseModel;
import com.doctorcar.mobile.common.base.BasePresenter;
import com.doctorcar.mobile.common.base.BaseView;
import com.doctorcar.mobile.module.ask.bean.BrandModelBean;

import rx.Observable;

/**
 * Created by dd on 2017/3/15.
 */

public interface BrandContract {
    interface Model extends BaseModel {
        //请求获取新闻
        Observable<BrandModelBean> getBrandData();
    }

    interface View extends BaseView {
        //返回登陆数据
        void returnBrandData(BrandModelBean brandModelBean);
    }
    abstract static class Presenter extends BasePresenter<BrandContract.View, BrandContract.Model> {
        //发起获取单条新闻请求
        public abstract void getBrandRequest();
    }

}
