package com.doctorcar.mobile.module.ask.activity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.doctorcar.mobile.R;
import com.doctorcar.mobile.common.base.BaseActivity;
import com.doctorcar.mobile.common.interf.OnItemClickViewListener;
import com.doctorcar.mobile.common.interf.OnModelItemClickViewListener;
import com.doctorcar.mobile.module.ask.adapter.BrandAdapter;
import com.doctorcar.mobile.module.ask.adapter.ModelAdapter;
import com.doctorcar.mobile.module.ask.bean.BrandBean;
import com.doctorcar.mobile.module.ask.bean.BrandModelBean;
import com.doctorcar.mobile.module.ask.bean.ModelBean;
import com.doctorcar.mobile.module.ask.contract.BrandContract;
import com.doctorcar.mobile.module.ask.model.BrandModel;
import com.doctorcar.mobile.module.ask.presenter.BrandPresenter;
import com.doctorcar.mobile.utils.TLUtil;
import com.doctorcar.mobile.view.indexbar.suspension.SuspensionDecoration;
import com.doctorcar.mobile.view.indexbar.widget.IndexBar;
import com.doctorcar.mobile.view.layout.CustomDrawerLayout;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by dd on 2017/3/8.
 */

public class BrandAndModelActivity extends BaseActivity<BrandPresenter, BrandModel> implements OnItemClickViewListener<BrandBean>, OnModelItemClickViewListener<ModelBean>, BrandContract.View {

