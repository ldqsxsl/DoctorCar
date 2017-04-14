package com.doctorcar.mobile.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.nfc.cardemulation.HostApduService;

import com.doctorcar.mobile.common.baseapp.AppConfig;
import com.parse.signpost.exception.OAuthNotAuthorizedException;

import static com.doctorcar.mobile.common.baseapp.AppConfig.context;

/**
 * Created by dd on 2017/4/13.
 */

public class SPUtils {

    private static final String FILE_NAME = "share";

    public static <T>void setParams(String key,T object){
        String type = object.getClass().getSimpleName();
        SharedPreferences sp = AppConfig.context.getSharedPreferences(FILE_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();

        if ("String".equals(type)){
            editor.putString(key,(String)object);
        }else if ("Integer".equals(type)){
            editor.putInt(key,(Integer)object);
        }else if ("Boolean".equals(type)){
            editor.putBoolean(key,(Boolean)object);
        }else if ("Float".equals(type)){
            editor.putFloat(key,(Float)object);
        }else if ("Long".equals(type)){
            editor.putLong(key,(Long)object);
        }
        editor.commit();
    }

    public static <T> T getParams(String key, T object){
        String type = object.getClass().getSimpleName();
        SharedPreferences sp = AppConfig.context.getSharedPreferences(FILE_NAME, Context.MODE_PRIVATE);

        if ("String".equals(type)){
            return (T) sp.getString(key, "");
        }else if ("Integer".equals(type)){
            Integer i = sp.getInt(key,0);
            return (T) i;
        }else if ("Boolean".equals(type)){
            Boolean b = sp.getBoolean(key,false);
            return (T) b;
        }else if ("Float".equals(type)){
            Float f = sp.getFloat(key,0);
            return (T) f;
        }else if ("Long".equals(type)){
            Long l = sp.getLong(key,0);
            return (T) l;
        }
        return null;
    }


}
