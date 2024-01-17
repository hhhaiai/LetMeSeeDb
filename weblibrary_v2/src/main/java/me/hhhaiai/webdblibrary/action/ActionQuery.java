package me.hhhaiai.webdblibrary.action;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import me.hhhaiai.webdblibrary.DatabaseManager;
import me.hhhaiai.webdblibrary.HttpRequest;
import me.hhhaiai.webdblibrary.Request;
import me.hhhaiai.webdblibrary.Response;
import me.hhhaiai.webdblibrary.ResponseHandler;
import me.hhhaiai.webdblibrary.utils.DatabaseHelper;

/**
 * @author lujianchao
 * 数据库查询
 */
public class ActionQuery implements Action {
    public static final String ACTION = "query";

    @Override
    public Response doAction(Context context, Request request, HttpRequest httpRequest, ResponseHandler responseHandler) {
        Response response = null;
        String first;
        try {
            SQLiteDatabase database = DatabaseManager.getDatabase(context, request.getDatabase());
            if (request.getData() != null && database != null) {
                first = request.getData().split(" ")[0].toLowerCase();
                if (first.equals("select") || first.equals("pragma")) {
                    response = DatabaseHelper.getTableData(database, request.getData(), null);
                } else {
                    response = DatabaseHelper.exec(database, request.getData());
                }
            } else {
                response = new Response().setCode(Response.code_SQLNODATA).setMsg("找不到数据库：" + request.getDatabase());
            }
        } catch (Exception e) {
            e.printStackTrace();
            response = new Response().setCode(Response.code_SQLNODATA).setMsg(e.getLocalizedMessage());
        }
        if (response == null) {
            response = new Response().setCode(Response.code_SQLNODATA).setMsg("找不到数据或者SQL语句错误");
        }
        responseHandler.sendPost(response);
        return response;
    }
}
