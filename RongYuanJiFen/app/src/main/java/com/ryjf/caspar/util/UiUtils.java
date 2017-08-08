package com.ryjf.caspar.util;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.ForegroundColorSpan;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;

import com.ryjf.caspar.OxbixApplication;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by lsm on 2016/9/6.
 */

public class UiUtils {
    public static final int EMPTY = 1;
    public static final int ERRO = 2;
    public static final int SUCCESS = 3;

    /**
     * @return 全局Context
     */
    public static Context getContext() {
        return OxbixApplication.getContext();
    }

    public static String getString(int resId) {
        return getContext().getResources().getString(resId);
    }

    public static int getDimens(int resId) {
        return getContext().getResources().getDimensionPixelSize((resId));
    }

    public static int getColor(int resId) {
        return getContext().getResources().getColor(resId);
    }

    public static Drawable getDrawable(int resId) {
        return getContext().getResources().getDrawable(resId);
    }


    public static Bitmap getBitmap(Context context, int resId) {
        BitmapFactory.Options opt = new BitmapFactory.Options();
        opt.inPreferredConfig = Bitmap.Config.RGB_565;
        opt.inPurgeable = true;
        opt.inInputShareable = true;
        InputStream in = context.getResources().openRawResource(resId);
        return BitmapFactory.decodeStream(in, null, opt);
    }

    public static Bitmap getBitmap(Context context, String path) {
        BitmapFactory.Options opt = new BitmapFactory.Options();
        opt.inPreferredConfig = Bitmap.Config.RGB_565;
        opt.inPurgeable = true;
        opt.inInputShareable = true;
        return BitmapFactory.decodeFile(path, opt);
    }

    public static String bitmap2Base64(Bitmap bitmap) {
        if (bitmap == null) {
            return "";
        }
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, baos);
        byte[] bytes = baos.toByteArray();

        //base64 encode
        byte[] encode = Base64.encode(bytes, Base64.DEFAULT);
        String encodeString = new String(encode);
        return encodeString;
    }

    /**
     * @Desc: dip转化为px
     */
    public static int dip2px(Context context, int dip) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dip * scale + 0.5f);
    }

    /**
     * @Desc: px转化为dip
     */
    public static int px2dip(Context context, int px) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (px / scale + 0.5f);
    }

    /**
     * sp转化为px
     *
     * @param context
     * @param sp
     * @return
     */
    public static int sp2px(Context context, int sp) {
        final float scale = context.getResources().getDisplayMetrics().scaledDensity;
        return (int) (sp * scale + 0.5f);
    }

    public static View inflate(int resId) {
        return LayoutInflater.from(getContext()).inflate(resId, null, false);
    }

    // This snippet hides the system bars.
    public static void hideSystemUI(Activity activity) {
        // Set the IMMERSIVE flag.
        // Set the content to appear under the system bars so that the content
        // doesn't resize when the system bars hide and show.
        activity.getWindow().getDecorView().setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                        | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION // hide nav bar
                        | View.SYSTEM_UI_FLAG_FULLSCREEN // hide status bar
                        | View.SYSTEM_UI_FLAG_IMMERSIVE);
    }

    public static void hideStickySystemUI(Activity activity) {
        activity.getWindow().getDecorView().setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                        | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_FULLSCREEN
//                        | View.SYSTEM_UI_FLAG_IMMERSIVE);
                        | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);
    }

    public static void showSystemUI(Activity activity) {
        activity.getWindow().getDecorView().setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                        | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
    }

    public static void showEmptyType(int type, RelativeLayout rlEmpty, RelativeLayout rlErro, FrameLayout flRoot) {
        switch (type) {
            case EMPTY:
                flRoot.setVisibility(View.VISIBLE);
                rlEmpty.setVisibility(View.VISIBLE);
                rlErro.setVisibility(View.GONE);
                LogUtils.i("lsm", "===EMPTY===");
                break;
            case ERRO:
                flRoot.setVisibility(View.VISIBLE);
                rlEmpty.setVisibility(View.GONE);
                rlErro.setVisibility(View.VISIBLE);
                LogUtils.i("lsm", "===ERRO===");
                break;
            case SUCCESS:
                flRoot.setVisibility(View.GONE);
                LogUtils.i("lsm", "===SUCCESS===");
                break;
            default:
                break;
        }

    }

    public static SpannableString matcherSearchText(int color, String text, String keyword) {
        SpannableString ss = new SpannableString(text);
        Pattern pattern = Pattern.compile(keyword);
        Matcher matcher = pattern.matcher(ss);

        while (matcher.find()) {
            int start = matcher.start();
            int end = matcher.end();
            ss.setSpan(new ForegroundColorSpan(color), start, end, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        }

        return ss;
    }


}
