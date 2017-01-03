package com.doctorcar.mobile.common.base;

/**
 * Created by dd on 2016/12/9.
 */

public interface BaseView {
    void showLoading(String title);
    void stopLoading();
    void showErrorTip(String msg);
}
