package com.doctorcar.mobile.module.ask.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.doctorcar.mobile.R;
import com.doctorcar.mobile.common.base.BaseFragment;
import com.doctorcar.mobile.common.interf.OnItemClickViewListener;
import com.doctorcar.mobile.module.ask.adapter.AskAlreadyAdapter;
import com.doctorcar.mobile.module.ask.bean.ProblemBean;
import com.doctorcar.mobile.module.ask.bean.ProblemResult;
import com.doctorcar.mobile.module.ask.contract.AskContract;
import com.doctorcar.mobile.module.ask.contract.AskGetContract;
import com.doctorcar.mobile.module.ask.model.AskGetModel;
import com.doctorcar.mobile.module.ask.presenter.AskGetPresenter;
import com.doctorcar.mobile.utils.ListUtils;
import com.doctorcar.mobile.utils.TLUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


public class AskAlreadyFragment extends BaseFragment<AskGetPresenter,AskGetModel> implements AskGetContract.View,OnItemClickViewListener<ProblemBean> {
    @BindView(R.id.ask_already_fg_rv)
    RecyclerView askAlreadyFgRv;
    @BindView(R.id.ask_already_fg_srl)
    SwipeRefreshLayout askAlreadyFgSrl;

    private AskAlreadyAdapter askAlreadyAdapter;
    private List<ProblemBean> problemResultList = new ArrayList<ProblemBean>();
    private Integer page = 0;
    private boolean isLoadMore = false;
    private boolean isLastPage = false;


    @Override
    protected int getLayoutResource() {
        return R.layout.ask_already_fragment;
    }

    @Override
    public void initPresenter() {
        mPresenter.setVM(this,mModel);
    }

    @Override
    protected void initView() {
        askAlreadyAdapter = new AskAlreadyAdapter(getActivity(),problemResultList);
        askAlreadyAdapter.setOnRecyclerViewListener(this);

        askAlreadyFgSrl.setOnRefreshListener(mOnRefreshListener);
        askAlreadyFgRv.setLayoutManager(new LinearLayoutManager(getActivity()));// 布局管理器。
        askAlreadyFgRv.setHasFixedSize(true);// 如果Item够简单，高度是确定的，打开FixSize将提高性能。
        askAlreadyFgRv.setItemAnimator(new DefaultItemAnimator());// 设置Item默认动画，加也行，不加也行。
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL);
        dividerItemDecoration.setDrawable(ContextCompat.getDrawable(getContext(),R.drawable.line));
        askAlreadyFgRv.addItemDecoration(dividerItemDecoration);// 添加分割线。
        // 添加滚动监听。
        askAlreadyFgRv.addOnScrollListener(mOnScrollListener);
        askAlreadyFgRv.setAdapter(askAlreadyAdapter);

        mPresenter.getAskRequest(page,5);
    }

    /**
     * 刷新监听。
     */
    private SwipeRefreshLayout.OnRefreshListener mOnRefreshListener = new SwipeRefreshLayout.OnRefreshListener() {
        @Override
        public void onRefresh() {
            askAlreadyFgRv.postDelayed(new Runnable() {
                @Override
                public void run() {
                    isLoadMore = false;
                    isLastPage = false;
                    page = 0;
                    mPresenter.getAskRequest(page,5);
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
                mPresenter.getAskRequest(++page,5);
                isLoadMore = true;
                TLUtil.showToast("加载更多");
            }
        }
    };

    @Override
    public void onItemClick(int position, ProblemBean object) {
        Bundle bundle = new Bundle();
        bundle.putSerializable("data",object);
        startActivity(AnswerActivity.class,bundle);
    }

    @Override
    public void returnGetAskData(ProblemResult problemResult) {
        List<ProblemBean> list = problemResult.getList();
        if (ListUtils.isList(list)){
            if (list.size() == 0){
                isLastPage = true;
                TLUtil.showToast("已经是最后一夜");
            }else{
                if (isLoadMore){
                    problemResultList.addAll(list);
                }else{
                    askAlreadyFgSrl.setRefreshing(false);
                    problemResultList.clear();
                    problemResultList.addAll(list);
                }
                askAlreadyAdapter.setDatas(problemResultList);
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
}
