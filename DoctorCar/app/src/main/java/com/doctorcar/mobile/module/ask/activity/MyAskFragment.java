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
import com.doctorcar.mobile.module.ask.adapter.MyAskAdapter;
import com.doctorcar.mobile.module.ask.bean.ProblemBean;
import com.doctorcar.mobile.module.ask.bean.ProblemResult;
import com.doctorcar.mobile.module.ask.contract.MyAskContract;
import com.doctorcar.mobile.module.ask.model.MyAskModel;
import com.doctorcar.mobile.module.ask.presenter.MyAskPresenter;
import com.doctorcar.mobile.utils.ListUtils;
import com.doctorcar.mobile.utils.TLUtil;
import com.doctorcar.mobile.utils.UserUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by dd on 2017/5/10.
 */

public class MyAskFragment extends BaseFragment<MyAskPresenter, MyAskModel> implements MyAskContract.View {

    @BindView(R.id.my_ask_fg_rv)
    RecyclerView myAskFgRv;
    @BindView(R.id.my_ask_fg_srl)
    SwipeRefreshLayout myAskFgSrl;

    private MyAskAdapter myAskAdapter;
    private List<ProblemBean> problemBeanList = new ArrayList<ProblemBean>();

    @Override
    protected int getLayoutResource() {
        return R.layout.my_ask_fg;
    }

    @Override
    public void initPresenter() {
        mPresenter.setVM(this, mModel);
    }

    @Override
    protected void initView() {
        User user = UserUtils.getUser();
        if(user != null){
            mPresenter.getMyAskRequest(user.getUser_id());
        }

        myAskFgRv.setLayoutManager(new LinearLayoutManager(getActivity()));// 布局管理器。
        myAskFgRv.setHasFixedSize(true);// 如果Item够简单，高度是确定的，打开FixSize将提高性能。
        myAskFgRv.setItemAnimator(new DefaultItemAnimator());// 设置Item默认动画，加也行，不加也行。

        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL);
        dividerItemDecoration.setDrawable(ContextCompat.getDrawable(getContext(),R.drawable.line));
        myAskFgRv.addItemDecoration(dividerItemDecoration);// 添加分割线。

        myAskAdapter = new MyAskAdapter(getActivity(),problemBeanList);
        myAskFgRv.setAdapter(myAskAdapter);
    }

    @Override
    public void returnGetMyAskData(ProblemResult problemResult) {
        if (problemResult != null){
            List<ProblemBean> problemBeanList = problemResult.getList();
            TLUtil.showLog(problemBeanList.size()+"pppppppppppppp");
            if(ListUtils.isListNotNull(problemBeanList)){
                myAskAdapter.setDatas(problemBeanList);
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
