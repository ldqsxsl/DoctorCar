package com.doctorcar.mobile.module.ask.activity;

import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.bartoszlipinski.recyclerviewheader.RecyclerViewHeader;
import com.doctorcar.mobile.R;
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

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by dd on 2017/4/17.
 */

public class AnswerActivity extends BaseActivity<AnswerPresenter, AnswerModel> implements AnswerContract.View, OnItemClickViewListener<AnswerBean> {
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

        answerSrl.setOnRefreshListener(mOnRefreshListener);
        answerRecyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));// 布局管理器。
        answerRecyclerView.setHasFixedSize(true);// 如果Item够简单，高度是确定的，打开FixSize将提高性能。
        answerRecyclerView.setItemAnimator(new DefaultItemAnimator());// 设置Item默认动画，加也行，不加也行。
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(this, DividerItemDecoration.VERTICAL);
        dividerItemDecoration.setDrawable(ContextCompat.getDrawable(this, R.drawable.line));
        answerRecyclerView.addItemDecoration(dividerItemDecoration);// 添加分割线。

        View header = LayoutInflater.from(this).inflate(R.layout.header_layout, answerRecyclerView, false);
        TextView answer = (TextView) header.findViewById(R.id.answer_add_tv);
        answer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                bundle.putSerializable("data", problemBean);
                startActivity(SubmitAnswerActivity.class, bundle);
            }
        });
        answerAdapter.setHeaderView(header);
        // 添加滚动监听。
        answerRecyclerView.addOnScrollListener(mOnScrollListener);
        answerRecyclerView.setAdapter(answerAdapter);


        mPresenter.getAnswerRequest(problemBean.getProblem_id(),page, 5);
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
                    mPresenter.getAnswerRequest(problemBean.getProblem_id(),page, 5);
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
                mPresenter.getAnswerRequest(problemBean.getProblem_id(),++page, 5);
                isLoadMore = true;
                TLUtil.showToast("加载更多");
            }
        }
    };


    public void initToolbar() {
        toolbarTitle.setText("回答(11)");
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
    public void returnAnswerData(Object object) {

    }

    @Override
    public void returnAnswerListData(AnswerResult answerResult) {
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
    public void onItemClick(int position, AnswerBean object) {
        Bundle bundle = new Bundle();
        bundle.putSerializable("data",object);
        startActivity(AnswerCommentActivity.class,bundle);
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
