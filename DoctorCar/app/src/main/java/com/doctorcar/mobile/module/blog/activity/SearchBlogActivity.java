package com.doctorcar.mobile.module.blog.activity;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.ActionMenuView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.doctorcar.mobile.R;
import com.doctorcar.mobile.common.base.BaseActivity;
import com.doctorcar.mobile.utils.TLUtil;

import java.lang.reflect.Field;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by dd on 2017/6/15.
 */

public class SearchBlogActivity extends BaseActivity {

    @BindView(R.id.toolbar_title)
    TextView toolbarTitle;
    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @Override
    public int getLayoutId() {
        return R.layout.search_blog_activity;
    }

    @Override
    public void initPresenter() {

    }

    @Override
    public void initView() {
        initToolbar();

    }


    public void initToolbar()  {
        toolbar.inflateMenu(R.menu.search_menu);
        toolbarTitle.setVisibility(View.GONE);
        toolbar.setNavigationIcon(ContextCompat.getDrawable(this, R.drawable.ic_navigate_before_black_24dp));

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        SearchView searchView = (SearchView) toolbar.getMenu().getItem(0).getActionView();
        TextView textView = (TextView) toolbar.getMenu().getItem(1).getActionView();
        textView.setText("       ");
        searchView.setIconifiedByDefault(false);
        searchView.setSubmitButtonEnabled(false);
        searchView.setQueryHint("搜索博客");
        searchView.setBackgroundColor(ContextCompat.getColor(this,R.color.transparent));
        searchView.setBackgroundResource(R.drawable.border_box_radius);
        ActionMenuView dsd;
        try {
            Class<?> argClass = searchView.getClass();
            Field field = argClass.getDeclaredField("mSearchPlate");
            field.setAccessible(true);
            View view = (View) field.get(searchView);
            view.setBackgroundColor(Color.TRANSPARENT);
            ActionMenuView.LayoutParams layoutParams = new ActionMenuView.LayoutParams(ActionMenuView.LayoutParams.MATCH_PARENT,ActionMenuView.LayoutParams.MATCH_PARENT);
//            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,LinearLayout.LayoutParams.MATCH_PARENT);
            layoutParams.setMargins(160, 16, 160, 16);
            layoutParams.rightMargin = 160;
//            view.setLayoutParams(layoutParams);
            searchView.setLayoutParams(layoutParams);
//            searchView.setGravity(Gravity.CENTER_HORIZONTAL);
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

//        String content = getIntent().getExtras().getString"data");
//        TLUtil.showLog(content);
//        if (!TextUtils.isEmpty(content)){
//            previewBlogWv.loadDataWithBaseURL("",content,"text/html","utf-8","");
//        }


    }

}
