package me.hotapk.utils;

import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;

import java.util.ArrayList;
import java.util.List;

import me.hotapk.fastDbs.FDbManager;

public class ManifestUtils {


    /**
     * 获取manifest有注册的权限
     *
     * @return
     */
    public static String[] getRegPermission() {
        PackageInfo appInfo = null;
        List<String> perls = new ArrayList<>();

        try {
            appInfo = FDbManager.getAppContext().getPackageManager()
                    .getPackageInfo(FDbManager.getAppContext().getPackageName(),  PackageManager.GET_PERMISSIONS);
            String[] perms = appInfo.requestedPermissions;
            for (String perm : perms) {
                perls.add(perm);
            }
            return perls.toArray(new String[perls.size()]);

        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }
}
