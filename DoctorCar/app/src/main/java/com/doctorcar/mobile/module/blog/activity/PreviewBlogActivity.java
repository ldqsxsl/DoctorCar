package com.doctorcar.mobile.module.blog.activity;

import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebView;
import android.widget.TextView;

import com.doctorcar.mobile.R;
import com.doctorcar.mobile.common.base.BaseActivity;
import com.doctorcar.mobile.utils.TLUtil;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by dd on 2017/5/3.
 */

public class PreviewBlogActivity extends BaseActivity {

    @BindView(R.id.toolbar_title)
    TextView toolbarTitle;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.preview_blog_wv)
    WebView previewBlogWv;

    @Override
    public int getLayoutId() {
        return R.layout.preview_blog_activity;
    }

    @Override
    public void initPresenter() {

    }

    @Override
    public void initView() {
        initToolbar();
    }

    public void initToolbar(){
        toolbarTitle.setText("预览");
        toolbar.setNavigationIcon(ContextCompat.getDrawable(this, R.drawable.ic_navigate_before_black_24dp));
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        String content = getIntent().getExtras().getString("data");
        TLUtil.showLog(content);
        if (!TextUtils.isEmpty(content)){
            previewBlogWv.loadDataWithBaseURL("",content,"text/html","utf-8","");
        }


    }

}
