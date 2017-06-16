package com.doctorcar.mobile.module.blog.activity;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.PopupMenu;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebSettings;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.ToggleButton;

import com.doctorcar.mobile.R;
import com.doctorcar.mobile.bean.User;
import com.doctorcar.mobile.common.base.BaseActivity;
import com.doctorcar.mobile.common.commonutils.DisplayUtil;

import com.doctorcar.mobile.database.manager.ArticlesManager;
import com.doctorcar.mobile.module.blog.contract.ArticleContract;
import com.doctorcar.mobile.module.blog.model.ArticleModel;
import com.doctorcar.mobile.module.blog.presenter.ArticlePresenter;
import com.doctorcar.mobile.module.common.activity.PhotosActivity;
import com.doctorcar.mobile.module.common.bean.EventEntry;
import com.doctorcar.mobile.module.common.presenter.UploadImagePresenter;
import com.doctorcar.mobile.module.login.activity.LoginActivity;
import com.doctorcar.mobile.utils.TLUtil;
import com.doctorcar.mobile.utils.UserUtils;
import com.doctorcar.mobile.view.dialog.EditDialogFragment;
import com.wbn.choiceimage.lib.entity.PhotoEntry;
import com.wbn.richeditor.RichEditor;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.lang.reflect.Method;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by dd on 2017/4/19.
 */

public class WriteBlogActivity extends BaseActivity<ArticlePresenter,ArticleModel> implements ArticleContract.View{

    @BindView(R.id.write_redo_iv)
    ImageView writeRedoIv;
    @BindView(R.id.write_undo_iv)
    ImageView writeUndoIv;
    @BindView(R.id.write_open_photo_iv)
    ImageView writeOpenPhotoIv;
    @BindView(R.id.write_setting_words_iv)
    ImageView writeSettingWordsIv;
    @BindView(R.id.write_setting_split_line_iv)
    ImageView writeSettingSplitLineIv;
    @BindView(R.id.write_setting_words_color_iv)
    ImageView writeSettingWordsColorIv;
    @BindView(R.id.write_setting_bg_iv)
    ImageView writeSettingBgIv;
    @BindView(R.id.write_words_ll)
    LinearLayout writeWordsLl;
    @BindView(R.id.write_blog_title_editor)
    RichEditor writeBlogTitleEditor;
    @BindView(R.id.write_blog_editor)
    RichEditor writeBlogEditor;
    @BindView(R.id.toolbar_title)
    TextView toolbarTitle;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.write_blog_blod_tb)
    ToggleButton writeBlogBlodTb;
    @BindView(R.id.write_blog_italic_tb)
    ToggleButton writeBlogItalicTb;
    @BindView(R.id.write_blog_underlined_tb)
    ToggleButton writeBlogUnderlinedTb;
    @BindView(R.id.write_blog_quote_tb)
    ToggleButton writeBlogQuoteTb;
    @BindView(R.id.write_blog_strikethrough_tb)
    ToggleButton writeBlogStrikethroughTb;
    @BindView(R.id.write_blog_h1_tb)
    ToggleButton writeBlogH1Tb;
    @BindView(R.id.write_blog_h2_tb)
    ToggleButton writeBlogH2Tb;
    @BindView(R.id.write_blog_h3_tb)
    ToggleButton writeBlogH3Tb;
    @BindView(R.id.write_blog_h4_tb)
    ToggleButton writeBlogH4Tb;
    @BindView(R.id.write_blog_center_tb)
    ToggleButton writeBlogCenterTb;
    @BindView(R.id.write_blog_left_tb)
    ToggleButton writeBlogLeftTb;
    @BindView(R.id.write_blog_right_tb)
    ToggleButton writeBlogRightTb;
    @BindView(R.id.write_blog_decrease_tb)
    ToggleButton writeBlogDecreaseTb;
    @BindView(R.id.write_blog_increase_tb)
    ToggleButton writeBlogIncreaseTb;
    @BindView(R.id.write_blog_numbered_tb)
    ToggleButton writeBlogNumberedTb;
    @BindView(R.id.write_blog_bullet_tb)
    ToggleButton writeBlogBulletTb;

    private String content;
    private String title;

    @Override
    public int getLayoutId() {
        return R.layout.write_blog_activity;
    }

    @Override
    public void initPresenter() {
        mPresenter.setVM(this,mModel);
    }

    @Override
    public void initView() {
        toolbarTitle.setText("写文章");
        toolbar.inflateMenu(R.menu.preview_menu);
        toolbar.setNavigationIcon(ContextCompat.getDrawable(this, R.drawable.ic_navigate_before_black_24dp));
        toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.preview_item_menu:
                        if(TextUtils.isEmpty(content)){
                            TLUtil.showToast("你还没写呢");
                        }else{
                            Bundle bundle = new Bundle();
                            bundle.putString("data",content);
                            startActivity(PreviewBlogActivity.class,bundle);
                        }
                        break;
                    case R.id.release_item_menu:
                        if(TextUtils.isEmpty(content)){
                            TLUtil.showToast("你还没写呢");
                        }else if(TextUtils.isEmpty(title)){
                            TLUtil.showToast("请为文章起个标题吧");
                        }else{
                            User user = UserUtils.getUser();
                            if (user != null){
                                mPresenter.addArticleRequest(user.getUser_id(),title,content,"1");
                            }else{
                                startActivity(LoginActivity.class);
                            }
                        }
                        break;
                    case R.id.save_item_menu:
                        if(TextUtils.isEmpty(content)){
                            TLUtil.showToast("你还没写呢");
                        }else if(TextUtils.isEmpty(title)){
                            TLUtil.showToast("请为文章起个标题吧");
                        }else{
                            ArticlesManager.getArticlesManager().addArticle(title,content,"0");
                            TLUtil.showToast("保存成功!");
                        }

                        break;
                }
                return true;
            }
        });
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        writeBlogEditor.getSettings().setDefaultTextEncodingName("utf-8");
        //支持javascript
