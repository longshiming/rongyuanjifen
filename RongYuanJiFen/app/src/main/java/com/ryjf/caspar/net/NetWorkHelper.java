/**
 * @(#) com.kfx.net 2015/12/10;
 * <p>
 * Copyright (c), 2009 深圳孔方兄金融信息服务有限公司（Shenzhen kfxiong
 * Financial Information Service Co. Ltd.）
 * <p>
 * 著作权人保留一切权利，任何使用需经授权。
 */
package com.ryjf.caspar.net;

import android.app.Activity;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.annotation.NonNull;
import android.text.TextUtils;
import android.util.Log;

import com.google.gson.Gson;
import com.ryjf.caspar.R;
import com.ryjf.caspar.bean.BaseBean;
import com.ryjf.caspar.util.AppInfoUtils;
import com.ryjf.caspar.util.LogUtils;
import com.ryjf.caspar.util.ToastUtils;
import com.ryjf.caspar.util.UiUtils;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import java.net.SocketTimeoutException;
import java.util.HashMap;

import rx.Observable;
import rx.functions.Func1;
import z.sye.space.library.OkHttpManager;
import z.sye.space.library.response.ResponseCallBack;

/**
 * Created by Syehunter on 2015/12/10.
 */
public class NetWorkHelper {

    //    private static Map<String, String> mPropertyMap = new HashMap<>();
    private static final String mSecretKey = "dj10ka3i9asdfmcfaldjj30j";

    private static final String TAG = NetWorkHelper.class.getSimpleName();

//    private static Properties mProperties = new Properties();
    /**
     * url + jsonBody
     */
    private static String mSecretKeyBody = "";
    public static final Gson gson = new Gson();

    private static Observable<HashMap<String, String>> mPostObservable() {
        return Observable.defer(() -> {
            HashMap<String, String> httpHeader = new HashMap<>();
            httpHeader.put(URLConfig.REQUEST_HEAD_VERSION, AppInfoUtils.getAppVersionName());
//            httpHeader.put(Constants.REQUEST_HEAD_SECRETKEY, MD5Utils.md5("POST" + mSecretKeyBody + mSecretKey));
//            httpHeader.put(URLConfig.REQUEST_HEAD_CHANNEL, "Android");
//            String token = UserStatus.token;
//            if (!TextUtils.isEmpty(token)) {
//                httpHeader.put(Constants.REQUEST_HEAD_TOKEN, token);
//            }
            return Observable.just(httpHeader);
        });
    }

    /**
     * 发送get请求
     *
     * @param url
     * @param responseCallBack
     */
    public static void getRequest(@NonNull String url, @NonNull ResponseCallBack<String> responseCallBack) {
        HashMap<String, String> header = new HashMap<>();
        header.put("X-Requested-With", "XMLHttpRequest");

        OkHttpManager.url(url)
                .addHeader(header)
                .callback(responseCallBack)
                .getEnqueue();
    }

