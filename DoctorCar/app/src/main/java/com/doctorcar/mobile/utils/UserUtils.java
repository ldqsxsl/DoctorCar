package com.doctorcar.mobile.utils;

import android.text.TextUtils;

import com.alibaba.fastjson.JSON;
import com.doctorcar.mobile.bean.User;

/**
 * Created by dd on 2017/4/19.
 */

public class UserUtils {
    public static  User getUser(){
        String str = SPUtils.getParams("user","");
        if (!TextUtils.isEmpty(str)){
            User user = JSON.parseObject(str,User.class);
            return user;
        }else{
            return null;
        }
    }
}
