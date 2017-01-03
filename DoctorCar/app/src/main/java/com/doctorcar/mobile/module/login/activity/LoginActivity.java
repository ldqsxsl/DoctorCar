package com.doctorcar.mobile.module.login.activity;

import com.doctorcar.mobile.R;
import com.doctorcar.mobile.bean.Result;
import com.doctorcar.mobile.common.base.BaseActivity;
import com.doctorcar.mobile.module.login.contract.LoginContract;
import com.doctorcar.mobile.module.login.model.LoginModel;
import com.doctorcar.mobile.module.login.presenter.LoginPresenter;

/**
 * Created by dd on 2016/12/9.
 */

public class LoginActivity extends BaseActivity<LoginPresenter,LoginModel> implements LoginContract.View{

    @Override
    public int getLayoutId() {
        return R.layout.activity_login;
    }

    @Override
    public void initPresenter() {
        mPresenter.setVM(this, mModel);
    }

    @Override
    public void initView() {

    }

    @Override
    public void returnLoginData(Result result) {

    }

    @Override
    public void showLoading(String title) {

    }

    @Override
    public void stopLoading() {

    }

    @Override
    public void showErrorTip(String msg) {

    }
}
