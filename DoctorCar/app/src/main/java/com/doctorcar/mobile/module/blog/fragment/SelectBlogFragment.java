package com.doctorcar.mobile.module.blog.fragment;

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
import com.doctorcar.mobile.common.base.BaseFragment;
import com.doctorcar.mobile.common.interf.OnItemClickViewListener;
import com.doctorcar.mobile.module.ask.bean.ProblemBean;
import com.doctorcar.mobile.module.blog.activity.BrowseBlogActivity;
import com.doctorcar.mobile.module.blog.adapter.MineArticleAdapter;
import com.doctorcar.mobile.module.blog.adapter.SelectBlogAdapter;
import com.doctorcar.mobile.module.blog.bean.ArticleBean;
import com.doctorcar.mobile.module.blog.bean.ArticleResult;
import com.doctorcar.mobile.module.blog.contract.SelectBlogContract;
import com.doctorcar.mobile.module.blog.model.SelectArticleModel;
import com.doctorcar.mobile.module.blog.presenter.SelectArticlePresenter;
import com.doctorcar.mobile.module.main.adapter.RecyclerViewAdapter;
import com.doctorcar.mobile.utils.ListUtils;
import com.doctorcar.mobile.utils.TLUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by dd on 2017/5/24.
 */

public class SelectBlogFragment extends BaseFragment<SelectArticlePresenter,SelectArticleModel> implements SelectBlogContract.View,OnItemClickViewListener<ArticleBean> {

    @BindView(R.id.select_blog_fg_rv)
    RecyclerView selectBlogFgRv;
    @BindView(R.id.select_blog_fg_srl)
    SwipeRefreshLayout selectBlogFgSrl;

    private SelectBlogAdapter selectBlogAdapter;
    private List<ArticleBean> articleBeanList = new ArrayList<ArticleBean>();
    private Integer page = 0;
    private boolean isLoadMore = false;
    private boolean isLastPage = false;

    @Override
    protected int getLayoutResource() {
        return R.layout.select_blog_fg;
    }

    @Override
    public void initPresenter() {
        mPresenter.setVM(this,mModel);
    }

    @Override
    protected void initView() {
        selectBlogFgRv.setLayoutManager(new LinearLayoutManager(getActivity()));// 布局管理器。
        selectBlogFgRv.setHasFixedSize(true);// 如果Item够简单，高度是确定的，打开FixSize将提高性能。
        selectBlogFgRv.setItemAnimator(new DefaultItemAnimator());// 设置Item默认动画，加也行，不加也行。

        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL);
        dividerItemDecoration.setDrawable(ContextCompat.getDrawable(getContext(), R.drawable.line));
        selectBlogFgRv.addItemDecoration(dividerItemDecoration);// 添加分割线。
        selectBlogFgSrl.setOnRefreshListener(mOnRefreshListener);

        selectBlogAdapter = new SelectBlogAdapter(getActivity(),articleBeanList);
        selectBlogAdapter.setOnRecyclerViewListener(this);
        selectBlogFgRv.setAdapter(selectBlogAdapter);

        View header = LayoutInflater.from(getActivity()).inflate(R.layout.answer_header_layout, selectBlogFgRv, false);

        mPresenter.getSelectArticleListRequest(page);
    }

    /**
     * 刷新监听。
     */
    private SwipeRefreshLayout.OnRefreshListener mOnRefreshListener = new SwipeRefreshLayout.OnRefreshListener() {
        @Override
        public void onRefresh() {
            selectBlogFgSrl.postDelayed(new Runnable() {
                @Override
                public void run() {
                    isLoadMore = false;
                    isLastPage = false;
                    page = 0;
                    mPresenter.getSelectArticleListRequest(page);
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
                mPresenter.getSelectArticleListRequest(++page);
                isLoadMore = true;
                TLUtil.showToast("加载更多");
            }
        }
    };


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
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

    @Override
    public void returnSelectArticleListData(ArticleResult articleResult) {
        List<ArticleBean> list = articleResult.getList();
        if (ListUtils.isList(list)){
            if (list.size() == 0){
                isLastPage = true;
                TLUtil.showToast("已经是最后一页");
            }else{

                if (isLoadMore){
                    articleBeanList.addAll(list);
                }else{
                    selectBlogFgSrl.setRefreshing(false);
                    articleBeanList.clear();
                    articleBeanList.addAll(list);
                }
                selectBlogAdapter.setDatas(articleBeanList);
            }
        }

    }

    @Override
    public void onItemClick(int position, ArticleBean object) {
        Bundle bundle = new Bundle();
        bundle.putString("data", object.getArticle_content());
        startActivity(BrowseBlogActivity.class,bundle);
    }
}
