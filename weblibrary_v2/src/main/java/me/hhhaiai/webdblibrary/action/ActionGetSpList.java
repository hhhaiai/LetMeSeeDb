package me.hhhaiai.webdblibrary.action;

import android.content.Context;

import me.hhhaiai.webdblibrary.HttpRequest;
import me.hhhaiai.webdblibrary.Request;
import me.hhhaiai.webdblibrary.Response;
import me.hhhaiai.webdblibrary.ResponseHandler;
import me.hhhaiai.webdblibrary.utils.PrefHelper;

/**
 * @author lujianchao
 * 获取共享参数列表
 */
public class ActionGetSpList implements Action {
    public static final String ACTION = "getSpList";

    @Override
    public Response doAction(Context context, Request request, HttpRequest httpRequest, ResponseHandler responseHandler) {
        Response response = new Response();
        response.setSpList(PrefHelper.getSharedPreferenceTags(context));
        responseHandler.sendPost(response);
        return response;
    }
}
