/**
 * @(#) ToastUtils 2015/7/6
 * <p>
 * Copyright (c), 2009 深圳孔方兄金融信息服务有限公司（Shenzhen kfxiong
 * Financial Information Service Co. Ltd.）
 * <p>
 * 著作权人保留一切权利，任何使用需经授权。
 */
package com.ryjf.caspar.util;

import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.ryjf.caspar.R;


public class ToastUtils {

    private static Toast mToast = null;

    public static void show(Context context, String promt) {
        View v = LayoutInflater.from(context).inflate(R.layout.toast_view, null);
        TextView textView = (TextView) v.findViewById(R.id.tv);
        textView.setText(promt);
        if (mToast == null) {
            mToast = Toast.makeText(context, promt, Toast.LENGTH_SHORT);
        }

        mToast.setView(v);
        mToast.setGravity(Gravity.BOTTOM, 0, 136);
        mToast.show();
    }

//    public static void showOnCenter(Context context, String promt) {
//        View v = LayoutInflater.from(context).inflate(R.layout.toast_view, null);
//        TextView textView = (TextView) v.findViewById(R.id.tv);
//        textView.setText(promt);
//        if (mToast == null) {
//            mToast = Toast.makeText(context, promt, Toast.LENGTH_SHORT);
//        }
//
//        mToast.setView(v);
//        mToast.setGravity(Gravity.CENTER, 0, 0);
//        mToast.show();
//    }


}
