package com.doctorcar.mobile.module.ask.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.doctorcar.mobile.R;

/**
 * author：luck
 * project：EasyIndicator
 * package：com.luck.indicator
 * email：893855882@qq.com
 * data：2017/4/1
 */

public class FragmentTab2 extends Fragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.brand_item, container, false);
    }
}
