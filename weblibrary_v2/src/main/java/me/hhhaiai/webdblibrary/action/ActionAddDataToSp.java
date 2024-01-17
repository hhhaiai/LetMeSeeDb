package me.hhhaiai.webdblibrary.action;

import android.content.Context;

import me.hhhaiai.webdblibrary.HttpRequest;
import me.hhhaiai.webdblibrary.Request;
import me.hhhaiai.webdblibrary.Response;
import me.hhhaiai.webdblibrary.ResponseHandler;
import me.hhhaiai.webdblibrary.utils.PrefHelper;

/**
 * @author lujianchao
 * 添加数据到共享参数
 */
public class ActionAddDataToSp implements Action {
    public static final String ACTION = "addDataToSp";

    @Override
    public Response doAction(Context context, Request request, HttpRequest httpRequest, ResponseHandler responseHandler) {
        Response response = PrefHelper.addOrUpdateRow(context, request.getSpFileName(), request.getRowDataRequests());
        responseHandler.sendPost(response);
        return response;
    }
}
