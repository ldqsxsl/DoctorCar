package com.doctorcar.mobile.module.ask.activity;

import android.animation.ValueAnimator;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.PopupWindowCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.widget.PopupWindow;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.doctorcar.mobile.R;
import com.doctorcar.mobile.bean.User;
import com.doctorcar.mobile.common.base.BaseActivity;
import com.doctorcar.mobile.common.interf.OnItemClickViewListener;
import com.doctorcar.mobile.module.ask.adapter.AnswerAdapter;
import com.doctorcar.mobile.module.ask.bean.AnswerBean;
import com.doctorcar.mobile.module.ask.bean.AnswerResult;
import com.doctorcar.mobile.module.ask.bean.ProblemBean;
import com.doctorcar.mobile.module.ask.contract.AnswerContract;
import com.doctorcar.mobile.module.ask.model.AnswerModel;
import com.doctorcar.mobile.module.ask.presenter.AnswerPresenter;
import com.doctorcar.mobile.utils.ListUtils;
import com.doctorcar.mobile.utils.TLUtil;
import com.doctorcar.mobile.utils.UserUtils;
import com.doctorcar.mobile.view.dialog.ShareDialogFragment;
import com.wbn.progress.button.CircularProgressButton;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * Created by dd on 2017/4/17.
 */

public class AnswerActivity extends BaseActivity<AnswerPresenter, AnswerModel> implements AnswerContract.View, OnItemClickViewListener<AnswerBean>,AnswerAdapter.PraiseClickListener {
    @BindView(R.id.toolbar_title)
    TextView toolbarTitle;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.answer_recycler_view)
    RecyclerView answerRecyclerView;
    @BindView(R.id.answer_srl)
    SwipeRefreshLayout answerSrl;
    private ProblemBean problemBean;
    private AnswerAdapter answerAdapter;
    private List<AnswerBean> answerBeanList = new ArrayList<AnswerBean>();
    private Integer page = 0;
    private boolean isLoadMore = false;
    private boolean isLastPage = false;
    private User user;
    private TextView focusNumber ;
    private TextView answerNumber;

    private TextView answer;
    private TextView problem;
    private TextView focus;
    private TextView invited;
    private ProgressBar focusPb;

    private boolean answerStatus = false;//没回答
    private boolean focusStatus = false;



    @Override
    public int getLayoutId() {
        return R.layout.answer_activity;
    }

    @Override
    public void initPresenter() {
        mPresenter.setVM(this, mModel);
    }

    @Override
    public void initView() {
        problemBean = (ProblemBean) getIntent().getExtras().getSerializable("data");
        initToolbar();


        answerAdapter = new AnswerAdapter(this, answerBeanList);
        answerAdapter.setOnRecyclerViewListener(this);
        answerAdapter.setPraiseClickListener(this);
        answerSrl.setOnRefreshListener(mOnRefreshListener);
        answerRecyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));// 布局管理器。
        answerRecyclerView.setHasFixedSize(true);// 如果Item够简单，高度是确定的，打开FixSize将提高性能。
        answerRecyclerView.setItemAnimator(new DefaultItemAnimator());// 设置Item默认动画，加也行，不加也行。
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(this, DividerItemDecoration.VERTICAL);
        dividerItemDecoration.setDrawable(ContextCompat.getDrawable(this, R.drawable.line));
        answerRecyclerView.addItemDecoration(dividerItemDecoration);// 添加分割线。

        View header = LayoutInflater.from(this).inflate(R.layout.answer_header_layout, answerRecyclerView, false);
        answer = getView(header,R.id.answer_add_tv);
        problem = getView(header,R.id.answer_header_problem_tv);
        focusNumber = getView(header,R.id.answer_header_focus_number_tv);
        answerNumber = getView(header,R.id.answer_header_answer_number_tv);
        focus = getView(header,R.id.answer_header_focus_tv);
        invited = getView(header,R.id.answer_invited_tv);
        focusPb = getView(header,R.id.answer_header_focus_pb);