    @BindView(R.id.brand_and_model_side_index_bar)
    IndexBar brandAndModelSideIndexBar;
    @BindView(R.id.brand_and_model_tv)
    TextView brandAndModelTv;
    @BindView(R.id.brand_recycler_view)
    RecyclerView brandRecyclerView;
    @BindView(R.id.model_recycler_view)
    RecyclerView modelRecyclerView;
    @BindView(R.id.toolbar_title)
    TextView toolbarTitle;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.brand_model_drawer_layout)
    CustomDrawerLayout brandModelDrawerLayout;
    private BrandAdapter brandAdapter;
    private ModelAdapter modelAdapter;
    private LinearLayoutManager mManager;
    private SuspensionDecoration suspensionDecoration;
    private List<BrandBean> brandBeanList = new ArrayList<BrandBean>();
    private List<ModelBean> modelBeanList = new ArrayList<ModelBean>();
    private static final String INDEX_STRING_TOP = "↑";

    private BrandBean brandBean ;

    @Override
    public int getLayoutId() {
        return R.layout.brand_and_model_activity;
    }

    @Override
    public void initPresenter() {
        mPresenter.setVM(this, mModel);
    }

    @Override
    public void initView() {
        toolbar.inflateMenu(R.menu.toobar);
        toolbarTitle.setText("品牌");
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

        brandModelDrawerLayout.setScrimColor(Color.TRANSPARENT);
//        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
//                this, brandModelDrawerLayout, toolbar, R.string.album,R.string.album);
//        brandModelDrawerLayout.setDrawerListener(toggle);
//        toggle.syncState();
        mPresenter.getBrandRequest();

        brandRecyclerView.setLayoutManager(mManager = new LinearLayoutManager(this));
        brandAdapter = new BrandAdapter(this, brandBeanList);
        brandAdapter.setOnRecyclerViewListener(this);
        brandRecyclerView.setAdapter(brandAdapter);
        brandRecyclerView.addItemDecoration(suspensionDecoration = new SuspensionDecoration(BrandAndModelActivity.this, brandBeanList));
        //如果add两个，那么按照先后顺序，依次渲染。
        brandRecyclerView.addItemDecoration(new DividerItemDecoration(BrandAndModelActivity.this, DividerItemDecoration.VERTICAL));
        modelRecyclerView.addItemDecoration(new DividerItemDecoration(BrandAndModelActivity.this, DividerItemDecoration.VERTICAL));

        //indexbar初始化
        brandAndModelSideIndexBar.setmPressedShowTextView(brandAndModelTv)//设置HintTextView
                .setNeedRealIndex(true)//设置需要真实的索引
                .setmLayoutManager(mManager);//设置RecyclerView的LayoutManager


        modelRecyclerView.setLayoutManager(mManager = new LinearLayoutManager(this));
        modelAdapter = new ModelAdapter(this, modelBeanList);
        modelAdapter.setOnModelItemClickViewListener(this);
        modelRecyclerView.setAdapter(modelAdapter);
        //模拟线上加载数据
//        initDatas(getResources().getStringArray(R.array.provinces));

    }


    /**
     * 组织数据源
     *
     * @param data
     * @return
     */
    private void initDatas(final String[] data) {
        //延迟两秒 模拟加载数据中....
//        getWindow().getDecorView().postDelayed(new Runnable() {
//            @Override
//            public void run() {
//                mDatas = new ArrayList<>();
//                //微信的头部 也是可以右侧IndexBar导航索引的，
//                // 但是它不需要被ItemDecoration设一个标题titile
//                mDatas.add((BrandBean) new BrandBean("新的朋友").setTop(true).setBaseIndexTag(INDEX_STRING_TOP));
//                mDatas.add((BrandBean) new BrandBean("群聊").setTop(true).setBaseIndexTag(INDEX_STRING_TOP));
//                mDatas.add((BrandBean) new BrandBean("标签").setTop(true).setBaseIndexTag(INDEX_STRING_TOP));
//                mDatas.add((BrandBean) new BrandBean("公众号").setTop(true).setBaseIndexTag(INDEX_STRING_TOP));
//                for (int i = 0; i < data.length; i++) {
//                    BrandBean cityBean = new BrandBean();
//                    cityBean.setBrand_name(data[i]);//设置城市名称
//                    mDatas.add(cityBean);
//                }
//                brandAdapter.setDatas(mDatas);
//                brandAdapter.notifyDataSetChanged();
//
//                brandAndModelSideIndexBar.setmSourceDatas(mDatas)//设置数据
//                        .invalidate();
//                suspensionDecoration.setmDatas(mDatas);
//            }
//        }, 500);
    }

    @Override
    public void onItemClick(int position, BrandBean object) {
        brandBean = object;
        List<ModelBean> list = new ArrayList<>();
        Integer brandId = object.getBrand_id();
        if (modelBeanList != null && modelBeanList.size() > 0) {
            for (ModelBean modelBean : modelBeanList) {
                Integer modelBrandId = modelBean.getBrand_id();
                if (brandId == modelBrandId) {
                    list.add(modelBean);
                }
            }
        }
        modelAdapter.setDatas(list);
        if(!brandModelDrawerLayout.isDrawerOpen(Gravity.RIGHT)){
            brandModelDrawerLayout.openDrawer(Gravity.RIGHT);
        }
    }

    @Override
    public void onModelItemClick(int position, ModelBean object) {
        Intent intent = new Intent(this, AskActivity.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable("brandBean",brandBean);
        bundle.putSerializable("modelBean",object);
        intent.putExtra("result", bundle);
        setResult(RESULT_OK, intent);
        finish();
    }

    @Override
    public void returnBrandData(BrandModelBean brandModelBean) {
        brandBeanList.addAll(brandModelBean.getBrandList());
        modelBeanList.addAll(brandModelBean.getModelList());
        brandAdapter.setDatas(brandBeanList);
        brandAdapter.notifyDataSetChanged();
        brandAndModelSideIndexBar.setmSourceDatas(brandModelBean.getBrandList()).invalidate();
        suspensionDecoration.setmDatas(brandModelBean.getBrandList());

//        modelAdapter.setDatas(modelBeanList);
//        modelAdapter.notifyDataSetChanged();
        TLUtil.showLog(JSON.toJSONString(brandModelBean));
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
