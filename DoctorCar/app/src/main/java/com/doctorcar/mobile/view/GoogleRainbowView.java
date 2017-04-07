package com.doctorcar.mobile.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import com.doctorcar.mobile.R;
import com.doctorcar.mobile.common.baseapp.AppConfig;
import com.doctorcar.mobile.utils.TLUtil;
import com.doctorcar.mobile.utils.ViewUtil;

import butterknife.internal.Utils;


/**
 * Created by dd on 2017/3/7.
 */

public class GoogleRainbowView extends View{

    //progress bar color
    int barColor = Color.parseColor("#1E88E5");
    //every bar segment width
    int hSpace = ViewUtil.dip2px(AppConfig.getContext(),80);
    //every bar segment height
    int vSpace = ViewUtil.dip2px(AppConfig.getContext(),4);
    //space among bars
    int space = ViewUtil.dip2px(AppConfig.getContext(),10);
    float startX = 0;
    float delta = 10f;
    Paint mPaint;
    int index = 0;

    public GoogleRainbowView(Context context) {
        super(context);

    }

    public GoogleRainbowView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        TypedArray t = context.obtainStyledAttributes(attrs,
                R.styleable.rainbowbar, 0, 0);
        hSpace = t.getDimensionPixelSize(R.styleable.rainbowbar_rainbowbar_hspace, hSpace);
        vSpace = t.getDimensionPixelOffset(R.styleable.rainbowbar_rainbowbar_vspace, vSpace);
        barColor = t.getColor(R.styleable.rainbowbar_rainbowbar_color, barColor);
        t.recycle();   // we should always recycle after used
        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setColor(barColor);
        mPaint.setStrokeWidth(vSpace);
    }

    public GoogleRainbowView(Context context, AttributeSet attrs) {
        super(context, attrs);
        TypedArray t = context.obtainStyledAttributes(attrs,
                R.styleable.rainbowbar, 0, 0);
        hSpace = t.getDimensionPixelSize(R.styleable.rainbowbar_rainbowbar_hspace, hSpace);
        vSpace = t.getDimensionPixelOffset(R.styleable.rainbowbar_rainbowbar_vspace, vSpace);
        barColor = t.getColor(R.styleable.rainbowbar_rainbowbar_color, barColor);
        t.recycle();   // we should always recycle after used
        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setColor(barColor);
        mPaint.setStrokeWidth(vSpace);
    }

    /**
     * 计算View需要多大面积
     * @param widthMeasureSpec
     * @param heightMeasureSpec
     */
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        setMeasuredDimension(getDefaultSize(getSuggestedMinimumWidth(), widthMeasureSpec),
                getDefaultSize(getSuggestedMinimumHeight(), heightMeasureSpec));

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        TLUtil.showLog("000000000000000000000000");
        float sw = this.getMeasuredWidth();
        if (startX >= sw + (hSpace + space) - (sw % (hSpace + space))) {
            startX = 0;
        } else {
            startX += delta;
        }
        float start = startX;
        // draw latter parse
        while (start < sw) {
            TLUtil.showLog("1111111111111111111");
            canvas.drawLine(start, 5, start + hSpace, 5, mPaint);
            start += (hSpace + space);
        }

        start = startX - space - hSpace;

        // draw front parse
        while (start >= -hSpace) {
            TLUtil.showLog("22222222222222222");
            canvas.drawLine(start, 5, start + hSpace, 5, mPaint);
            start -= (hSpace + space);
        }
        if (index >= 700000) {
            index = 0;
        }
        invalidate();
    }


    public static int getDefaultSize(int size, int measureSpec) {
        int result = size;
        int specMode = MeasureSpec.getMode(measureSpec);
        int specSize = MeasureSpec.getSize(measureSpec);

        switch (specMode) {
            case MeasureSpec.UNSPECIFIED:
                result = size;
                break;
            case MeasureSpec.AT_MOST:
            case MeasureSpec.EXACTLY:
                result = specSize;
                break;
        }
        return result;
    }
}
