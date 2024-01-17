package me.hotapk.utils;

import android.content.Context;

/**
 * @author laijian
 * @version 2017/9/18
 * @Copyright (C)下午4:56 , www.hotapk.cn
 * 初始化
 */
public class HttpServerUtils {

    private Context context;

    private volatile static HttpServerUtils fUtils;

    private HttpServerUtils(Context context) {
        this.context = context;
    }

    public static void init(Context context) {
        if (fUtils == null) {
            synchronized (HttpServerUtils.class) {
                if (fUtils == null) {
                    fUtils = new HttpServerUtils(context);
                }
            }
        }
    }

    public static Context getAppContext() {
        if (fUtils != null) return fUtils.context.getApplicationContext();
        throw new NullPointerException("To initialize first");
    }

}
