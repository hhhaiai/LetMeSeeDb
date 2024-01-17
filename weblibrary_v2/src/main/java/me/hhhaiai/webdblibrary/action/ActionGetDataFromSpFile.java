package me.hhhaiai.webdblibrary.action;

import android.content.Context;

import me.hhhaiai.webdblibrary.HttpRequest;
import me.hhhaiai.webdblibrary.Request;
import me.hhhaiai.webdblibrary.Response;
import me.hhhaiai.webdblibrary.ResponseHandler;
import me.hhhaiai.webdblibrary.utils.PrefHelper;

/**
 * @author lujianchao
 * 从共享参数文件获取数据
 */
public class ActionGetDataFromSpFile implements Action {
    public static final String ACTION = "getDataFromSpFile";

    @Override
    public Response doAction(Context context, Request request, HttpRequest httpRequest, ResponseHandler responseHandler) {
        if (request.getSpFileName() == null || request.getSpFileName().length() < 1) {
            return null;
        }
        Response response = PrefHelper.getAllPrefData(context, request.getSpFileName());
        responseHandler.sendPost(response);
        return response;
    }
}