    /**
     * 发送post请求
     *
     * @param url
     * @param paramas
     * @param responseCallBack
     */
    public static void postRequest(@NonNull String url,
//                                   @NonNull JSONObject jsonObject,
                                   @NonNull HashMap<String, String> paramas,
                                   @NonNull ResponseCallBack<String> responseCallBack) {
        if (!isNetworkAvailable()) {
            //当前网络不可用
            responseCallBack.onFailureCallBack(null, null, null);
            return;
        }

//        mSecretKeyBody = url + jsonObject.toString();
//        TelephonyManager mTm = (TelephonyManager) UiUtils.getContext().getSystemService(TELEPHONY_SERVICE);

        paramas.put(URLConfig.REQUEST_HEAD_VERSION, AppInfoUtils.getAppVersionName());
        paramas.put(URLConfig.REQUEST_HEAD_CHANNEL, "Android");


//        if (!TextUtils.isEmpty(mTm.getDeviceId())) {
//            paramas.put("imei", mTm.getDeviceId());
//            LogUtils.i("lsm", "mTm.getDeviceId() == " + mTm.getDeviceId());
//        }
//        if (!TextUtils.isEmpty(mTm.getSubscriberId())) {
//            paramas.put("imsi", mTm.getSubscriberId());
//            LogUtils.i("lsm", "mTm.getSubscriberId() == " + mTm.getSubscriberId());
//        }
//        if (!TextUtils.isEmpty(android.os.Build.MANUFACTURER)) {
//            paramas.put("phoneName", android.os.Build.MANUFACTURER);
//            LogUtils.i("lsm", "android.os.Build.MANUFACTURER == " + android.os.Build.MANUFACTURER);
//        }
//        if (!TextUtils.isEmpty(android.os.Build.MODEL)) {
//            paramas.put("mtype", android.os.Build.MODEL);   //手机型号
//            LogUtils.i("lsm", "android.os.Build.MODEL == " + android.os.Build.MODEL);
//        }
//        if (!TextUtils.isEmpty(android.os.Build.VERSION.RELEASE)) {
//            paramas.put("phoneSystemVersion", android.os.Build.VERSION.RELEASE);
//            LogUtils.i("lsm", "android.os.Build.VERSION.RELEASE == " + android.os.Build.VERSION.RELEASE);
//        }
//        if (!TextUtils.isEmpty(DeviceUtils.getLocalMacAddressFromWifiInfo(UiUtils.getContext()))) {
//            paramas.put("phoneMac", DeviceUtils.getLocalMacAddressFromWifiInfo(UiUtils.getContext()));
//            LogUtils.i("lsm", "DeviceUtils.getLocalMacAddressFromWifiInfo(UiUtils.getContext()) == " + DeviceUtils.getLocalMacAddressFromWifiInfo(UiUtils.getContext()));
//        }

//        List<Map.Entry<String, String>> list = new ArrayList<Map.Entry<String, String>>(paramas.entrySet());
//        Collections.sort(list, new Comparator<Map.Entry<String, String>>() {
//            //升序排序
//            public int compare(Map.Entry<String, String> o1,
//                               Map.Entry<String, String> o2) {
//                return o1.getKey().compareTo(o2.getKey());
//            }
//
//        });
//        StringBuffer sign = new StringBuffer();
//        sign.append("{");
//        for (int i = 0; i < list.size() - 1; i++) {
//            sign.append(list.get(i).getKey()).append("=").append(list.get(i).getValue()).append(",");
//        }
//        sign.append(list.get(list.size() - 1).getKey()).append("=").append(list.get(list.size() - 1).getValue());
//        sign.append("}");
//        LogUtils.i("lsm", "sign.toString() == " + sign.toString());
//        paramas.put(URLConfig.REQUEST_HEAD_SIGN, MD5.md5("POST" + mSecretKey + url + sign.toString() + mSecretKey));
//        LogUtils.i("lsm", "REQUEST_HEAD_SIGN == " + "POST" + mSecretKey + url + sign.toString() + mSecretKey);
//        LogUtils.i("lsm", "REQUEST_HEAD_SIGN jiamihou == " + MD5.md5("POST" + mSecretKey + url + sign.toString() + mSecretKey));
        mPostObservable()
                .subscribe(header -> {
                    OkHttpManager.url(url)
                            .addHeader(header)
                            .formBody(paramas)
//                    .json(jsonObject)
                            .callback(responseCallBack)
                            .postEnqueue();
                })
                .unsubscribe();
    }

    /**
     * 处理response
     *
     * @param result    json
     * @param beanClazz JsonBean
     */
    public static void onResponse(@NonNull String result,
                                  @NonNull Class<? extends BaseBean> beanClazz) {
        onResponse(result, beanClazz, null);
    }


    /**
     * 处理response
     *
     * @param result                    json
     * @param beanClazz                 JsonBean
     * @param onResponseSuccessListener
     */
    public static void onResponse(@NonNull String result,
                                  @NonNull Class<? extends BaseBean> beanClazz,
                                  OnResponseSuccessListener onResponseSuccessListener) {
        onResponse(result, beanClazz, onResponseSuccessListener, null, null);
    }

    /**
     * 处理response
     *
     * @param result                    json
     * @param beanClazz                 JsonBean
     * @param activity                  指定返回ResultCode为需要重新登录时要弹出窗口的activiy
     * @param onResponseSuccessListener
     */
    public static void onResponse(@NonNull String result,
                                  @NonNull Class<? extends BaseBean> beanClazz,
                                  OnResponseSuccessListener onResponseSuccessListener,
                                  Activity activity) {
        onResponse(result, beanClazz, onResponseSuccessListener, null, activity);
    }

    /**
     * 处理response
     * --需要对某些错误的resultCode单独处理时调用此方法
     *
     * @param result                    json
     * @param beanClazz                 JsonBean
     * @param onResponseSuccessListener
     * @param onResponseFailureListener
     */
    public static void onResponse(@NonNull String result,
                                  @NonNull Class<? extends BaseBean> beanClazz,
                                  OnResponseSuccessListener onResponseSuccessListener,
                                  OnResponseFailureListener onResponseFailureListener) {
        onResponse(result, beanClazz, onResponseSuccessListener, onResponseFailureListener, null);
    }

