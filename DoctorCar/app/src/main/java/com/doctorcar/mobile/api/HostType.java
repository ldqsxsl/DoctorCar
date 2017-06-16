/*
 * Copyright (c) 2016 咖枯 <kaku201313@163.com | 3772304@qq.com>
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */
package com.doctorcar.mobile.api;

public class HostType {

    /**
     * 多少种Host类型
     */
    public static final int TYPE_COUNT = 3;

    //登陆
    public static final int LOGIN = 5;
    //获取品牌和型号
    public static final int GET_BRAND_LIST = 6;
    //提交问题
    public static final int ADD_PROBLEM = 7;
    //上传图片
    public static final int UPLOAD = 8 ;
   //注册
    public static final int REGISTER = 9;
    //获取问题列表
    public static final int GET_PROBLEM_LIST = 10;

    public static final int ADD_ANSWER = 11;

    public static final int GET_ANSWER_LIST = 12;

    public static final int ADD_COMMENT_ANSWER = 13;

    public static  final int GET_COMMENT_ANSWER_LIST = 14;

    public static final int FOCUS = 15;

    public static final int ADD_ARTICLE = 16;

    public static final int GET_ARTICLE_LIST =17;

    public static final int GET_MY_PROBLEM_LIST = 18;

    public static final int DELETE_ARTICLE = 19;

    public static final int ADD_ANSWER_PRAISE = 20;

    public static final int DELETE_ANSWER_PRAISE = 21;

    public static final int GET_MY_ANSWER = 22;

    public static  final int GET_MY_FOCUS_PROBLEM_PAGE_LIST = 23;

    public static final int GET_NO_SOLVE_PROBLEM_PAGE_LIST = 24;

    public static final int ADD_FEEDBACK = 25;

    public static final int GET_SELECT_BLOG_LIST = 26;


    /**
     * 网易新闻视频的host
     */
    public static final int NETEASE_NEWS_VIDEO = 1;

    /**
     * 新浪图片的host
     */
    public static final int GANK_GIRL_PHOTO = 2;

    /**
     * 新闻详情html图片的host
     */
    public static final int NEWS_DETAIL_HTML_PHOTO = 3;

}
