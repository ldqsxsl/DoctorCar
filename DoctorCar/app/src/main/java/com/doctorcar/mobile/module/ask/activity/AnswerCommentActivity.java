package com.doctorcar.mobile.module.ask.activity;

import android.net.Uri;
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
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.doctorcar.mobile.R;
import com.doctorcar.mobile.bean.User;
import com.doctorcar.mobile.common.base.BaseActivity;
import com.doctorcar.mobile.common.interf.OnItemClickViewListener;
import com.doctorcar.mobile.module.ask.adapter.AnswerCommentAdapter;
import com.doctorcar.mobile.module.ask.bean.AnswerBean;
import com.doctorcar.mobile.module.ask.bean.AnswerCommentBean;
import com.doctorcar.mobile.module.ask.bean.AnswerCommentResult;
import com.doctorcar.mobile.module.ask.bean.ProblemBean;
import com.doctorcar.mobile.module.ask.contract.AnswerCommentContract;
import com.doctorcar.mobile.module.ask.model.AnswerCommentModel;
import com.doctorcar.mobile.module.ask.presenter.AnswerCommentPresenter;
import com.doctorcar.mobile.module.login.activity.LoginActivity;
import com.doctorcar.mobile.utils.ListUtils;
import com.doctorcar.mobile.utils.SPUtils;
import com.doctorcar.mobile.utils.TLUtil;
import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.appindexing.Thing;
import com.google.android.gms.common.api.GoogleApiClient;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by dd on 2017/4/17.
 */

public class AnswerCommentActivity extends BaseActivity<AnswerCommentPresenter, AnswerCommentModel> implements OnItemClickViewListener, AnswerCommentContract.View {

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

    private AnswerBean answerBean;
    private ProblemBean problemBean;

    private AnswerCommentAdapter answerCommentAdapter;
    private List<AnswerCommentBean> answerCommentBeenList = new ArrayList<AnswerCommentBean>();
    private Integer page = 0;
    private boolean isLoadMore = false;
    private boolean isLastPage = false;


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
        answerBean = (AnswerBean) getIntent().getExtras().getSerializable("data");
        problemBean = (ProblemBean) getIntent().getExtras().getSerializable("data1");

        initToolbar();

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
        problemTv.setText(problemBean.getProblem_content()+"");
//        TextView answer = (TextView) header.findViewById(R.id.answer_add_tv);
//        answer.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Bundle bundle = new Bundle();
//                bundle.putSerializable("data", answerBean);
//                startActivity(SubmitAnswerActivity.class, bundle);
//            }
//        });
        answerCommentAdapter.setHeaderView(header);
        // 添加滚动监听。
        answerCommentRv.addOnScrollListener(mOnScrollListener);
        answerCommentRv.setAdapter(answerCommentAdapter);
        mPresenter.getCommentAnswerRequest(answerBean.getAnswer_id(), 0, 5);
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
                    isLoadMore = false;
                    isLastPage = false;
                    page = 0;
                    mPresenter.getCommentAnswerRequest(answerBean.getAnswer_id(), page, 5);
                }
            }, 2000);
        }
    };

    /**
     * 加载更多
     */
    private RecyclerView.OnScrollListener mOnScrollListener = new RecyclerView.OnScrollListener() {
        @Override
        public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
            if (!recyclerView.canScrollVertically(1) && !isLastPage) {// 手指不能向上滑动了
                // TODO 这里有个注意的地方，如果你刚进来时没有数据，但是设置了适配器，这个时候就会触发加载更多，需要开发者判断下是否有数据，如果有数据才去加载更多。
                mPresenter.getCommentAnswerRequest(answerBean.getAnswer_id(), ++page, 5);
                isLoadMore = true;
                TLUtil.showToast("加载更多");
            }
        }
    };

    public void initToolbar() {
        toolbarTitle.setText("评论("+ answerBean.getAnswer_comment_number()+")");
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

    @Override
    public void onItemClick(int position, Object object) {

    }

    @Override
    public void returnAnswerCommentData(Object object) {

    }

    @Override
    public void returnAnswerCommentListData(AnswerCommentResult answerCommentResult) {
        List<AnswerCommentBean> list = answerCommentResult.getList();
        TLUtil.showLog(list.size() + "********111111111********");
        if (ListUtils.isList(list)) {
            if (list.size() == 0) {
                isLastPage = true;
                TLUtil.showToast("已经是最后一夜");
            } else {
                TLUtil.showLog(list.size() + "******333333**********");
                if (isLoadMore) {
                    TLUtil.showLog(list.size() + "********ooooooo********");
                    answerCommentBeenList.addAll(list);
                } else {
                    TLUtil.showLog(list.size() + "********222********");
                    answerCommentSrl.setRefreshing(false);
                    answerCommentBeenList.clear();
                    answerCommentBeenList.addAll(list);
                }
                answerCommentAdapter.addDatas(answerCommentBeenList);
            }
        }
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
        submitCommentAnswer();
    }

    public void submitCommentAnswer() {
        String content = answerCommentContentEt.getText().toString().trim();

        Integer answer_id = -1;
        if (answerBean != null)
            answer_id = answerBean.getAnswer_id();
        String str = SPUtils.getParams("user", "");
        if (!TextUtils.isEmpty(str)) {
            User user = JSON.parseObject(str, User.class);
            if (user != null) {
                if (TextUtils.isEmpty(content)) {
                    TLUtil.showToast("请输入内容");
                } else {
                    mPresenter.submitAnswerCommentRequest(answer_id, user.getUser_id(), content);
                }

            } else {
                startActivity(LoginActivity.class);
            }
        }


    }


}
