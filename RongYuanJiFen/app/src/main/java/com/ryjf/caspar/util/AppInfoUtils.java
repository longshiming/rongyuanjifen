/**
 * @(#) com.kfx.e_news.utils 2015/10/22;
 * <p>
 * Copyright (c), 2009 深圳孔方兄金融信息服务有限公司（Shenzhen kfxiong
 * Financial Information Service Co. Ltd.）
 * <p>
 * 著作权人保留一切权利，任何使用需经授权。
 */
package com.ryjf.caspar.util;

import android.app.ActivityManager;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.telephony.TelephonyManager;

import java.util.List;


public class AppInfoUtils {

    /**
     * 获取应用程序版本名称
     *
     * @return
     */
    public static String getAppVersionName() {
        try {
            PackageManager pm = UiUtils.getContext().getPackageManager();
            PackageInfo packinfo = pm.getPackageInfo(UiUtils.getContext().getPackageName(), 0);
            String versionName = packinfo.versionName;
            return versionName;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
            //can't reach
            return "";
        }
    }

    /**
     * 获取应用程序版本号
     *
     * @return
     */
    public static int getAppVersionCode() {
        try {
            PackageManager pm = UiUtils.getContext().getPackageManager();
            PackageInfo packinfo = pm.getPackageInfo(UiUtils.getContext().getPackageName(), 0);
            int versionCode = packinfo.versionCode;
            return versionCode;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
            //can't reach
            return 0;
        }
    }

    /**
     * 检测是否开启了某个权限
     *
     * @param permissionName
     * @return
     */
    public static boolean checkPermission(String permissionName) {

        return UiUtils.getContext().checkCallingOrSelfPermission(permissionName) == PackageManager.PERMISSION_GRANTED;
    }


    /**
     * app是否在运行
     *
     * @param context
     * @return
     */
    public static boolean isAppRunning(Context context) {
        String packageName = context.getPackageName();
        ActivityManager am = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
        List<ActivityManager.RunningTaskInfo> list = am.getRunningTasks(100);
        for (ActivityManager.RunningTaskInfo info : list) {
            if (info.topActivity.getPackageName().equals(packageName) && info.baseActivity.getPackageName().equals(packageName)) {
                return true;
            }
        }
        return false;
    }

    /**
     * 获取设备Id
     *
     * @return
     */
    public static String getDeviceId() {
        TelephonyManager manager =
                (TelephonyManager) UiUtils.getContext().getSystemService(Context.TELEPHONY_SERVICE);
        return manager.getSimSerialNumber();
    }

}
