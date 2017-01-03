package com.doctorcar.mobile.common.base;

import android.content.Context;

import com.doctorcar.mobile.common.baserx.RxManager;

/**
 * Created by dd on 2016/12/9.
 */

public abstract class BasePresenter<T,E> {
    public Context mContext;
    public E mModel;
    public T mView;
    public RxManager mRxManage = new RxManager();

    public void setVM(T v, E m) {
        this.mView = v;
        this.mModel = m;
        this.onStart();
    }
    public void onStart(){
    };
    public void onDestroy() {
        mRxManage.clear();
    }

}
