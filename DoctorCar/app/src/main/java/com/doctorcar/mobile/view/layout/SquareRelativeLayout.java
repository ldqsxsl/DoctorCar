package com.doctorcar.mobile.view.layout;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.RelativeLayout;

/**
 * Created by dd on 2017/3/16.
 */

public class SquareRelativeLayout extends RelativeLayout{

    public SquareRelativeLayout(Context context) {
        super(context);
    }

    public SquareRelativeLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public SquareRelativeLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        //设置自己测量结果
        setMeasuredDimension(getDefaultSize(0,widthMeasureSpec),getDefaultSize(0,heightMeasureSpec));
        /**
         * 测量子View的
         */
        int childWidthSize=getMeasuredWidth();
        //高度与宽度一样
        widthMeasureSpec =MeasureSpec.makeMeasureSpec(childWidthSize,MeasureSpec.EXACTLY);
        heightMeasureSpec =widthMeasureSpec;
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }
}
