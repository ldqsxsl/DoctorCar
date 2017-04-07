package com.doctorcar.mobile.module.login.model;

import android.util.Log;

import com.doctorcar.mobile.api.Api;
import com.doctorcar.mobile.api.HostType;
import com.doctorcar.mobile.bean.Result;
import com.doctorcar.mobile.bean.User;
import com.doctorcar.mobile.common.basebean.BaseRespose;
import com.doctorcar.mobile.common.baserx.RxHelper;
import com.doctorcar.mobile.common.baserx.RxSchedulers;
import com.doctorcar.mobile.module.login.contract.LoginContract;
import com.doctorcar.mobile.utils.TLUtil;

import java.util.Map;

import rx.Observable;
import rx.Subscriber;
import rx.functions.Action0;
import rx.functions.Action1;
import rx.functions.Func1;



/**
 * Created by dd on 2016/12/14.
 */

public class LoginModel implements LoginContract.Model {
    @Override
    public Observable<User> getLoginData(String username, String password) {
        return Api.getDefault(HostType.LOGIN).login(Api.getCacheControl(),username,password).map(new Func1<BaseRespose<User>, User>() {
            @Override
            public User call(BaseRespose<User> userBaseRespose) {
                TLUtil.showLog(userBaseRespose.toString()+"----------------");
                return userBaseRespose.data;
            }
        }).compose(RxSchedulers.<User>io_main());
    }


    Observable<String> observable = Observable.create(new Observable.OnSubscribe<String>() {
        @Override
        public void call(Subscriber<? super String> subscriber) {
            subscriber.onNext("Hello");
            subscriber.onNext("Hi");
            subscriber.onNext("Aloha");
            subscriber.onCompleted();
        }
    }).map(new Func1<String, String>() {
        @Override
        public String call(String s) {
            return null;
        }
    });

    Action1<String> onNextAction = new Action1<String>() {
        // onNext()
        @Override
        public void call(String s) {

        }
    };
    Action1<Throwable> onErrorAction = new Action1<Throwable>() {
        // onError()
        @Override
        public void call(Throwable throwable) {
            // Error handling
        }
    };
    Action0 onCompletedAction = new Action0() {
        // onCompleted()
        @Override
        public void call() {

        }
    };
    public void dfdf(){
        Subscriber dsf;
        Func1 dsd;
        // 自动创建 Subscriber ，并使用 onNextAction 来定义 onNext()
        observable.subscribe(onNextAction);
// 自动创建 Subscriber ，并使用 onNextAction 和 onErrorAction 来定义 onNext() 和 onError()
        observable.subscribe(onNextAction, onErrorAction);
// 自动创建 Subscriber ，并使用 onNextAction、 onErrorAction 和 onCompletedAction 来定义 onNext()、 onError() 和 onCompleted()
        observable.subscribe(onNextAction, onErrorAction, onCompletedAction);
    }


//    .compose(RxSchedulers.<User>io_main())
    //    @Override
//    public Observable<User> getLoginData(String username, String password) {
//        return Api.getDefault(HostType.LOGIN).login(Api.getCacheControl(),username,password).map(new Func1<BaseRespose<User>, Result>() {
//            @Override
//            public Result call(BaseRespose<User> userBaseRespose) {
//                TLUtil.showLog(userBaseRespose.toString());
//                return null;
//            }
//        }).compose();

//        return Api.getDefault(HostType.NETEASE_NEWS_VIDEO).getNewDetail(Api.getCacheControl(),postId)
//                .map(new Func1<Map<String, NewsDetail>, NewsDetail>() {
//                    @Override
//                    public NewsDetail call(Map<String, NewsDetail> map) {
//                        NewsDetail newsDetail = map.get(postId);
//                        changeNewsDetail(newsDetail);
//                        return newsDetail;
//                    }
//                })
//                .compose(RxSchedulers.<NewsDetail>io_main());

}
