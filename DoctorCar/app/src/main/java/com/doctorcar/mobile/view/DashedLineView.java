package com.doctorcar.mobile.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.DashPathEffect;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PathEffect;
import android.util.AttributeSet;
import android.view.View;

import com.doctorcar.mobile.R;
import com.doctorcar.mobile.common.commonutils.DisplayUtil;

import static android.R.attr.path;

/**
 * Created by dd on 2017/5/2.
 */

public class DashedLineView extends View{

    private Paint paint = null;
    private Path path = null;
    private PathEffect pe = null;


    public DashedLineView(Context context, AttributeSet attrs) {
        super(context, attrs);
        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.dashed_line);
        int lineColor = a.getColor(R.styleable.dashed_line_lineColor,0XFFcccccc);
        a.recycle();
        this.paint = new Paint();
        this.path = new Path();
        this.paint.setStyle(Paint.Style.STROKE);
        this.paint.setColor(lineColor);
        this.paint.setAntiAlias(true);
        this.paint.setStrokeWidth(DisplayUtil.dip2px(14.0f));
        float[]arrayOfFloat = new float[4];
        arrayOfFloat[0] = DisplayUtil.dip2px(6.0f);
        arrayOfFloat[1] = DisplayUtil.dip2px(6.0f);
        arrayOfFloat[2] = DisplayUtil.dip2px(6.0f);
        arrayOfFloat[3] = DisplayUtil.dip2px(6.0f);
        this.pe = new DashPathEffect(arrayOfFloat,DisplayUtil.dip2px(6.0f));
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        this.path.moveTo(0.0f,0.0f);
        this.path.lineTo(getMeasuredWidth(), 0.0f);
        this.paint.setPathEffect(this.pe);
        canvas.drawPath(this.path,this.paint);



//        Paint paint = new Paint();
//        paint.setStyle(Paint.Style.STROKE);
//        paint.setColor(Color.BLUE);
//        Path path = new Path();
//        path.moveTo(0,10);
//        path.lineTo(480,10);
//        PathEffect effect = new DashPathEffect(new float[]{5,5,5,5},1);
//        paint.setPathEffect(effect);
//        canvas.drawPath(path , paint);
    }
}
