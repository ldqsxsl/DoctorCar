package com.doctorcar.mobile.module.login.model;

import com.doctorcar.mobile.bean.Result;
import com.doctorcar.mobile.module.login.contract.LoginContract;

import rx.Observable;

/**
 * Created by dd on 2016/12/14.
 */

public class LoginModel implements LoginContract.Model {
    @Override
    public Observable<Result> getLoginData(String username, String password) {
        return null;
    }
}
