package com.doctorcar.mobile.module.ask.activity;

import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.doctorcar.mobile.R;
import com.doctorcar.mobile.bean.User;
import com.doctorcar.mobile.common.base.BaseActivity;
import com.doctorcar.mobile.common.commonutils.TimeUtil;
import com.doctorcar.mobile.common.interf.OnItemClickViewListener;
import com.doctorcar.mobile.module.ask.adapter.AnswerCommentAdapter;
import com.doctorcar.mobile.module.ask.bean.AnswerCommentBean;
import com.doctorcar.mobile.module.ask.bean.AnswerCommentResult;
import com.doctorcar.mobile.module.ask.bean.ProblemBean;
import com.doctorcar.mobile.module.ask.contract.SearchMyAnswerContract;
import com.doctorcar.mobile.module.ask.model.SearchMyAnswerModel;
import com.doctorcar.mobile.module.ask.presenter.SearchMyAnswerPresenter;
import com.doctorcar.mobile.utils.TLUtil;
import com.doctorcar.mobile.utils.UserUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by dd on 2017/5/17.
 */

public class SearchMyAnswerActivity extends BaseActivity<SearchMyAnswerPresenter, SearchMyAnswerModel> implements OnItemClickViewListener,SearchMyAnswerContract.View {

    @BindView(R.id.toolbar_title)
    TextView toolbarTitle;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.answer_comment_rv)
    RecyclerView answerCommentRv;
    @BindView(R.id.answer_comment_srl)
    SwipeRefreshLayout answerCommentSrl;
    @BindView(R.id.answer_comment_content_et)
    EditText answerCommentContentEt;
    @BindView(R.id.answer_comment_submit_bt)
    TextView answerCommentSubmitBt;
    @BindView(R.id.answer_comment_rl)
    RelativeLayout answerCommentRl;
    private ProblemBean problemBean;
    private AnswerCommentResult answer;

    private User user;
    private TextView timeTv;
    private TextView praiseTv;
    private TextView contentTv;
    private TextView userNameTv ;
    private TextView userDescriptionTv;

    private AnswerCommentAdapter answerCommentAdapter;
    private List<AnswerCommentBean> answerCommentBeenList = new ArrayList<AnswerCommentBean>();

    @Override
    public int getLayoutId() {
        return R.layout.answer_comment_activity;
    }

    @Override
    public void initPresenter() {
        mPresenter.setVM(this, mModel);
    }

    @Override
    public void initView() {
        initToolbar();
        problemBean = (ProblemBean) getIntent().getExtras().getSerializable("data");
        answerCommentAdapter = new AnswerCommentAdapter(this, answerCommentBeenList);
        answerCommentAdapter.setOnRecyclerViewListener(this);

        answerCommentSrl.setOnRefreshListener(mOnRefreshListener);
        answerCommentRv.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));// 布局管理器。
        answerCommentRv.setHasFixedSize(true);// 如果Item够简单，高度是确定的，打开FixSize将提高性能。
        answerCommentRv.setItemAnimator(new DefaultItemAnimator());// 设置Item默认动画，加也行，不加也行。
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(this, DividerItemDecoration.VERTICAL);
        dividerItemDecoration.setDrawable(ContextCompat.getDrawable(this, R.drawable.line));
        answerCommentRv.addItemDecoration(dividerItemDecoration);// 添加分割线。
        View header = LayoutInflater.from(this).inflate(R.layout.answer_comment_header_layout, answerCommentRv, false);
        TextView problemTv = (TextView) header.findViewById(R.id.answer_comment_header_layout_problem_tv);
        timeTv = (TextView) header.findViewById(R.id.answer_comment_header_layout_time_tv);
        praiseTv = (TextView) header.findViewById(R.id.answer_comment_header_layout_praise_tv);
        contentTv = (TextView) header.findViewById(R.id.answer_comment_header_layout_content_tv);
        userNameTv = (TextView) header.findViewById(R.id.answer_comment_header_name_tv);
        userDescriptionTv = (TextView) header.findViewById(R.id.answer_comment_header_description_tv);
        problemTv.setText(problemBean.getProblem_content()+"");


        answerCommentAdapter.setHeaderView(header);
        answerCommentRv.setAdapter(answerCommentAdapter);
        user = UserUtils.getUser();
        mPresenter.getMyCommentAnswerRequest(problemBean.getProblem_id(), user.getUser_id());
    }

    public void initToolbar() {
        toolbarTitle.setText("评论(0)");
        toolbar.inflateMenu(R.menu.answer_menu);
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
                        break;
                }
                return true;
            }
        });
    }

    /**
     * 刷新监听。
     */
    private SwipeRefreshLayout.OnRefreshListener mOnRefreshListener = new SwipeRefreshLayout.OnRefreshListener() {
        @Override
        public void onRefresh() {
            answerCommentRv.postDelayed(new Runnable() {
                @Override
                public void run() {
                    mPresenter.getMyCommentAnswerRequest(problemBean.getProblem_id(), user.getUser_id());
                }
            }, 2000);
        }
    };

    @Override
    public void returnMyAnswerCommentListData(AnswerCommentResult answerCommentResult) {
        TLUtil.showLog(JSON.toJSONString(answerCommentResult));
        answer = answerCommentResult;
        timeTv.setText(TimeUtil.formatData(TimeUtil.dateFormatYMDofChinese,Long.parseLong(answerCommentResult.getAnswer_time())));
        praiseTv.setText(answerCommentResult.getAnswer_praise_number()+"");
        contentTv.setText(answerCommentResult.getAnswer_content()+"");
        userNameTv.setText(answerCommentResult.getUser_phone()+"");
        userDescriptionTv.setText(answerCommentResult.getUser_description()+"");
        List<AnswerCommentBean> list = answerCommentResult.getList();
        answerCommentBeenList.clear();
        answerCommentBeenList.addAll(list);
        answerCommentAdapter.addDatas(answerCommentBeenList);
    }

    @Override
    public void returnAnswerCommentData(Object object) {
        TLUtil.showToast("添加成功!");
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



    @OnClick(R.id.answer_comment_submit_bt)
    public void onClick() {
        String string = answerCommentContentEt.getText().toString().trim();
        if (TextUtils.isEmpty(string)){
            TLUtil.showToast("请输入内容");
        }else{
            User user = UserUtils.getUser();
            mPresenter.submitAnswerCommentRequest(answer.getAnswer_id(),user.getUser_id(),string);
        }

    }

    @Override
    public void onItemClick(int position, Object object) {

    }
}
