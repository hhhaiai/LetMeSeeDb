package me.hhhaiai.webdblibrary.action;

import android.content.Context;

import me.hhhaiai.webdblibrary.HttpRequest;
import me.hhhaiai.webdblibrary.Request;
import me.hhhaiai.webdblibrary.Response;
import me.hhhaiai.webdblibrary.ResponseHandler;

/**
 * @author lujianchao
 */
public interface Action {
    Response doAction(Context context, Request request,HttpRequest httpRequest, ResponseHandler responseHandler);
}
