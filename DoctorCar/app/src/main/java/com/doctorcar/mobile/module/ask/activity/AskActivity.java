package com.doctorcar.mobile.module.ask.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.doctorcar.mobile.R;
import com.doctorcar.mobile.common.base.BaseActivity;
import com.doctorcar.mobile.common.compressorutils.FileUtil;
import com.doctorcar.mobile.common.security.Base64;
import com.doctorcar.mobile.module.ask.bean.BrandBean;
import com.doctorcar.mobile.module.ask.bean.ModelBean;
import com.doctorcar.mobile.module.ask.contract.AskContract;
import com.doctorcar.mobile.module.ask.model.AskModel;
import com.doctorcar.mobile.module.ask.presenter.AskPresenter;
import com.doctorcar.mobile.module.common.activity.PhotosActivity;
import com.doctorcar.mobile.module.common.adapter.ChooseAdapter;
import com.doctorcar.mobile.module.common.bean.EventEntry;
import com.doctorcar.mobile.utils.PermissionUtils;
import com.doctorcar.mobile.utils.TLUtil;
import com.wbn.choiceimage.lib.Utils.GridSpacingItemDecoration;
import com.wbn.choiceimage.lib.entity.PhotoEntry;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by dd on 2017/3/8.
 */

public class AskActivity extends BaseActivity<AskPresenter,AskModel> implements ChooseAdapter.OnItmeClickListener ,AskContract.View{

    @BindView(R.id.editText)
    EditText editText;
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

    private ChooseAdapter mAdapter;

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
//        mPresenter.submitAskRequest();
//        Base64.decode()
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
            List<PhotoEntry> photos = entries.photos;
            StringBuffer stringBuffer = new StringBuffer();
            String str = "";
            for(int i = 0 ; i < photos.size(); i++){
                TLUtil.showLog("PPPPPPPPPPPPPPPPPPPPPPPPPPP");
                byte[] bytes = FileUtil.fileToByte(photos.get(i).getPath());
                String img = Base64.encode(bytes);
//                stringBuffer.append(img);
                str = str + img;
                if(i != photos.size()-1){
                    TLUtil.showLog("OOOOOOOOOOOOOOOOOOOOOOOOO");
                    str = str+",";
//                    stringBuffer.append(",");
                }
            }
            TLUtil.showLog("LLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLL");
            TLUtil.showLog(str);
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
        toolbar.inflateMenu(R.menu.toobar);
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
                    case R.id.action_share:
                        TLUtil.showToast("分享");
                        break;
                }
                return true;
            }
        });
    }

    @Override
    public void returnAskData(Object object) {

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
            BrandBean brandBean = (BrandBean) b.get("brandBean");
            ModelBean modelBean = (ModelBean) b.get("modelBean");
            switch (requestCode) {
                case 1:
                    if (brandBean != null && modelBean != null){
                        askAtyModeTv.setText(brandBean.getBrand_name()+" "+modelBean.getModel_name());
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
