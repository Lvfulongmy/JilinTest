package com.jilin.test.util;

import android.widget.Toast;

import com.jilin.test.MyApplication;

/**
 * Created by ruthout on 2016/8/5.
 */
public final class ToastUtils {

    public static final int LENGTH_LONG = Toast.LENGTH_LONG;
    public static final int LENGTH_SHORT = Toast.LENGTH_SHORT;
    public static boolean isShow = true;
    private static Toast sToast;

    private ToastUtils() {
        /* cannot be instantiated */
        throw new UnsupportedOperationException("cannot be instantiated");
    }

    /**
     * 短时间显示Toast
     *
     * @param message
     */
    public static void showShort(CharSequence message) {
        try {
            if (isShow) {
                if (null == sToast) {
                    sToast = Toast.makeText(MyApplication.getApp().getBaseContext(), message, Toast.LENGTH_SHORT);
                } else {
                    sToast.setText(message);
                }
                sToast.show();
                sToast = null;
                //            Snackbar.make(context.getCurrentFocus(),message,Snackbar.LENGTH_SHORT).show();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 短时间显示Toast
     *
     * @param message
     */
    public static void showShort(int message) {
        try {
            if (isShow) {
                //            Snackbar.make(context.getCurrentFocus(), message, Snackbar.LENGTH_SHORT).show();

                if (null == sToast) {
                    sToast = Toast.makeText(MyApplication.getApp().getBaseContext(), message, Toast.LENGTH_SHORT);
                } else {
                    sToast.setText(message);
                }
                sToast.show();
                sToast = null;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 自定义显示Toast时间
     *
     * @param message
     * @param duration
     */
    public static void show(CharSequence message, int duration) {
        try {
            if (isShow) {
                if (null == sToast) {
                    sToast = Toast.makeText(MyApplication.getApp().getBaseContext(), message, duration);
                } else {
                    sToast.setText(message);
                }
                sToast.show();
                sToast = null;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 自定义显示Toast时间
     *
     * @param message
     * @param duration
     */
    public static void show(int message, int duration) {
        try {
            if (isShow) {
                if (null == sToast) {
                    sToast = Toast.makeText(MyApplication.getApp().getBaseContext(), message, duration);
                } else {
                    sToast.setText(message);
                }
                sToast.show();
                sToast = null;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
