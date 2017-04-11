package com.doctorcar.mobile.module.register.activity;

import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.doctorcar.mobile.R;
import com.doctorcar.mobile.common.base.BaseActivity;
import com.doctorcar.mobile.module.register.bean.RegisterResult;
import com.doctorcar.mobile.module.register.contract.RegisterContract;
import com.doctorcar.mobile.module.register.model.RegisterModel;
import com.doctorcar.mobile.module.register.presenter.RegisterPresenter;
import com.doctorcar.mobile.utils.TLUtil;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by dd on 2017/4/11.
 */

public class RegisterActivity extends BaseActivity<RegisterPresenter, RegisterModel> implements RegisterContract.View{

    @BindView(R.id.toolbar_title)
    TextView toolbarTitle;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.register_username_et)
    EditText registerUsernameEt;
    @BindView(R.id.register_password_et)
    EditText registerPasswordEt;
    @BindView(R.id.register_submit_bt)
    Button registerSubmitBt;

    @Override
    public int getLayoutId() {
        return R.layout.register_activity;
    }

    @Override
    public void initPresenter() {
        mPresenter.setVM(this,mModel);
    }

    @Override
    public void initView() {
        initToolbar();
    }

    public void initToolbar(){
        toolbarTitle.setText("用户注册");
        toolbar.inflateMenu(R.menu.login_menu);
        toolbar.setNavigationIcon(ContextCompat.getDrawable(this, R.drawable.ic_navigate_before_black_24dp));
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    public void register(){
        String userName = registerUsernameEt.getText().toString().trim();
        String userPassword = registerPasswordEt.getText().toString().trim();
        if (TextUtils.isEmpty(userName)){
            TLUtil.showToast("请输入用户名");
        }else if(TextUtils.isEmpty(userPassword)){
            TLUtil.showToast("请输入用户密码");
        }else{
            mPresenter.registerRequest(userName,userPassword);
        }
    }

    @OnClick(R.id.register_submit_bt)
    public void onClick() {
        register();
    }

    @Override
    public void returnRegisterData(RegisterResult registerResult) {
        TLUtil.showToast("注册成功");
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
