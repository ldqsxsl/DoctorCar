package com.doctorcar.mobile.module.login.activity;

import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.doctorcar.mobile.DemoActivity;
import com.doctorcar.mobile.R;
import com.doctorcar.mobile.bean.User;
import com.doctorcar.mobile.common.base.BaseActivity;
import com.doctorcar.mobile.module.login.contract.LoginContract;
import com.doctorcar.mobile.module.login.model.LoginModel;
import com.doctorcar.mobile.module.login.presenter.LoginPresenter;
import com.doctorcar.mobile.module.register.activity.RegisterActivity;
import com.doctorcar.mobile.utils.ImageUtils;
import com.doctorcar.mobile.utils.TLUtil;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.MediaType;
import okhttp3.RequestBody;

/**
 * Created by dd on 2016/12/9.
 */

public class LoginActivity extends BaseActivity<LoginPresenter, LoginModel> implements LoginContract.View {


    @BindView(R.id.toolbar_title)
    TextView toolbarTitle;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.login_username_et)
    EditText loginUsernameEt;
    @BindView(R.id.login_password_et)
    EditText loginPasswordEt;
    @BindView(R.id.login_submit_bt)
    Button loginSubmitBt;

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
        initToolbar();
    }

    public void initToolbar(){
        toolbarTitle.setText("用户登陆");
        toolbar.inflateMenu(R.menu.login_menu);
        toolbar.setNavigationIcon(ContextCompat.getDrawable(this, R.drawable.ic_navigate_before_black_24dp));
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.register_menu:
                        startActivity(RegisterActivity.class);
                        break;
                }
                return true;
            }
        });
    }

    @Override
    public void returnLoginData(User user) {
        TLUtil.showToast("登陆成功");
        finish();
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


    @OnClick(R.id.login_submit_bt)
    public void onClick() {
        String username = loginUsernameEt.getText().toString().trim();
        String password = loginPasswordEt.getText().toString().trim();
        if (TextUtils.isEmpty(username)) {
            TLUtil.showToast("请输入用户名");
        } else if (TextUtils.isEmpty(password)) {
            TLUtil.showToast("请输入用户密码");
        } else {
            mPresenter.getLoginRequest(username, password);
        }
    }
}
