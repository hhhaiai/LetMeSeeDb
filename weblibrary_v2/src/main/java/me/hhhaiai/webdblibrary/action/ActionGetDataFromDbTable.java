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
 * 从数据库表读取数据
 */
public class ActionGetDataFromDbTable implements Action {
    public static final String ACTION = "getDataFromDbTable";

    @Override
    public Response doAction(Context context, Request request, HttpRequest httpRequest, ResponseHandler responseHandler) {
        if (request.getTableName() == null || request.getTableName().length() < 1) {
            return null;
        }
        Response response = null;
        SQLiteDatabase sqLiteDatabase = DatabaseManager.getDatabase(context, request.getDatabase());
        if (sqLiteDatabase != null) {
            String sql;
            if (request.getPageIndex() == null || request.getPageSize() == null) {
                sql = "SELECT * FROM " + request.getTableName();
            } else {
                sql = "SELECT * FROM " + request.getTableName() + " limit " + (request.getPageIndex() - 1) * request.getPageSize() + "," + request.getPageSize();
            }
            response = DatabaseHelper.getTableData(sqLiteDatabase, sql, request.getTableName());
        } else {
            response = new Response().setCode(Response.code_FileNotFound).setMsg("数据库文件不存在，请检查是否做了删除操作");
        }
        responseHandler.sendPost(response);
        return response;
    }
}
