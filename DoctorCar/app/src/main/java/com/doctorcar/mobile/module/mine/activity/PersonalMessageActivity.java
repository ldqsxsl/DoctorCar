package com.doctorcar.mobile.module.mine.activity;

import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.doctorcar.mobile.R;
import com.doctorcar.mobile.common.base.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by dd on 2017/5/24.
 */

public class PersonalMessageActivity extends BaseActivity {

    @BindView(R.id.toolbar_title)
    TextView toolbarTitle;
    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @Override
    public int getLayoutId() {
        return R.layout.personal_message_activity;
    }

    @Override
    public void initPresenter() {

    }

    @Override
    public void initView() {
        initToolbar();
    }

    public void initToolbar() {
        toolbarTitle.setText("个人信息");
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
}
