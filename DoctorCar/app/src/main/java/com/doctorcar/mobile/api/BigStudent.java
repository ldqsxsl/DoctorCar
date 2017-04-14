package com.doctorcar.mobile.api;

import java.lang.reflect.ParameterizedType;

/**
 * Created by dd on 2017/2/9.
 */

public class BigStudent extends Student<Girl,Teacher>{
    static  BigStudent bigStudent;
    public BigStudent() {

    }

    public static void main(String[]args){
//        bigStudent = new BigStudent();
//        getT(bigStudent,0);
//        getT(bigStudent,1);
//        girl.friends();
//        teacher.teacher();
//          System.out.print(((ParameterizedType)(new BigStudent().getClass().getGenericSuperclass())).getActualTypeArguments()[1]);
        System.out.print(forName("com.doctorcar.mobile.api.Teacher"));
    }

    public static <T> T getT(Object o, int i) {
        try {
            return ((Class<T>) ((ParameterizedType) (o.getClass()
                    .getGenericSuperclass())).getActualTypeArguments()[i])
                    .newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (ClassCastException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static Class<?> forName(String className) {
        try {
            return Class.forName(className);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

}
