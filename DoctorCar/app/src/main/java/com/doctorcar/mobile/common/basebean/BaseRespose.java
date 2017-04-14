package com.doctorcar.mobile.common.basebean;

import java.io.Serializable;

/**
 * des:封装服务器返回数据
 * Created by xsf
 * on 2016.09.9:47
 */
public class BaseRespose<T> implements Serializable {

    public String status;
    public String message;
//    {"data":{"loginName":"jiaoxu","id":95},"status":"1","message":"成功"}
    public T data;

    public boolean success() {
        return "1".equals(status);
    }

    @Override
    public String toString() {
        return "BaseRespose{" +
                "status='" + status + '\'' +
                ", message='" + message + '\'' +
                ", data=" + data +
                '}';
    }
}
