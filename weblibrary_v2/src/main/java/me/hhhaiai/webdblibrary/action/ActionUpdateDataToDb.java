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
 * 更新数据库数据
 */
public class ActionUpdateDataToDb implements Action {
    public static final String ACTION = "updateDataToDb";

    @Override
    public Response doAction(Context context, Request request, HttpRequest httpRequest, ResponseHandler responseHandler) {
        Response response = DatabaseHelper.updateRow(DatabaseManager.getDatabase(context, request.getDatabase()), request.getTableName(), request.getRowDataRequests());
        responseHandler.sendPost(response);
        return response;
    }
}