//        final CircularProgressButton circular_progress_button = getView(header,R.id.circular_progress_button);
//        circular_progress_button.setIndeterminateProgressMode(true);
//        circular_progress_button.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if (circular_progress_button.getProgress() == 0) {
//                    simulateSuccessProgress(circular_progress_button);
//                    circular_progress_button.setmState(CircularProgressButton.State.PROGRESS);
//
//                } else {
////                    circular_progress_button.setmState(CircularProgressButton.State.IDLE);
//                    simulateSuccessProgress(circular_progress_button);
//                    circular_progress_button.setmState(CircularProgressButton.State.PROGRESS);
//                    circular_progress_button.setProgress(0);
//
//                }
//            }
//        });


        problem.setText(problemBean.getProblem_content());

        invited.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(InvitedAnswerActivity.class);
            }
        });

        answer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(answerStatus){
                    Bundle bundle = new Bundle();
                    bundle.putString("type","0");
                    bundle.putSerializable("data", problemBean);
                    startActivity(SearchMyAnswerActivity.class,bundle);

                }else{
                    Bundle bundle = new Bundle();
                    bundle.putSerializable("data", problemBean);
                    startActivity(SubmitAnswerActivity.class, bundle);
                }
            }
        });
        answerAdapter.setHeaderView(header);
        // 添加滚动监听。
        answerRecyclerView.addOnScrollListener(mOnScrollListener);
        answerRecyclerView.setAdapter(answerAdapter);
        user = UserUtils.getUser();
        if (user != null){
            mPresenter.getAnswerRequest(user.getUser_id(),problemBean.getProblem_id(),page, 5);
        }else{
            mPresenter.getAnswerRequest("",problemBean.getProblem_id(),page, 5);
        }

        focus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                focusPb.setVisibility(View.VISIBLE);
                focus.setVisibility(View.GONE);
                if(focusStatus){
                    mPresenter.deleteProblemFocusRequest(user.getUser_id(),problemBean.getProblem_id());
                }else{
                    mPresenter.addProblemFocusRequest(user.getUser_id(),problemBean.getProblem_id());
                }
            }
        });

    }


    /**
     * 刷新监听。
     */
    private SwipeRefreshLayout.OnRefreshListener mOnRefreshListener = new SwipeRefreshLayout.OnRefreshListener() {
        @Override
        public void onRefresh() {
            answerRecyclerView.postDelayed(new Runnable() {
                @Override
                public void run() {
                    isLoadMore = false;
                    isLastPage = false;
                    page = 0;
                    if(user != null){
                        mPresenter.getAnswerRequest(user.getUser_id(),problemBean.getProblem_id(),page, 5);
                    }else{
                        mPresenter.getAnswerRequest("",problemBean.getProblem_id(),page, 5);
                    }

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
                if(user != null){
                    mPresenter.getAnswerRequest(user.getUser_id(),problemBean.getProblem_id(),++page, 5);
                }else{
                    mPresenter.getAnswerRequest("",problemBean.getProblem_id(),++page, 5);
                }
                isLoadMore = true;
                TLUtil.showToast("加载更多");
            }
        }
    };


    private void simulateSuccessProgress(final CircularProgressButton button) {
        ValueAnimator widthAnimation = ValueAnimator.ofInt(1, 100);
        widthAnimation.setDuration(1500);
        widthAnimation.setInterpolator(new AccelerateDecelerateInterpolator());
        widthAnimation.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                Integer value = (Integer) animation.getAnimatedValue();
                button.setProgress(value);
            }
        });
        widthAnimation.start();
    }


    public void initToolbar() {
        toolbarTitle.setText("回答(0)");
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
                    case R.id.answer_menu_share:
                        ShareDialogFragment shareDialogFragment = new ShareDialogFragment();
                        shareDialogFragment.show(AnswerActivity.this.getFragmentManager(), "dialog");
                        break;
                }
                return true;
            }
        });
    }

    @Override
    public void returnAnswerData(Object object) {

    }

    @Override
    public void returnAnswerListData(AnswerResult answerResult) {
        TLUtil.showLog("***********1111111111111111*****");
        focusNumber.setText(answerResult.getFocus_number()+"");
        answerNumber.setText(answerResult.getAnswer_number()+"");
        toolbarTitle.setText("回答("+answerResult.getAnswer_number()+")");
        if(answerResult.is_answer()){
            answer.setText("查看我的回答");
            answerStatus = true;
        }else{
            answer.setText("添加回答");
            answerStatus = false;
        }
        if(answerResult.is_focus_problem()){
            focus.setText("已经关注");
            focusStatus = true;
        }else{
            focus.setText("关注");
            focusStatus = false;
        }
        List<AnswerBean> list = answerResult.getList();
        TLUtil.showLog(list.size() + "****************");
        if (ListUtils.isList(list)) {
            if (list.size() == 0) {
                isLastPage = true;
                TLUtil.showToast("已经是最后一夜");
            } else {
                TLUtil.showLog(list.size() + "****************");
                if (isLoadMore) {
                    answerBeanList.addAll(list);
                } else {
                    answerSrl.setRefreshing(false);
                    answerBeanList.clear();
                    answerBeanList.addAll(list);
                }
                answerAdapter.addDatas(answerBeanList);
            }
        }
    }

    @Override
    public void returnAddProblemFocus(Object object) {
        focus.setText("已经关注");
        focusStatus = true;
        focusPb.setVisibility(View.GONE);
        focus.setVisibility(View.VISIBLE);
    }

    @Override
    public void returnDeleteProblemFocus(Object object) {
        focus.setText("关注");
        focusStatus = false;
        focusPb.setVisibility(View.GONE);
        focus.setVisibility(View.VISIBLE);
    }

    @Override
    public void onItemClick(int position, AnswerBean object) {
        TLUtil.showLog(object+"");
        Bundle bundle = new Bundle();
        bundle.putString("type","1");
        bundle.putSerializable("data",object);
        bundle.putSerializable("data1",problemBean);
        startActivity(AnswerCommentActivity.class,bundle);
    }

    @Override
    public void praise(View v,final AnswerBean answerBean) {
        View popupView = getLayoutInflater().inflate(R.layout.praise_layout, null);
        RadioGroup radioGroup = (RadioGroup) popupView.findViewById(R.id.praise_layout_rg);
        RadioButton agreedRb = (RadioButton) popupView.findViewById(R.id.praise_layout_agreed_rb);
        RadioButton opposeRb = (RadioButton) popupView.findViewById(R.id.praise_layout_oppose_rb);
//        opposeRb.setChecked(true);
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId){
                    case R.id.praise_layout_agreed_rb:
                        User user = UserUtils.getUser();

                        TLUtil.showLog(user.getUser_id()+"========");
                        TLUtil.showLog(answerBean.getUser_id()+"========");
                        mPresenter.addAnswerPraiseRequest(answerBean.getAnswer_id(),answerBean.getUser_id(),user.getUser_id(),answerBean.getProblem_id());
                        break;
                    case R.id.praise_layout_oppose_rb:
                        User user1 = UserUtils.getUser();
                        mPresenter.deleteAnswerPraiseRequest(answerBean.getAnswer_id(),user1.getUser_id());
                        break;
                }
            }
        });

        PopupWindow mPopupWindow = new PopupWindow(popupView, ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT, true);
        mPopupWindow.showAtLocation(v,Gravity.CENTER,0,0);
    }

    @Override
    public void returnAddAnswerPraise(Object object) {

    }

    @Override
    public void returnDeleteAnswerPraise(Object object) {

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
