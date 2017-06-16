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
import com.doctorcar.mobile.bean.User;
import com.doctorcar.mobile.common.base.BaseFragment;
import com.doctorcar.mobile.common.interf.OnItemClickViewListener;
import com.doctorcar.mobile.database.Articles;
import com.doctorcar.mobile.module.blog.activity.PreviewBlogActivity;
import com.doctorcar.mobile.module.blog.adapter.MineArticleAdapter;
import com.doctorcar.mobile.module.blog.bean.ArticleBean;
import com.doctorcar.mobile.module.blog.bean.ArticleResult;
import com.doctorcar.mobile.module.blog.contract.ArticleGetContract;
import com.doctorcar.mobile.module.blog.model.ArticleGetModel;
import com.doctorcar.mobile.module.blog.presenter.ArticleGetPresenter;
import com.doctorcar.mobile.utils.TLUtil;
import com.doctorcar.mobile.utils.UserUtils;
import com.doctorcar.mobile.view.dialog.ListDialogFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;



public class MineArticlesFragment extends BaseFragment<ArticleGetPresenter, ArticleGetModel> implements ArticleGetContract.View,OnItemClickViewListener<ArticleBean> {

    @BindView(R.id.mine_articles_fg_rv)
    RecyclerView mineArticlesFgRv;
    @BindView(R.id.mine_articles_fg_srl)
    SwipeRefreshLayout mineArticlesFgSrl;

    private MineArticleAdapter mineArticleAdapter;
    private List<ArticleBean> articleBeanList = new ArrayList<ArticleBean>();

    @Override
    protected int getLayoutResource() {
        return R.layout.mine_articles_fragmet;
    }

    @Override
    public void initPresenter() {
        mPresenter.setVM(this, mModel);
    }

    @Override
    protected void initView() {

        mineArticlesFgRv.setLayoutManager(new LinearLayoutManager(getActivity()));// 布局管理器。
        mineArticlesFgRv.setHasFixedSize(true);// 如果Item够简单，高度是确定的，打开FixSize将提高性能。
        mineArticlesFgRv.setItemAnimator(new DefaultItemAnimator());// 设置Item默认动画，加也行，不加也行。

        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL);
        dividerItemDecoration.setDrawable(ContextCompat.getDrawable(getContext(), R.drawable.line));
        mineArticlesFgRv.addItemDecoration(dividerItemDecoration);// 添加分割线。

        mineArticleAdapter = new MineArticleAdapter(getActivity(),articleBeanList);
        mineArticleAdapter.setOnRecyclerViewListener(this);
        mineArticlesFgRv.setAdapter(mineArticleAdapter);

        User user = UserUtils.getUser();
        if (user != null) {
            mPresenter.getArticleListRequest(user.getUser_id(), 0, 10);
        }
    }

    @Override
    public void onItemClick(int position, final ArticleBean object) {
        ListDialogFragment listDialogFragment = new ListDialogFragment();
        listDialogFragment.setListDialogChoiceListener(new ListDialogFragment.ListDialogChoiceListener() {
            @Override
            public void choicePosition(int position) {
                switch (position) {
                    case 0:
                        Bundle bundle = new Bundle();
                        bundle.putString("data", object.getArticle_content());
                        startActivity(PreviewBlogActivity.class, bundle);
                        break;
                    case 1:
                        mPresenter.deleteArticleRequest(object.getArticle_id());
                        articleBeanList.remove(object);
                        mineArticleAdapter.setDatas(articleBeanList);
                        break;
                    case 2:
                        break;
                    case 3:
                        break;
                }
            }
        });
        listDialogFragment.show(getActivity().getFragmentManager(), "dialog");
    }

    @Override
    public void returnArticleListData(ArticleResult articleResult) {
        if (articleResult != null){
            List<ArticleBean> list = articleResult.getList();
            articleBeanList.addAll(list);
            mineArticleAdapter.setDatas(articleBeanList);
        }
    }

    @Override
    public void returnDeleteArticleData() {
        TLUtil.showToast("删除成功");
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
