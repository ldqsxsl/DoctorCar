package com.doctorcar.mobile.module.mine.activity;

import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.doctorcar.mobile.R;
import com.doctorcar.mobile.bean.User;
import com.doctorcar.mobile.common.base.BaseActivity;
import com.doctorcar.mobile.module.mine.contract.FeedBackContract;
import com.doctorcar.mobile.module.mine.model.FeedbackModel;
import com.doctorcar.mobile.module.mine.presenter.FeedbackPresenter;
import com.doctorcar.mobile.utils.ImageUtils;
import com.doctorcar.mobile.utils.TLUtil;
import com.doctorcar.mobile.utils.UserUtils;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.MediaType;
import okhttp3.RequestBody;

/**
 * Created by dd on 2017/5/19.
 */

public class FeedbackActivity extends BaseActivity<FeedbackPresenter, FeedbackModel> implements FeedBackContract.View {

    @BindView(R.id.toolbar_title)
    TextView toolbarTitle;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.feedback_et)
    EditText feedbackEt;

    @Override
    public int getLayoutId() {
        return R.layout.feedback_activity;
    }

    @Override
    public void initPresenter() {
        mPresenter.setVM(this, mModel);
    }

    @Override
    public void initView() {
        initToolbar();
    }


    public void initToolbar() {
        toolbarTitle.setText("意见反馈");
        toolbar.inflateMenu(R.menu.ask_menu);
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
                    case R.id.ask_menu_save:
                        submitFeedback();
                        break;
                }
                return true;
            }
        });
    }

    public void submitFeedback(){
        String content = feedbackEt.getText().toString().trim();
        if(TextUtils.isEmpty(content)){
            TLUtil.showToast("请输入内容");
        }else{
            User user = UserUtils.getUser();
            if (user != null){
                mPresenter.addFeedbackRequest(user.getUser_id(),content);
            }else{
                TLUtil.showToast("你还没有登录");
            }

        }
    }

    @Override
    public void returnFeedbackData(Object object) {

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
