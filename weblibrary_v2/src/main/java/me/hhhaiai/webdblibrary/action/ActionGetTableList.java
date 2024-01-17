package me.hhhaiai.webdblibrary.action;

import android.content.Context;

import me.hhhaiai.webdblibrary.DatabaseManager;
import me.hhhaiai.webdblibrary.HttpRequest;
import me.hhhaiai.webdblibrary.Request;
import me.hhhaiai.webdblibrary.Response;
import me.hhhaiai.webdblibrary.ResponseHandler;
import me.hhhaiai.webdblibrary.utils.DatabaseHelper;

/**
 * @author lujianchao
 * 获取数据库表
 */
public class ActionGetTableList implements Action {
    public static final String ACTION = "getTableList";

    @Override
    public Response doAction(Context context, Request request, HttpRequest httpRequest, ResponseHandler responseHandler) {
        if (request.getDatabase() == null || request.getDatabase().length() < 1) {
            return null;
        }
        Response response=DatabaseHelper.getAllTableName(DatabaseManager.getDatabase(context, request.getDatabase()));
        responseHandler.sendPost(response);
        return response;
    }
}
