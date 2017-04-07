package com.doctorcar.mobile.api;



import com.doctorcar.mobile.bean.NewsDetail;
import com.doctorcar.mobile.bean.User;
import com.doctorcar.mobile.common.basebean.BaseRespose;
import com.doctorcar.mobile.module.ask.bean.BrandModelBean;

import java.util.List;
import java.util.Map;

import okhttp3.MultipartBody;
import okhttp3.ResponseBody;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.PartMap;
import retrofit2.http.Path;
import retrofit2.http.Query;
import retrofit2.http.Url;
import rx.Observable;

/**
 * des:ApiService
 * Created by xsf
 * on 2016.06.15:47
 */
public interface ApiService {

    @GET("login")
    Observable<BaseRespose<User>> login(
            @Header("Cache-Control") String cacheControl,
            @Query("loginName") String username,
            @Query("pwd") String password);

    @GET("getBrandList")
    Observable<BaseRespose<BrandModelBean>> getBrandModel(
            @Header("Cache-Control") String cacheControl);

    @GET("submitAsk")
    Observable<BaseRespose<Object>> submitAsk(
            @Header("Cache-Control") String cacheControl,
            @Query("brand_id") String brand_id,
            @Query("model_id") String model_id,
            @Query("content") String content,
            @Query("img") String img);
    @Multipart
    @POST("upload")
    Observable<BaseRespose<Object>>uploadImage(@PartMap Map<String ,ResponseBody> file);

//    @GET("nc/article/{postId}/full.html")
//    Observable<Map<String, NewsDetail>> getNewDetail(
//            @Header("Cache-Control") String cacheControl,
//            @Path("postId") String postId);

//    @GET("nc/article/{type}/{id}/{startPage}-20.html")
//    Observable<Map<String, List<NewsSummary>>> getNewsList(
//            @Header("Cache-Control") String cacheControl,
//            @Path("type") String type, @Path("id") String id,
//            @Path("startPage") int startPage);
//
//    @GET
//    Observable<ResponseBody> getNewsBodyHtmlPhoto(
//            @Header("Cache-Control") String cacheControl,
//            @Url String photoPath);
//    //@Url，它允许我们直接传入一个请求的URL。这样以来我们可以将上一个请求的获得的url直接传入进来，baseUrl将被无视
//    // baseUrl 需要符合标准，为空、""、或不合法将会报错
//
//    @GET("data/福利/{size}/{page}")
//    Observable<GirlData> getPhotoList(
//            @Header("Cache-Control") String cacheControl,
//            @Path("size") int size,
//            @Path("page") int page);
//
//    @GET("nc/video/list/{type}/n/{startPage}-10.html")
//    Observable<Map<String, List<VideoData>>> getVideoList(
//            @Header("Cache-Control") String cacheControl,
//            @Path("type") String type,
//            @Path("startPage") int startPage);


}