    /**
     * 处理response
     * --需要对某些错误的resultCode单独处理时调用此方法
     *
     * @param result                    json
     * @param beanClazz                 JsonBean
     * @param activity                  指定返回需要重新登录时要弹出窗口的activiy
     * @param onResponseSuccessListener
     * @param onResponseFailureListener
     */
    public static void onResponse(@NonNull String result,
                                  @NonNull Class<? extends BaseBean> beanClazz,
                                  OnResponseSuccessListener onResponseSuccessListener,
                                  OnResponseFailureListener onResponseFailureListener,
                                  Activity activity) {
        Observable.just(result)
//                    .flatMap(json -> Observable.just(gson.fromJson(json, beanClazz)))
                .flatMap(new Func1<String, Observable<BaseBean>>() {
                    @Override
                    public Observable<BaseBean> call(String json) {
                        try {
                            return Observable.just(gson.fromJson(json, beanClazz));
                        } catch (Exception e) {
                            e.printStackTrace();
                            LogUtils.e(TAG, "返回数据有误 result == " + result);
                        }
                        return null;
                    }
                })
                .subscribe(bean -> {
                    if (bean != null) {
                        Integer resultCode = bean.getStatus();
                        if (200 != resultCode) {
                            //response异常
                            if (resultCode == 219 || resultCode == 519) {
                                LogUtils.i("onResponse", "resultCode == " + resultCode);

//                                RxBus.getInstance().send(Events.LoginByOtner, new LoginByOtherEvent());

//                                PreferenceHelper.write(UiUtils.getContext(), "oxbix", "online", false);  //是否已登录
//                            PreferenceHelper.write(UiUtils.getContext(), "oxbix", "token", "");
                            } else {
                                LogUtils.i("onResponse", "其他类型response异常");
                                onResponseException(bean, onResponseFailureListener);
                            }
                        } else {
                            //请求成功
                            if (null != onResponseSuccessListener) {
                                onResponseSuccessListener.onResponseSuccess(bean);
                            }
                        }
                    }

                })
                .unsubscribe();

    }

    /**
     * 处理失败信息
     *
     * @param request
     * @param response
     * @param e
     */
    public static void onFailure(Request request, Response response, Exception e) {
        if (!isNetworkAvailable()) {
            ToastUtils.show(UiUtils.getContext(), UiUtils.getString(R.string.no_internet));
            return;
        }

        if (null != request) {
            Log.e("NetWorkHelper", "[Request Failed]Request: " + request.toString());
        }

        if (e instanceof SocketTimeoutException) {
            ToastUtils.show(UiUtils.getContext(), "连接超时!");
            Log.e("NetWorkHelper", "Connection Time Out!");
            return;
        }

        Observable.just(response)
                .filter(r -> null != r)
                .flatMap(code -> Observable.just(response.code()))
                .subscribe(code -> {
                    LogUtils.e("NetWorkError", "ResponseCode --> " + code + ": " + e.toString());
                    if (code == 401 || code == 404 || code == 422) {
                        ToastUtils.show(UiUtils.getContext(), UiUtils.getString(R.string.generic_error));
                    } else if (code == 500) {
                        ToastUtils.show(UiUtils.getContext(), UiUtils.getString(R.string.generic_server_down));
                    } else {
                        ToastUtils.show(UiUtils.getContext(), "连接超时!");
                    }
                })
                .unsubscribe();
    }

    /**
     * 请求成功，resultCode = 0时调用
     */
    public interface OnResponseSuccessListener<T extends BaseBean> {
        void onResponseSuccess(T bean);
    }

    /**
     * 请求成功，resultCode != 0时调用
     */
    public interface OnResponseFailureListener<T extends BaseBean> {
        boolean onResponseFailure(T bean);
    }

    /**
     * response异常
     *
     * @param bean
     * @param onResponseFailureListener
     */
    private static void onResponseException(BaseBean bean, OnResponseFailureListener onResponseFailureListener) {
        Observable.just(bean)
                .flatMap(b -> {
                    String errorPrompt = bean.getMessage();
                    LogUtils.e("NetWorkHelper".toString(), "resultCode == " +
                            b.getStatus() + ": " + errorPrompt);
                    boolean intercept = false;
                    if (null != onResponseFailureListener) {
                        //判断是否有对resultCode的拦截
                        intercept = onResponseFailureListener.onResponseFailure(b);
                    }
                    return intercept ? Observable.just("") : Observable.just(null == errorPrompt ? "" : errorPrompt);
                })
                .subscribe(errorPrompt -> {
                    if (!TextUtils.isEmpty(errorPrompt)) {
                        ToastUtils.show(UiUtils.getContext(), errorPrompt);
                    }

                })
                .unsubscribe();
    }

    /**
     * 判断当前网络是否可用
     *
     * @return
     */
    public static boolean isNetworkAvailable() {
        ConnectivityManager cm = (ConnectivityManager) UiUtils.getContext().getSystemService(Context.CONNECTIVITY_SERVICE);
        if (null == cm) {
            return false;
        } else {
            NetworkInfo[] networkInfo = cm.getAllNetworkInfo();

            if (networkInfo != null && networkInfo.length > 0) {
                for (int i = 0; i < networkInfo.length; i++) {
                    // 判断当前网络状态是否为连接状态
                    if (networkInfo[i].getState() == NetworkInfo.State.CONNECTED) {
                        return true;
                    }
                }
            }
        }

        return false;
    }
}
