package com.doctorcar.mobile.module.register.model;

import com.doctorcar.mobile.api.Api;
import com.doctorcar.mobile.api.HostType;
import com.doctorcar.mobile.bean.User;
import com.doctorcar.mobile.common.basebean.BaseRespose;
import com.doctorcar.mobile.common.baserx.RxSchedulers;
import com.doctorcar.mobile.module.login.contract.LoginContract;
import com.doctorcar.mobile.module.register.bean.RegisterResult;
import com.doctorcar.mobile.module.register.contract.RegisterContract;
import com.doctorcar.mobile.utils.TLUtil;

import rx.Observable;
import rx.Subscriber;
import rx.functions.Action0;
import rx.functions.Action1;
import rx.functions.Func1;


/**
 * Created by dd on 2016/12/14.
 */

public class RegisterModel implements RegisterContract.Model {
    @Override
    public Observable<RegisterResult> register(String username, String password) {
        return Api.getDefault(HostType.REGISTER).register(username, password).map(new Func1<BaseRespose<RegisterResult>, RegisterResult>() {
            @Override
            public RegisterResult call(BaseRespose<RegisterResult> userBaseRespose) {
                TLUtil.showLog(userBaseRespose.toString() + "----------------");
                return userBaseRespose.data;
            }
        }).compose(RxSchedulers.<RegisterResult>io_main());
    }
}
