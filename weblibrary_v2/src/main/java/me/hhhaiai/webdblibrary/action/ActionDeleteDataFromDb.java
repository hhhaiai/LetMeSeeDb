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
 * 从数据库中删数据
 */
public class ActionDeleteDataFromDb implements Action {
    public static final String ACTION = "deleteDataFromDb";

    @Override
    public Response doAction(Context context, Request request, HttpRequest httpRequest, ResponseHandler responseHandler) {
        Response response = DatabaseHelper.deleteRow(DatabaseManager.getDatabase(context, request.getDatabase()), request.getTableName(), request.getRowDataRequests());
        responseHandler.sendPost(response);
        return response;
    }
}
