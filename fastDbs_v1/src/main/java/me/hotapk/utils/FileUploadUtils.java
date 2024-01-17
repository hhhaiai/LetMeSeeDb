package me.hotapk.utils;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import me.hotapk.fhttpserver.NanoHTTPD;

/**
 * @author laijian
 * @version 2017/12/6
 * @Copyright (C)下午6:38 , www.hotapk.cn
 */
public class FileUploadUtils {

    /**
     * 文件上传
     *
     * @param session
     * @param fileDir 保存文件的目录
     * @param parm    上传文件的参数
     * @return
     */
    public static boolean uploadFile(NanoHTTPD.IHTTPSession session, String fileDir, String parm) {
        Map<String, String> files = new HashMap<>();
        try {
            session.parseBody(files);
            Map<String, String> parms = session.getParms();
            return FileUtils.copyFileTo(files.get(parm), fileDir + "/" + parms.get(parm));
        } catch (IOException e) {
            e.printStackTrace();
        } catch (NanoHTTPD.ResponseException e) {
            e.printStackTrace();
        }
        return false;
    }
}
