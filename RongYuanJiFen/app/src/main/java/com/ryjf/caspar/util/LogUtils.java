/**
 * @(#) com.kfx.e_news.utils 2015/10/22;
 * <p>
 * Copyright (c), 2009 深圳孔方兄金融信息服务有限公司（Shenzhen kfxiong
 * Financial Information Service Co. Ltd.）
 * <p>
 * 著作权人保留一切权利，任何使用需经授权。
 */
package com.ryjf.caspar.util;

import android.util.Log;

/**
 * @Desc: 日志打印工具类
 * <p>
 * Created by Sye on 2015/10/22.
 */
public class LogUtils {

    public static final int LEVEL_VERBOSE = 1;
    public static final int LEVEL_DEBUG = 2;

    public static final int LEVEL_INFO = 3;
    public static final int LEVEL_WARN = 4;
    public static final int LEVEL_ERROR = 5;

    //TODO 发布生产环境的时候需切换
//    private static final int LOG_TAG = 6;      //关闭log日志
    private static final int LOG_TAG = 0;        //打开log日志

    public static void v(String tag, String msg) {
        if (LEVEL_VERBOSE > LOG_TAG) {
            Log.v(tag, msg);
        }
    }

    public static void d(String tag, String msg) {
        if (LEVEL_DEBUG > LOG_TAG) {
            Log.d(tag, msg);
        }
    }

    public static void i(String tag, String msg) {
        if (LEVEL_INFO > LOG_TAG) {
            Log.i(tag, msg);
        }
    }

    public static void w(String tag, String msg) {
        if (LEVEL_WARN > LOG_TAG) {
            Log.w(tag, msg);
        }
    }

    public static void e(String tag, String msg) {
        if (LEVEL_ERROR > LOG_TAG) {
            Log.e(tag, msg);
        }
    }

}
