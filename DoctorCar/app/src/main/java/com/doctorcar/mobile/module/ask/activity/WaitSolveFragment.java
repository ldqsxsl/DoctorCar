package com.doctorcar.mobile.module.ask.activity;

import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.doctorcar.mobile.R;
import com.doctorcar.mobile.bean.User;
import com.doctorcar.mobile.common.base.BaseFragment;
import com.doctorcar.mobile.common.interf.OnItemClickViewListener;
import com.doctorcar.mobile.module.ask.adapter.MyFocusProblemAdapter;
import com.doctorcar.mobile.module.ask.adapter.WaitSolveAdapter;
import com.doctorcar.mobile.module.ask.bean.ProblemBean;
import com.doctorcar.mobile.module.ask.bean.ProblemResult;
import com.doctorcar.mobile.module.ask.contract.WaitSolveProblemContact;
import com.doctorcar.mobile.module.ask.model.WaitSolveProblemModel;
import com.doctorcar.mobile.module.ask.presenter.WaitSolveProblemPresenter;
import com.doctorcar.mobile.utils.ListUtils;
import com.doctorcar.mobile.utils.TLUtil;
import com.doctorcar.mobile.utils.UserUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by dd on 2017/5/18.
 */

public class WaitSolveFragment extends BaseFragment<WaitSolveProblemPresenter,WaitSolveProblemModel> implements WaitSolveProblemContact.View ,OnItemClickViewListener {

    @BindView(R.id.wait_solve_fg_rv)
    RecyclerView waitSolveFgRv;
    @BindView(R.id.wait_solve_fg_srl)
    SwipeRefreshLayout waitSolveFgSrl;

    private WaitSolveAdapter waitSolveAdapter;
    private List<ProblemBean> problemResultList = new ArrayList<ProblemBean>();
    private Integer page = 0;
    private boolean isLoadMore = false;
    private boolean isLastPage = false;
    private User user;

    @Override
    protected int getLayoutResource() {
        return R.layout.wait_solve_fg;
    }

    @Override
    public void initPresenter() {
        mPresenter.setVM(this,mModel);
    }

    @Override
    protected void initView() {
        waitSolveAdapter = new WaitSolveAdapter(getActivity(),problemResultList);

        waitSolveAdapter.setOnRecyclerViewListener(this);

        waitSolveFgSrl.setOnRefreshListener(mOnRefreshListener);

        waitSolveFgRv.setLayoutManager(new LinearLayoutManager(getActivity()));// 布局管理器。
        waitSolveFgRv.setHasFixedSize(true);// 如果Item够简单，高度是确定的，打开FixSize将提高性能。
        waitSolveFgRv.setItemAnimator(new DefaultItemAnimator());// 设置Item默认动画，加也行，不加也行。

        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL);
        dividerItemDecoration.setDrawable(ContextCompat.getDrawable(getContext(),R.drawable.line));
        waitSolveFgRv.addItemDecoration(dividerItemDecoration);// 添加分割线。


        // 添加滚动监听。
        waitSolveFgRv.addOnScrollListener(mOnScrollListener);
        waitSolveFgRv.setAdapter(waitSolveAdapter);
        user = UserUtils.getUser();
        mPresenter.getNoSolveProblemRequest(page);
    }

    /**
     * 刷新监听。
     */
    private SwipeRefreshLayout.OnRefreshListener mOnRefreshListener = new SwipeRefreshLayout.OnRefreshListener() {
        @Override
        public void onRefresh() {
            waitSolveFgRv.postDelayed(new Runnable() {
                @Override
                public void run() {
                    isLoadMore = false;
                    isLastPage = false;
                    page = 0;
                    mPresenter.getNoSolveProblemRequest(page);
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
                mPresenter.getNoSolveProblemRequest(++page);
                isLoadMore = true;
                TLUtil.showToast("加载更多");
            }
        }
    };

    @Override
    public void returnNoSolveProblemListData(ProblemResult problemResult) {
        TLUtil.showLog("+++++++++++++++++++++++++++++++++++++++");
        List<ProblemBean> list = problemResult.getList();
        if (ListUtils.isList(list)){
            if (list.size() == 0){
                isLastPage = true;
                TLUtil.showToast("已经是最后一夜");
            }else{
                if (isLoadMore){
                    problemResultList.addAll(list);
                }else{
                    waitSolveFgSrl.setRefreshing(false);
                    problemResultList.clear();
                    problemResultList.addAll(list);
                }
                waitSolveAdapter.setDatas(problemResultList);
            }
        }
    }

    @Override
    public void onItemClick(int position, Object object) {

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
