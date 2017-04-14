package com.doctorcar.mobile.view.layout;

import android.content.Context;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewCompat;
import android.support.v4.widget.DrawerLayout;
import android.util.AttributeSet;
import android.util.Log;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;

/**
 * 作者：jack on 2016/5/25 22:27.
 * 功能描述：自定义DrawerLayout,调整Drawerlayout事件拦截逻辑
 */
public class CustomDrawerLayout extends DrawerLayout {

    public CustomDrawerLayout(Context context){
        this(context, null);
    }

    public CustomDrawerLayout(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CustomDrawerLayout(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs,defStyle);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev){
        switch(ev.getAction()) {
            case MotionEvent.ACTION_DOWN:
                Log.i("OUTPUT","11111111111111111111111111111");
                final float x = ev.getX();
                final float y = ev.getY();
                final View touchedView = findTopChildUnder((int) x, (int) y);
                Log.i("OUTPUT","=======111========="+isContentView(touchedView));
                Log.i("OUTPUT","=======222========="+this.isDrawerOpen(GravityCompat.END));
                if (touchedView != null && isContentView(touchedView)
                        && this.isDrawerOpen(GravityCompat.END)) {
                    Log.i("OUTPUT","======333==========");
                    return false;
                }
                break;
            case MotionEvent.ACTION_MOVE:
                Log.i("OUTPUT","333333333333333333333333333333333");
                break;
            case MotionEvent.ACTION_CANCEL:
                Log.i("OUTPUT","44444444444444444444444444444444");
                break;
            case MotionEvent.ACTION_UP:
                Log.i("OUTPUT","555555555555555555555555555555555");
                break;

            default:
                break;
        }
        return super.onInterceptTouchEvent(ev);
    }

    /**
     * 判断点击位置是否位于相应的View内
     * @param x
     * @param y
     * @return
     */
    public View findTopChildUnder(int x, int y) {
        final int childCount = getChildCount();
        Log.i("OUTPUT",childCount+"--------------");
        for (int i = childCount - 1; i >= 0; i--) {
            final View child = getChildAt(i);
            if (x >= child.getLeft() && x < child.getRight() &&
                    y >= child.getTop() && y < child.getBottom()) {
                return child;
            }
        }
        return null;
    }

    /**
     * 判断点击触摸点的View是否是ContentView(即是主页面的View)
     * @param child
     * @return
     */
    boolean isContentView(View child) {
        return ((LayoutParams) child.getLayoutParams()).gravity == Gravity.NO_GRAVITY;
    }

    /**
     * 判断点击触摸点的View是否是DrawerView(即是侧边栏的View)
     * @param child
     * @return
     */
    boolean isDrawerView(View child) {
        final int gravity = ((LayoutParams) child.getLayoutParams()).gravity;
        final int absGravity = GravityCompat.getAbsoluteGravity(gravity,
                ViewCompat.getLayoutDirection(child));
        if ((absGravity & Gravity.LEFT) != 0) {
            // This child is a left-edge drawer
            return true;
        }
        if ((absGravity & Gravity.RIGHT) != 0) {
            // This child is a right-edge drawer
            return true;
        }
        return false;
    }

}


