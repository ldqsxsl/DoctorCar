package com.doctorcar.mobile.view.dialog;

import android.app.DialogFragment;
import android.os.Bundle;
import android.view.Gravity;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;

import com.doctorcar.mobile.R;

/**
 * Created by dd on 2017/5/9.
 */

public class BaseDialogFragment extends DialogFragment{
    Window window;
    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        window = getDialog().getWindow();
        window.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
//        WindowManager.LayoutParams wlp = window.getAttributes();
//        wlp.gravity = Gravity.BOTTOM;
//        window.setAttributes(wlp);
        window.setWindowAnimations(R.style.BottomToTopAnim);

    }
}