//        writeBlogEditor.getSettings().setJavaScriptEnabled(true);
        // 设置可以支持缩放 
        writeBlogEditor.getSettings().setSupportZoom(true);
        // 设置出现缩放工具 
//        writeBlogEditor.getSettings().setBuiltInZoomControls(true);
        //扩大比例的缩放
//        writeBlogEditor.getSettings().setUseWideViewPort(true);
        //自适应屏幕
        writeBlogEditor.getSettings().setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
        writeBlogEditor.getSettings().setLoadWithOverviewMode(true);
        writeBlogEditor.setEditorWidth(DisplayUtil.getScreenWidth(this));

//        writeBlogEditor.getSettings().setLoadsImagesAutomatically(false);

        writeBlogEditor.getSettings().setBlockNetworkImage(true);

        writeBlogTitleEditor.setEditorFontSize(20);
        writeBlogTitleEditor.setPadding(10, 0, 10, 0);
        writeBlogTitleEditor.setPlaceholder("请输入标题");
        //富文本编辑初始化

        writeBlogEditor.setEditorFontSize(15);
        writeBlogEditor.setPadding(10, 10, 10, 50);
        writeBlogEditor.setPlaceholder("请输入正文");


        writeBlogTitleEditor.setOnTextChangeListener(new RichEditor.OnTextChangeListener() {
            @Override public void onTextChange(String text) {
                title = text;
            }
        });


        writeBlogEditor.setOnTextChangeListener(new RichEditor.OnTextChangeListener() {
            @Override public void onTextChange(String text) {
                content = text;
            }
        });

        writeBlogBlodTb.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                writeBlogEditor.setBold();
            }
        });

        writeBlogItalicTb.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                writeBlogEditor.setItalic();
            }
        });

        writeBlogUnderlinedTb.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                writeBlogEditor.setUnderline();
            }
        });

        writeBlogQuoteTb.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                writeBlogEditor.setBlockquote();
            }
        });

        writeBlogStrikethroughTb.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                writeBlogEditor.setStrikeThrough();
            }
        });

        writeBlogH1Tb.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                writeBlogEditor.setHeading(1);
            }
        });

        writeBlogH2Tb.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                writeBlogEditor.setHeading(2);
            }
        });

        writeBlogH3Tb.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                writeBlogEditor.setHeading(3);
            }
        });

        writeBlogH4Tb.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                writeBlogEditor.setHeading(4);
            }
        });

        writeBlogCenterTb.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                writeBlogEditor.setAlignCenter();
            }
        });


        writeBlogLeftTb.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                writeBlogEditor.setAlignLeft();
            }
        });

        writeBlogRightTb.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                writeBlogEditor.setAlignRight();
            }
        });

        writeBlogDecreaseTb.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                writeBlogEditor.setOutdent();
            }
        });

        writeBlogIncreaseTb.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                writeBlogEditor.setIndent();
            }
        });

        writeBlogNumberedTb.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                writeBlogEditor.setNumbers();
            }
        });

        writeBlogBulletTb.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                writeBlogEditor.setBullets();
            }
        });

    }

    @OnClick({R.id.write_redo_iv, R.id.write_undo_iv, R.id.write_open_photo_iv, R.id.write_setting_words_iv, R.id.write_setting_split_line_iv, R.id.write_setting_words_color_iv, R.id.write_setting_bg_iv})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.write_redo_iv:
                writeBlogEditor.redo();
                break;
            case R.id.write_undo_iv:
                writeBlogEditor.undo();
                break;
            case R.id.write_open_photo_iv:
                writeBlogEditor.insertImage("http://192.168.20.87:8080/images/681996017.jpg",
                        "car");
