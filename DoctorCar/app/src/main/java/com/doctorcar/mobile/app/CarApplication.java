package com.doctorcar.mobile.app;

import android.app.Application;
import android.content.Context;
import android.support.multidex.MultiDex;
import android.util.DisplayMetrics;

import com.doctorcar.mobile.R;
import com.doctorcar.mobile.common.baseapp.AppConfig;
import com.doctorcar.mobile.common.baseapp.BaseApplication;
import com.doctorcar.mobile.database.manager.GreenDaoManager;
import com.doctorcar.mobile.utils.TLUtil;
import com.facebook.drawee.backends.pipeline.Fresco;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by dd on 2016/12/9.
 */

public class CarApplication extends BaseApplication{

    public static List<?> images=new ArrayList<>();
    public static List<String> titles=new ArrayList<>();

    @Override
    public void onCreate() {
        super.onCreate();
        AppConfig.setContext(this.getApplicationContext());
        GreenDaoManager.getInstance();

        Fresco.initialize(this);
        String[] urls = getResources().getStringArray(R.array.url);
        String[] tips = getResources().getStringArray(R.array.title);
        List list = Arrays.asList(urls);
        images = new ArrayList(list);
        List list1 = Arrays.asList(tips);
        titles= new ArrayList(list1);

//        final float scale = this.getApplicationContext().getResources().getDisplayMetrics().density;
//        DisplayMetrics dm =  this.getApplicationContext().getResources().getDisplayMetrics();
//        TLUtil.showLog(scale+"");
//        TLUtil.showLog(dm.densityDpi+"");//像素密度

    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);
    }
}

