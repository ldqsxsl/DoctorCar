package com.doctorcar.mobile.module.ask.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.doctorcar.mobile.R;
import com.doctorcar.mobile.bean.UploadImageResult;
import com.doctorcar.mobile.common.base.BaseActivity;
import com.doctorcar.mobile.module.ask.bean.BrandBean;
import com.doctorcar.mobile.module.ask.bean.ModelBean;
import com.doctorcar.mobile.module.ask.contract.AskContract;
import com.doctorcar.mobile.module.ask.model.AskModel;
import com.doctorcar.mobile.module.ask.presenter.AskPresenter;
import com.doctorcar.mobile.module.common.activity.PhotosActivity;
import com.doctorcar.mobile.module.common.adapter.ChooseAdapter;
import com.doctorcar.mobile.module.common.bean.EventEntry;
import com.doctorcar.mobile.utils.ImageUtils;
import com.doctorcar.mobile.utils.PermissionUtils;
import com.doctorcar.mobile.utils.TLUtil;
import com.wbn.choiceimage.lib.Utils.GridSpacingItemDecoration;
import com.wbn.choiceimage.lib.entity.PhotoEntry;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.MediaType;
import okhttp3.RequestBody;

/**
 * Created by dd on 2017/3/8.
 */

public class AskActivity extends BaseActivity<AskPresenter, AskModel> implements ChooseAdapter.OnItmeClickListener, AskContract.View {

    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;
    @BindView(R.id.ask_choice_mode_rl)
    RelativeLayout askChoiceModeRl;
    @BindView(R.id.toolbar_title)
    TextView toolbarTitle;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.ask_aty_mode_tv)
    TextView askAtyModeTv;
    @BindView(R.id.ask_aty_mode_et)
    EditText askAtyModeEt;

    private ChooseAdapter mAdapter;
    private List<PhotoEntry> photos = new ArrayList<PhotoEntry>();

    private BrandBean brandBean;
    private ModelBean modelBean;

    @Override
    public int getLayoutId() {
        return R.layout.ask_activity;
    }

    @Override
    public void initPresenter() {
        mPresenter.setVM(this, mModel);
    }

    @Override
    public void initView() {
        initToolbar();
        EventBus.getDefault().register(this);
        readExternalStorage();
        mAdapter = new ChooseAdapter(this);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 4));
        recyclerView.setAdapter(mAdapter);
        recyclerView.addItemDecoration(new GridSpacingItemDecoration(4, 4, true));
        List<EventEntry> list = new ArrayList<EventEntry>();
    }

    @OnClick({R.id.ask_choice_mode_rl})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.ask_choice_mode_rl:
                Intent intent = new Intent(this, BrandAndModelActivity.class);
                startActivityForResult(intent, 1);
                break;
        }
    }


    @Override
    public void onItemClicked(int position) {
        if (position == mAdapter.getItemCount() - 1) {
            startActivity(new Intent(AskActivity.this, PhotosActivity.class));
            EventBus.getDefault().postSticky(new EventEntry(mAdapter.getData(), EventEntry.SELECTED_PHOTOS_ID));
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void photosMessageEvent(EventEntry entries) {
        if (entries.id == EventEntry.RECEIVED_PHOTOS_ID) {
            mAdapter.reloadList(entries.photos);
            photos.clear();
            photos.addAll(entries.photos);
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void photoMessageEvent(PhotoEntry entry) {
        mAdapter.appendPhoto(entry);
    }

    @Override
    protected void onDestroy() {
        EventBus.getDefault().unregister(this);
        super.onDestroy();
    }

    public void initToolbar() {
        toolbarTitle.setText("清楚");
        toolbar.inflateMenu(R.menu.ask_menu);
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
                        if (photos.size() > 0) {
                            Map<String, RequestBody> map = new HashMap<String, RequestBody>();
                            for (int i = 0; i < photos.size(); i++) {
                                byte[] b = ImageUtils.getImage(photos.get(i).getPath());
                                File file = new File(photos.get(i).getPath());
                                map.put("file" + i + "\";filename=\"" + file.getName(), RequestBody.create(MediaType.parse("multipart/form-data"), b));
                            }
                            mPresenter.uploadImageRequest(map);
                        } else {
                            TLUtil.showToast("请选择图片");
                        }

                        break;
                }
                return true;
            }
        });
    }

    @Override
    public void returnUploadImageData(UploadImageResult object) {
        TLUtil.showToast("上传成功");
        TLUtil.showLog(object.getImage_key());
        submitAsk(object.getImage_key());
    }

    public void submitAsk(String image_key) {
        String content = askAtyModeEt.getText().toString().trim();
        if (brandBean == null || modelBean == null) {
            TLUtil.showToast("请选择型");
        } else if (TextUtils.isEmpty(content)) {
            TLUtil.showToast("请输入内容");
        }else{
            Integer brand_id= brandBean.getBrand_id();
            Integer model_id = modelBean.getModel_id();
            mPresenter.submitAskRequest("",brand_id,model_id, content ,image_key);
        }
    }

    @Override
    public void returnAskData(Object object) {
        finish();
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
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (data != null) {
            Bundle b = data.getBundleExtra("result");
            brandBean = (BrandBean) b.get("brandBean");
            modelBean = (ModelBean) b.get("modelBean");
            switch (requestCode) {
                case 1:
                    if (brandBean != null && modelBean != null) {
                        askAtyModeTv.setText(brandBean.getBrand_name() + " " + modelBean.getModel_name());
                    }
                    break;
            }
        }
    }

    public void readExternalStorage() {
        PermissionUtils.requestPermission(this, PermissionUtils.CODE_READ_EXTERNAL_STORAGE, mPermissionGrant);
    }

    private PermissionUtils.PermissionGrant mPermissionGrant = new PermissionUtils.PermissionGrant() {
        @Override
        public void onPermissionGranted(int requestCode) {
            switch (requestCode) {
                case PermissionUtils.CODE_RECORD_AUDIO:
//                    Toast.makeText(AskActivity.this, "Result Permission Grant CODE_RECORD_AUDIO", Toast.LENGTH_SHORT).show();
                    break;
                case PermissionUtils.CODE_GET_ACCOUNTS:
//                    Toast.makeText(AskActivity.this, "Result Permission Grant CODE_GET_ACCOUNTS", Toast.LENGTH_SHORT).show();
                    break;
                case PermissionUtils.CODE_READ_PHONE_STATE:
//                    Toast.makeText(AskActivity.this, "Result Permission Grant CODE_READ_PHONE_STATE", Toast.LENGTH_SHORT).show();
                    break;
                case PermissionUtils.CODE_CALL_PHONE:
//                    Toast.makeText(AskActivity.this, "Result Permission Grant CODE_CALL_PHONE", Toast.LENGTH_SHORT).show();
                    break;
                case PermissionUtils.CODE_CAMERA:
//                    Toast.makeText(AskActivity.this, "Result Permission Grant CODE_CAMERA", Toast.LENGTH_SHORT).show();
                    break;
                case PermissionUtils.CODE_ACCESS_FINE_LOCATION:
//                    Toast.makeText(AskActivity.this, "Result Permission Grant CODE_ACCESS_FINE_LOCATION", Toast.LENGTH_SHORT).show();
                    break;
                case PermissionUtils.CODE_ACCESS_COARSE_LOCATION:
//                    Toast.makeText(AskActivity.this, "Result Permission Grant CODE_ACCESS_COARSE_LOCATION", Toast.LENGTH_SHORT).show();
                    break;
                case PermissionUtils.CODE_READ_EXTERNAL_STORAGE:
//                    Toast.makeText(AskActivity.this, "Result Permission Grant CODE_READ_EXTERNAL_STORAGE", Toast.LENGTH_SHORT).show();
                    break;
                case PermissionUtils.CODE_WRITE_EXTERNAL_STORAGE:
//                    Toast.makeText(AskActivity.this, "Result Permission Grant CODE_WRITE_EXTERNAL_STORAGE", Toast.LENGTH_SHORT).show();
                    break;
                default:
                    break;
            }
        }
    };

    /**
     * Callback received when a permissions request has been completed.
     */
    @Override
    public void onRequestPermissionsResult(final int requestCode, @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        PermissionUtils.requestPermissionsResult(this, requestCode, permissions, grantResults, mPermissionGrant);
    }

}
