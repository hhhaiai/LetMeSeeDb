/*
 *
 *  *    Copyright (C) 2016 Amit Shekhar
 *  *    Copyright (C) 2011 Android Open Source Project
 *  *
 *  *    Licensed under the Apache License, Version 2.0 (the "License");
 *  *    you may not use this file except in compliance with the License.
 *  *    You may obtain a copy of the License at
 *  *
 *  *        http://www.apache.org/licenses/LICENSE-2.0
 *  *
 *  *    Unless required by applicable law or agreed to in writing, software
 *  *    distributed under the License is distributed on an "AS IS" BASIS,
 *  *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  *    See the License for the specific language governing permissions and
 *  *    limitations under the License.
 *
 */

package me.hhhaiai.webdblibrary.utils;

import android.content.Context;
import android.content.res.AssetManager;
import android.text.TextUtils;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

/**
 * Created by lujianchao on 2017.8.22.
 */

public class Utils {

    private static final String TAG = "Utils";
    public static final String JSON = "application/json;charset=utf-8";
    public static final String HTML = "text/html";
    public static final String JS = "application/javascript";
    public static final String CSS = "text/css";
    public static final String OBJECT = "application/octet-stream";

    private Utils() {
        // This class in not publicly instantiable
    }

    public static String detectMimeType(String fileName) {
        if (TextUtils.isEmpty(fileName)) {
            return JSON;
        } else if (fileName.endsWith(".html")) {
            return HTML;
        } else if (fileName.endsWith(".js")) {
            return JS;
        } else if (fileName.endsWith(".css")) {
            return CSS;
        } else {
            return OBJECT;
        }
    }

    public static byte[] loadContent(String fileName, AssetManager assetManager) throws IOException {
        InputStream input = null;
        try {
            ByteArrayOutputStream output = new ByteArrayOutputStream();
            input = assetManager.open(fileName);
            byte[] buffer = new byte[1024];
            int size;
            while (-1 != (size = input.read(buffer))) {
                output.write(buffer, 0, size);
            }
            output.flush();
            return output.toByteArray();
        } catch (FileNotFoundException e) {
            return null;
        } finally {
            try {
                if (null != input) {
                    input.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }


    /**
     * 获取文件
     *
     * @param mFile
     * @return
     */
    public static byte[] getFile(File mFile) {
        if (mFile==null||!mFile.exists()) {
            return null;
        }

        byte[] byteArray = new byte[0];
        try {
            byteArray = null;
            try {
                InputStream inputStream = new FileInputStream(mFile);
                ByteArrayOutputStream bos = new ByteArrayOutputStream();
                byte[] b = new byte[(int) mFile.length()];
                int bytesRead;

                while ((bytesRead = inputStream.read(b)) != -1) {
                    bos.write(b, 0, bytesRead);
                }
                byteArray = bos.toByteArray();
            } catch (IOException e) {
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return byteArray;
    }

    public static String formatFileSize(Context context, long number, boolean shorter) {
        if (context == null) {
            return "";
        }

        float result = number;
        String suffix = "B";
        if (result > 900) {
            suffix = "KB";
            result = result / 1024;
        }
        if (result > 900) {
            suffix = "MB";
            result = result / 1024;
        }
        if (result > 900) {
            suffix = "GB";
            result = result / 1024;
        }
        if (result > 900) {
            suffix = "TB";
            result = result / 1024;
        }
        if (result > 900) {
            suffix = "PB";
            result = result / 1024;
        }
        String value;
        if (result < 1) {
            value = String.format("%.2f", result);
        } else if (result < 10) {
            if (shorter) {
                value = String.format("%.1f", result);
            } else {
                value = String.format("%.2f", result);
            }
        } else if (result < 100) {
            if (shorter) {
                value = String.format("%.0f", result);
            } else {
                value = String.format("%.2f", result);
            }
        } else {
            value = String.format("%.0f", result);
        }

        return value + suffix;
    }
}
