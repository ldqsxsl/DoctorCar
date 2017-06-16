package com.doctorcar.mobile.module.blog.activity;

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
import android.webkit.WebView;
import android.widget.TextView;

import com.doctorcar.mobile.R;
import com.doctorcar.mobile.common.base.BaseActivity;
import com.doctorcar.mobile.common.interf.OnItemClickViewListener;
import com.doctorcar.mobile.module.ask.activity.AnswerActivity;
import com.doctorcar.mobile.module.blog.adapter.BlogCommentAdapter;
import com.doctorcar.mobile.module.blog.bean.ArticleCommentBean;
import com.doctorcar.mobile.utils.TLUtil;
import com.doctorcar.mobile.view.dialog.ShareDialogFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by dd on 2017/6/16.
 */

public class BrowseBlogActivity extends BaseActivity{

    @BindView(R.id.toolbar_title)
    TextView toolbarTitle;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.browse_blog_fg_rv)
    RecyclerView browseBlogFgRv;
    @BindView(R.id.browse_blog_fg_srl)
    SwipeRefreshLayout browseBlogFgSrl;

    private BlogCommentAdapter blogCommentAdapter;
    private List<ArticleCommentBean> articleCommentBeanList = new ArrayList<ArticleCommentBean>();

    @Override
    public int getLayoutId() {
        return R.layout.browse_blog_activity;
    }

    @Override
    public void initPresenter() {

    }

    @Override
    public void initView() {
        initToolbar();
        browseBlogFgSrl.setEnabled(false);
        browseBlogFgRv.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));// 布局管理器。
        browseBlogFgRv.setHasFixedSize(true);// 如果Item够简单，高度是确定的，打开FixSize将提高性能。
        browseBlogFgRv.setItemAnimator(new DefaultItemAnimator());// 设置Item默认动画，加也行，不加也行。
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(this, DividerItemDecoration.VERTICAL);
        dividerItemDecoration.setDrawable(ContextCompat.getDrawable(this, R.drawable.line));
        browseBlogFgRv.addItemDecoration(dividerItemDecoration);// 添加分割线。
        View header = LayoutInflater.from(this).inflate(R.layout.blog_comment_header, browseBlogFgRv, false);
        WebView webView = (WebView) header.findViewById(R.id.blog_wv);
        String content = getIntent().getExtras().getString("data");

        if (!TextUtils.isEmpty(content)){
            webView.loadDataWithBaseURL("",content,"text/html","utf-8","");
        }
        blogCommentAdapter = new BlogCommentAdapter(this,articleCommentBeanList);
        blogCommentAdapter.setHeaderView(header);
        browseBlogFgRv.setAdapter(blogCommentAdapter);
    }

    public void initToolbar() {
        toolbarTitle.setText("");
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
                        shareDialogFragment.show(BrowseBlogActivity.this.getFragmentManager(), "dialog");
                        break;
                }
                return true;
            }
        });
    }


}
