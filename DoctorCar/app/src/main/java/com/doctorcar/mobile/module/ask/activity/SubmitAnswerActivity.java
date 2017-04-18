package com.doctorcar.mobile.module.ask.activity;

import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.doctorcar.mobile.R;
import com.doctorcar.mobile.bean.User;
import com.doctorcar.mobile.common.base.BaseActivity;
import com.doctorcar.mobile.common.commonutils.DisplayUtil;
import com.doctorcar.mobile.module.ask.bean.AnswerResult;
import com.doctorcar.mobile.module.ask.bean.ProblemBean;
import com.doctorcar.mobile.module.ask.contract.AnswerContract;
import com.doctorcar.mobile.module.ask.model.AnswerModel;
import com.doctorcar.mobile.module.ask.presenter.AnswerPresenter;
import com.doctorcar.mobile.module.login.activity.LoginActivity;
import com.doctorcar.mobile.utils.SPUtils;
import com.doctorcar.mobile.utils.TLUtil;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by dd on 2017/4/17.
 */

public class SubmitAnswerActivity extends BaseActivity<AnswerPresenter, AnswerModel> implements AnswerContract.View {

    @BindView(R.id.toolbar_title)
    TextView toolbarTitle;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.submit_answer_et)
    EditText submitAnswerEt;

    private ProblemBean problemBean;

    @Override
    public int getLayoutId() {
        return R.layout.submit_answer_activity;
    }

    @Override
    public void initPresenter() {
        mPresenter.setVM(this, mModel);
    }

    @Override
    public void initView() {
        problemBean = (ProblemBean) getIntent().getExtras().getSerializable("data");
        initToolbar();
    }

    public void initToolbar() {
        toolbarTitle.setText("回答");
        toolbar.inflateMenu(R.menu.submit_answer_menu);
        toolbar.setNavigationIcon(ContextCompat.getDrawable(this, R.drawable.ic_navigate_before_black_24dp));
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
//        TextView rightTv = (TextView) toolbar.getMenu().getItem(0).getActionView();
//        rightTv.setText("发布");
//        rightTv.setTextSize(DisplayUtil.sp2px(16));
        toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.submit_answer_menu:
                        submitAnswer();
//                        TextView textView = (TextView) item.getActionView();
//                        textView.setText("fa");
                        break;
                }
                return true;
            }
        });
    }

    public void submitAnswer(){
        String content = submitAnswerEt.getText().toString().trim();
        Integer problem_id = -1;
        if (problemBean != null)
            problem_id = problemBean.getProblem_id();
        String str = SPUtils.getParams("user","");
        if (!TextUtils.isEmpty(str)){
            User user = JSON.parseObject(str,User.class);
            if (user != null){
                if (TextUtils.isEmpty(content)){
                    TLUtil.showToast("请输入内容");
                }else{
                    TLUtil.showLog(user.getUser_id()+"  ooooooooooooooooo");
                    mPresenter.submitAnswerRequest(user.getUser_id(),problem_id,content);
                }

            }else {
                startActivity(LoginActivity.class);
            }
        }
    }

    @Override
    public void returnAnswerData(Object object) {

    }

    @Override
    public void returnAnswerListData(AnswerResult answerResult) {

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
