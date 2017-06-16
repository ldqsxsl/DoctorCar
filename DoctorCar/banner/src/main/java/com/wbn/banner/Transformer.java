package com.wbn.banner;

import android.support.v4.view.ViewPager.PageTransformer;

import com.wbn.banner.transformer.AccordionTransformer;
import com.wbn.banner.transformer.BackgroundToForegroundTransformer;
import com.wbn.banner.transformer.CubeInTransformer;
import com.wbn.banner.transformer.CubeOutTransformer;
import com.wbn.banner.transformer.DefaultTransformer;
import com.wbn.banner.transformer.DepthPageTransformer;
import com.wbn.banner.transformer.FlipHorizontalTransformer;
import com.wbn.banner.transformer.FlipVerticalTransformer;
import com.wbn.banner.transformer.ForegroundToBackgroundTransformer;
import com.wbn.banner.transformer.RotateDownTransformer;
import com.wbn.banner.transformer.RotateUpTransformer;
import com.wbn.banner.transformer.ScaleInOutTransformer;
import com.wbn.banner.transformer.StackTransformer;
import com.wbn.banner.transformer.TabletTransformer;
import com.wbn.banner.transformer.ZoomInTransformer;
import com.wbn.banner.transformer.ZoomOutSlideTransformer;
import com.wbn.banner.transformer.ZoomOutTranformer;


public class Transformer {
    public static Class<? extends PageTransformer> Default = DefaultTransformer.class;
    public static Class<? extends PageTransformer> Accordion = AccordionTransformer.class;
    public static Class<? extends PageTransformer> BackgroundToForeground = BackgroundToForegroundTransformer.class;
    public static Class<? extends PageTransformer> ForegroundToBackground = ForegroundToBackgroundTransformer.class;
    public static Class<? extends PageTransformer> CubeIn = CubeInTransformer.class;
    public static Class<? extends PageTransformer> CubeOut = CubeOutTransformer.class;
    public static Class<? extends PageTransformer> DepthPage = DepthPageTransformer.class;
    public static Class<? extends PageTransformer> FlipHorizontal = FlipHorizontalTransformer.class;
    public static Class<? extends PageTransformer> FlipVertical = FlipVerticalTransformer.class;
    public static Class<? extends PageTransformer> RotateDown = RotateDownTransformer.class;
    public static Class<? extends PageTransformer> RotateUp = RotateUpTransformer.class;
    public static Class<? extends PageTransformer> ScaleInOut = ScaleInOutTransformer.class;
    public static Class<? extends PageTransformer> Stack = StackTransformer.class;
    public static Class<? extends PageTransformer> Tablet = TabletTransformer.class;
    public static Class<? extends PageTransformer> ZoomIn = ZoomInTransformer.class;
    public static Class<? extends PageTransformer> ZoomOut = ZoomOutTranformer.class;
    public static Class<? extends PageTransformer> ZoomOutSlide = ZoomOutSlideTransformer.class;
}
