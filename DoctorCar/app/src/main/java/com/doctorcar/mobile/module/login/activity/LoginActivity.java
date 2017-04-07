package com.doctorcar.mobile.module.login.activity;

import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.doctorcar.mobile.DemoActivity;
import com.doctorcar.mobile.R;
import com.doctorcar.mobile.bean.Result;
import com.doctorcar.mobile.bean.User;
import com.doctorcar.mobile.common.base.BaseActivity;
import com.doctorcar.mobile.module.login.contract.LoginContract;
import com.doctorcar.mobile.module.login.model.LoginModel;
import com.doctorcar.mobile.module.login.presenter.LoginPresenter;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import rx.Observable;
import rx.Subscriber;
import rx.subjects.Subject;

/**
 * Created by dd on 2016/12/9.
 */

public class LoginActivity extends BaseActivity<LoginPresenter, LoginModel> implements LoginContract.View {


    @BindView(R.id.login_username)
    EditText loginUsername;
    @BindView(R.id.login_password)
    EditText loginPassword;
    @BindView(R.id.login_submit)
    Button loginSubmit;

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
    public void returnLoginData(User user) {
        startActivity(DemoActivity.class);

        Toast.makeText(this, user.getId()+"+++++++++"+user.getLoginName(), Toast.LENGTH_SHORT).show();
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



    @OnClick(R.id.login_submit)
    public void onClick() {
        String username = loginUsername.getText().toString().trim();
        String password = loginPassword.getText().toString().trim();
        if (TextUtils.isEmpty(username)){
            Toast.makeText(this, "input username", Toast.LENGTH_SHORT).show();
        }else if(TextUtils.isEmpty(password)){
            Toast.makeText(this, "input password", Toast.LENGTH_SHORT).show();
        }else{
            mPresenter.getLoginRequest(username, password);
        }

    }
}
