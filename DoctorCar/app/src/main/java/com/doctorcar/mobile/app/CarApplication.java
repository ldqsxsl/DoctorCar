package com.doctorcar.mobile.app;

import android.app.Application;
import android.util.DisplayMetrics;

import com.doctorcar.mobile.common.baseapp.AppConfig;
import com.doctorcar.mobile.common.baseapp.BaseApplication;
import com.doctorcar.mobile.utils.TLUtil;

/**
 * Created by dd on 2016/12/9.
 */

public class CarApplication extends BaseApplication{

    @Override
    public void onCreate() {
        super.onCreate();
        final float scale = this.getApplicationContext().getResources().getDisplayMetrics().density;
        DisplayMetrics dm =  this.getApplicationContext().getResources().getDisplayMetrics();
        TLUtil.showLog(scale+"");
        TLUtil.showLog(dm.densityDpi+"");//像素密度
        AppConfig.setContext(this.getApplicationContext());
    }
}
