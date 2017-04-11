package com.doctorcar.mobile.module.ask.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.doctorcar.mobile.R;

import butterknife.BindView;
import butterknife.ButterKnife;


public class AskAlreadyFragment extends Fragment {
    @BindView(R.id.ask_already_fg_rv)
    RecyclerView askAlreadyFgRv;
    @BindView(R.id.ask_already_fg_srl)
    SwipeRefreshLayout askAlreadyFgSrl;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View inflate = inflater.inflate(R.layout.ask_already_fragment, container, false);
        ButterKnife.bind(this, inflate);
        return inflate;
    }
}
