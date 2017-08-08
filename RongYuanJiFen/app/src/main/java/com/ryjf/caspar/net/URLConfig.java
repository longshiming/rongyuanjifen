package com.ryjf.caspar.net;


public interface URLConfig {

    /**
     * 请求头
     */
    String REQUEST_HEAD_VERSION = "appVersion";
    //    public static final String REQUEST_HEAD_SECRETKEY = "secretKey";
//    public static final String REQUEST_HEAD_TOKEN = "token";
    String REQUEST_HEAD_CHANNEL = "client";
    String REQUEST_HEAD_SIGN = "sign";

//    public static final int CONNECT_TIMEOUT = 3000;
//    public static final int MAX_RETRY_COUNT = 2;

// ================================================================================================ //


    String URL_PREFIX_HTTP = "http://app.szzztl.com/";// 开发环境

//    String URL_PREFIX_HTTP = "http://wdapi.szzztl.com/";// 测试环境

    //            String URL_ROOT = URL_PREFIX_HTTP + "jinyao.api/api/"; // 生产环境
    String URL_ROOT = URL_PREFIX_HTTP + "api/";      //测试


}
