package com.doctorcar.mobile.api;



import com.doctorcar.mobile.bean.NewsDetail;
import com.doctorcar.mobile.bean.UploadImageResult;
import com.doctorcar.mobile.bean.User;
import com.doctorcar.mobile.common.basebean.BaseRespose;
import com.doctorcar.mobile.module.ask.bean.AnswerCommentResult;
import com.doctorcar.mobile.module.ask.bean.AnswerResult;
import com.doctorcar.mobile.module.ask.bean.BrandModelBean;
import com.doctorcar.mobile.module.ask.bean.ProblemResult;
import com.doctorcar.mobile.module.blog.bean.ArticleResult;
import com.doctorcar.mobile.module.register.bean.RegisterResult;

import java.util.List;
import java.util.Map;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.http.DELETE;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
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

public interface ApiService {

    @FormUrlEncoded
    @POST("login")
    Observable<BaseRespose<User>> login(
            @Field("user_phone") String username,
            @Field("user_password") String password);

    @FormUrlEncoded
    @POST("register")
    Observable<BaseRespose<RegisterResult>> register(
            @Field("user_phone") String username,
            @Field("user_password") String password);

    @GET("getBrandList")
    Observable<BaseRespose<BrandModelBean>> getBrandModel(
            @Header("Cache-Control") String cacheControl);

    @GET("getProblemList")
    Observable<BaseRespose<ProblemResult>> getProblem(
            @Query("page") Integer page,
            @Query("page_size") Integer page_size
    );

    @GET("getMyProblemList")
    Observable<BaseRespose<ProblemResult>> getMyProblemList(
            @Query("user_id") String user_id
    );


    @FormUrlEncoded
    @POST("addProblem")
    Observable<BaseRespose<Object>> submitAsk(
            @Field("user_id") String user_id,
            @Field("brand_id") Integer brand_id,
            @Field("model_id") Integer model_id,
            @Field("problem_content") String content,
            @Field("problem_img") String img);

    @FormUrlEncoded
    @POST("addAnswer")
    Observable<BaseRespose<Object>> submitAnswer(
            @Field("user_id") String user_id,
            @Field("problem_id") Integer problem_id,
            @Field("answer_content") String answer_content);

    @GET("getAnswer")
    Observable<BaseRespose<AnswerResult>> getAnswer(
            @Query("user_id") String user_id,
            @Query("problem_id") Integer problem_id,
            @Query("page") Integer page,
            @Query("page_size") Integer page_size
    );

    //问题添加关注
    @GET("addProblemFocus")
    Observable<BaseRespose<Object>> addProblemFocus(
            @Query("user_id") String user_id,
            @Query("problem_id") Integer problem_id
    );

    //问题取消关注

    @GET("deleteProblemFocus")
    Observable<BaseRespose<Object>> deleteProblemFocus(
            @Query("user_id") String user_id,
            @Query("problem_id") Integer problem_id
    );

    @GET("getMyFocusProblemPageList")
    Observable<BaseRespose<ProblemResult>> getMyFocusProblemPageList(
            @Query("user_id") String user_id,
            @Query("page") Integer page,
            @Query("page_size") Integer page_size
    );

    @GET("getNoSolveProblemPageList")
    Observable<BaseRespose<ProblemResult>> getNoSolveProblemPageList(
            @Query("page") Integer page
    );

    //关注一个人

    //取消关注人


    // 给回答 回答的人点赞
    @GET("addAnswerPraise")
    Observable<BaseRespose<Object>> addAnswerPraise(
            @Query("answer_id") Integer answer_id,
            @Query("answer_user_id") String answer_user_id,
            @Query("praise_user_id") String praise_user_id,
            @Query("problem_id") Integer problem_id
    );

    //取消给回答 和回的人点赞
    @GET("deleteAnswerPraise")
    Observable<BaseRespose<Object>> deleteAnswerPraise(
            @Query("answer_id") Integer answer_id,
            @Query("praise_user_id") String praise_user_id
    );

    @FormUrlEncoded
    @POST("addCommentAnswer")
    Observable<BaseRespose<Object>> submitCommentAnswer(
            @Field("answer_id") Integer answer_id,
            @Field("user_id") String user_id,
            @Field("comment_answer_content") String comment_answer_content);

    @GET("getCommentAnswer")
    Observable<BaseRespose<AnswerCommentResult>> getAnswerComment(
            @Query("answer_id") Integer answer_id,
            @Query("page") Integer page,
            @Query("page_size") Integer page_size
    );

    @GET("getMyAnswer")
    Observable<BaseRespose<AnswerCommentResult>> getMyAnswer(
            @Query("problem_id") Integer problem_id,
            @Query("user_id") String user_id
    );

    @FormUrlEncoded
    @POST("addArticle")
    Observable<BaseRespose<Object>> addArticle(
            @Field("user_id") String user_id,
            @Field("article_title")String article_title,
            @Field("article_content")String article_content,
            @Field("article_privacy")String article_privacy
    );

    @DELETE("deleteArticle")
    Observable<BaseRespose<Object>> deleteArticle(
            @Query("article_id")Integer article_id
    );

    @GET("getArticleList")
    Observable<BaseRespose<ArticleResult>> getArticleList(
            @Query("user_id") String user_id,
            @Query("page") Integer page,
            @Query("page_size") Integer page_size
    );

    @GET("getSelectArticleList")
    Observable<BaseRespose<ArticleResult>> getSelectArticleList(
            @Query("page") Integer page
    );

    @FormUrlEncoded
    @POST("addFeedback")
    Observable<BaseRespose<Object>> addFeedback(
            @Field("user_id") String user_id,
            @Field("feedback_content") String content);


    @Multipart
    @POST("upload")
    Observable<BaseRespose<UploadImageResult>>uploadImage(@PartMap Map<String , RequestBody> file);

    @Multipart
    @POST("upload")
    Observable<BaseRespose<UploadImageResult>>uploadSingleImage(@Part("file\"; filename=\"image.png") RequestBody file);

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
