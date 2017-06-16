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
import com.doctorcar.mobile.database.Articles;
import com.doctorcar.mobile.database.manager.ArticlesManager;
import com.doctorcar.mobile.module.blog.activity.PreviewBlogActivity;
import com.doctorcar.mobile.module.blog.adapter.DraftBoxAdapter;
import com.doctorcar.mobile.utils.ListUtils;
import com.doctorcar.mobile.utils.TLUtil;
import com.doctorcar.mobile.view.dialog.ListDialogFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * author：luck
 * project：EasyIndicator
 * package：com.luck.indicator
 * email：893855882@qq.com
 * data：2017/4/1
 */

public class DraftBoxFragment extends BaseFragment implements OnItemClickViewListener<Articles> {
    @BindView(R.id.draft_box_fg_rv)
    RecyclerView draftBoxFgRv;
    @BindView(R.id.draft_box_fg_srl)
    SwipeRefreshLayout draftBoxFgSrl;

    private DraftBoxAdapter draftBoxAdapter;
    private List<Articles> articlesList = new ArrayList<Articles>();

    @Override
    protected int getLayoutResource() {
        return R.layout.draft_box_fragment;
    }

    @Override
    public void initPresenter() {

    }

    @Override
    protected void initView() {
        List<Articles> list = ArticlesManager.getArticlesManager().getAllArticlesList();
        if (ListUtils.isListNotNull(list)) {
            TLUtil.showLog(list.size() + "sdsdsdsd");
            articlesList.addAll(list);
        }
        draftBoxAdapter = new DraftBoxAdapter(getActivity(), articlesList);
        draftBoxAdapter.setOnRecyclerViewListener(this);
        draftBoxFgRv.setLayoutManager(new LinearLayoutManager(getActivity()));// 布局管理器。
        draftBoxFgRv.setHasFixedSize(true);// 如果Item够简单，高度是确定的，打开FixSize将提高性能。
        draftBoxFgRv.setItemAnimator(new DefaultItemAnimator());// 设置Item默认动画，加也行，不加也行。
        draftBoxFgSrl.setOnRefreshListener(mOnRefreshListener);

        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL);
        dividerItemDecoration.setDrawable(ContextCompat.getDrawable(getContext(), R.drawable.line));
        draftBoxFgRv.addItemDecoration(dividerItemDecoration);// 添加分割线。

        draftBoxFgRv.setAdapter(draftBoxAdapter);
    }

    /**
     * 刷新监听。
     */
    private SwipeRefreshLayout.OnRefreshListener mOnRefreshListener = new SwipeRefreshLayout.OnRefreshListener() {
        @Override
        public void onRefresh() {
            draftBoxFgRv.postDelayed(new Runnable() {
                @Override
                public void run() {
                    List<Articles> list = ArticlesManager.getArticlesManager().getAllArticlesList();
                    if (ListUtils.isListNotNull(list)) {
                        TLUtil.showLog(list.size() + "sdsdsdsd");
                        articlesList.clear();
                        articlesList.addAll(list);
                        draftBoxAdapter.setDatas(articlesList);
                        draftBoxFgSrl.setRefreshing(false);
                    }
                }
            }, 1000);
        }
    };

    @Override
    public void onItemClick(int position, final Articles object) {
        ListDialogFragment listDialogFragment = new ListDialogFragment();
        listDialogFragment.setListDialogChoiceListener(new ListDialogFragment.ListDialogChoiceListener() {
            @Override
            public void choicePosition(int position) {
                switch (position) {
                    case 0:
                        Bundle bundle = new Bundle();
                        bundle.putString("data", object.getContent());
                        startActivity(PreviewBlogActivity.class, bundle);
                        break;
                    case 1:
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

}
