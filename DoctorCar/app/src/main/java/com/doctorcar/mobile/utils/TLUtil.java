package com.doctorcar.mobile.utils;

import android.util.Log;
import android.widget.Toast;

import com.doctorcar.mobile.common.baseapp.AppConfig;

/**
 * Created by dd on 2017/2/4.
 */

public class TLUtil {

    public static void showLog(String string){
        Log.i("OUTPUT",string);
    }

    public static void showToast(String string){
        Toast.makeText(AppConfig.context,string,Toast.LENGTH_SHORT).show();
    }

}
