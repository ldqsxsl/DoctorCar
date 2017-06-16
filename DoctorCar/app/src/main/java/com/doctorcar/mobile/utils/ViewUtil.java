package com.doctorcar.mobile.utils;

import android.content.Context;
import android.util.Log;
import android.view.View;

/**
 * Created by dd on 2017/3/7.
 */

public class ViewUtil {

    /**
     * 通用findViewById,减少重复的类型转换
     * @param view
     * @param id
     * @param <E>
     * @return
     */
    public  static <E extends View> E getView(View view, int id) {
        try {
            return (E) view.findViewById(id);
        } catch (ClassCastException ex) {
            Log.e("", "Could not cast View to concrete class.", ex);
            throw ex;
        }
    }

    /**
     * 根据手机的分辨率从 dp 的单位 转成为 px(像素)
     */
    public static int dip2px(Context context, float dpValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }

    /**
     * 根据手机的分辨率从 px(像素) 的单位 转成为 dp
     */
    public static int px2dip(Context context, float pxValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (pxValue / scale + 0.5f);
    }

}
