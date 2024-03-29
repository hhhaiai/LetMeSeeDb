package me.hotapk.fastDbs;

import android.content.Context;
import android.util.Log;

import me.hotapk.fhttpserver.FHttpManager;
import me.hotapk.utils.IpHelper;


/**
 * @author laijian
 * @version 2017/12/8
 * @Copyright (C)下午3:18 , www.hotapk.cn
 */
public class FDbManager {
    private static final String TAG = "DebugDb.FDbM";

    private Context context;

    private volatile static FDbManager dbManager;
    private FHttpManager fHttpManager;
    private int port = 8888;

    private FDbManager(Context context) {
        this.context = context;
        fHttpManager = FHttpManager.init(context, FDbController.class);
    }

    /**
     * 初始化
     *
     * @param context
     * @return
     */
    public static FDbManager init(Context context) {
        if (dbManager == null) {
            synchronized (FDbManager.class) {
                if (dbManager == null) {
                    dbManager = new FDbManager(context.getApplicationContext());
                }
            }
        }
        return dbManager;
    }

    public static Context getAppContext() {
        if (dbManager != null) return dbManager.context;
        throw new NullPointerException("To initialize first");
    }

    /**
     * 设置端口号
     *
     * @param port
     * @throws Exception
     */
    public void setPort(int port) throws Exception {
        this.port = port;
        throw new Exception("please init FHttpManager");
    }

    /**
     * 启动服务
     */
    public void startServer() throws Exception {
        if (fHttpManager == null) {
            throw new Exception("please init FHttpManager");
        } else {
            fHttpManager.setPort(port);
            fHttpManager.setIndexName("sqlindex.html");
            fHttpManager.startServer();
            String addressLog = IpHelper.getAddressLog(context, port);
            Log.d(TAG, "Open http://" + addressLog + " in your browser");
            Log.d(TAG, "请用浏览器打开 http://" + addressLog);
        }
    }

    /**
     * 关闭服务
     *
     * @throws Exception
     */
    public void stopServer() throws Exception {
        if (fHttpManager == null) {
            throw new Exception("please init FHttpManager");
        } else {
            fHttpManager.stopServer();
        }
    }

    /**
     * 获取http服务
     */
    public FHttpManager getFHttpManager() {
        return fHttpManager;
    }


}
