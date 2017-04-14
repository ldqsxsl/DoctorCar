package com.doctorcar.mobile.api;

import java.util.UUID;

/**
 * Created by dd on 2017/2/6.
 */

public class Test {

    public static void main(String[]args){
        Integer[] arr1 = {5,6,3,9};
        Double[] arr2 = {15.0,16.0,13.0,19.0};
        String[] arr3 = {"25","4","65"};
        UUID uuid = UUID.randomUUID();
        System.out.println(uuid);
        System.out.println(getMax(arr2)+"------------");
    }

    public static  <T extends Number> T getMax(T array[]){
        T max = null;
        for(T element : array){
            System.out.println(element+"++++++++++");
            if (max == null){
                max = element;
            }else{
                max = element.intValue() > max.intValue() ? element : max;
            }

        }
        return max;
    }
}
