package com.doctorcar.mobile.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;

import com.doctorcar.mobile.R;

/**
 * Created by dd on 2017/3/28.
 */

public class AddImageView extends LinearLayout{

    View view ;

    public AddImageView(Context context) {
        super(context);
    }

    public AddImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
        view = LayoutInflater.from(context).inflate(R.layout.add_image_item,null);
        for (int i = 0 ; i < 10 ; i++){
            addView(view);
        }
    }

    public AddImageView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

    }
}