//                startActivity(PhotosActivity.class);
                break;
            case R.id.write_setting_words_iv:
                int b = writeWordsLl.getVisibility();
                if (View.VISIBLE == b) {
                    writeWordsLl.setVisibility(View.GONE);
                } else if (View.GONE == b) {
                    writeWordsLl.setVisibility(View.VISIBLE);
                }
                break;
            case R.id.write_setting_split_line_iv:
                settingSplitLine();
                break;
            case R.id.write_setting_words_color_iv:
                settingWordsColor();
                break;
            case R.id.write_setting_bg_iv:
                settingWordsBackground();
                break;
        }
    }

    public void settingWordsColor() {
        PopupMenu popup = new PopupMenu(this, writeSettingWordsColorIv);
        popup.setGravity(Gravity.TOP);
        popup.getMenuInflater().inflate(R.menu.color_menu, popup.getMenu());
        setIconsVisible(popup.getMenu(), true);
        popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                switch (item.getItemId()){
                    case R.id.color_w_red_menu:
                        writeBlogEditor.setTextColor(Color.RED);
                        break;
                    case R.id.color_w_black_menu:
                        writeBlogEditor.setTextColor(Color.BLACK);
                        break;
                    case R.id.color_w_yellow_menu:
                        writeBlogEditor.setTextColor(Color.YELLOW);
                        break;
                    case R.id.color_w_green_menu:
                        writeBlogEditor.setTextColor(Color.GREEN);
                        break;
                    case R.id.color_b_red_menu:
                        writeBlogEditor.setTextBackgroundColor(Color.RED);
                        break;
                    case R.id.color_b_black_menu:
                        writeBlogEditor.setTextBackgroundColor(Color.BLACK);
                        break;
                    case R.id.color_b_yellow_menu:
                        writeBlogEditor.setTextBackgroundColor(Color.YELLOW);
                        break;
                    case R.id.color_b_green_menu:
                        writeBlogEditor.setTextBackgroundColor(Color.GREEN);
                        break;
                }
                return false;
            }
        });
        popup.show();
    }


    public void settingSplitLine() {
        PopupMenu popup = new PopupMenu(this, writeSettingSplitLineIv);
        popup.setGravity(Gravity.TOP);
        popup.getMenuInflater().inflate(R.menu.mine_menu, popup.getMenu());
        setIconsVisible(popup.getMenu(), true);
        popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                switch (item.getItemId()){
                    case R.id.add_split_line_menu:
                        writeBlogEditor.insertSplitLine();
                        break;
                    case R.id.add_link_menu:
                        EditDialogFragment editDialogFragment = new EditDialogFragment();
                        editDialogFragment.setCancelable(false);
                        editDialogFragment.setEditDialogListener(new EditDialogFragment.EditDialogListener() {
                            @Override
                            public void makeSure(String name, String link) {
                                writeBlogEditor.insertLink(link, name);
                            }
                        });
                        editDialogFragment.show(getFragmentManager(),"edit");
                        break;
                }
                return false;
            }
        });
        popup.show();
    }

    public void settingWordsBackground(){
        PopupMenu popup = new PopupMenu(this, writeSettingBgIv);
        popup.setGravity(Gravity.TOP);
        popup.getMenuInflater().inflate(R.menu.background_menu, popup.getMenu());
        setIconsVisible(popup.getMenu(), true);
        popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                return false;
            }
        });
        popup.show();

    }

    @Override
    public void returnArticleData(Object object) {

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

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void photosMessageEvent(EventEntry entries) {
        if (entries.id == EventEntry.RECEIVED_PHOTOS_ID) {
            List<PhotoEntry> list = entries.photos;
        }
    }


    /**
     * 解决menu不显示图标问题
     *
     * @param menu
     * @param flag
     */
    private void setIconsVisible(Menu menu, boolean flag) {
        //判断menu是否为空
        if (menu != null) {
            try {
                //如果不为空,就反射拿到menu的setOptionalIconsVisible方法
                Method method = menu.getClass().getDeclaredMethod("setOptionalIconsVisible", Boolean.TYPE);
                //暴力访问该方法
                method.setAccessible(true);
                //调用该方法显示icon
                method.invoke(menu, flag);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }


}
