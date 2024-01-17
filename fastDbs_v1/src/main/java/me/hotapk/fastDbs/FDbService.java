package me.hotapk.fastDbs;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import me.hotapk.fastDbs.bean.FieldInfor;
import me.hotapk.fastDbs.bean.ResponseData;
import me.hotapk.utils.Constant;
import me.hotapk.utils.DatabaseUtils;
import me.hotapk.utils.FileUtils;
import me.hotapk.utils.SharedPrefsUtils;


/**
 * @author laijian
 * @version 2017/11/13
 * @Copyright (C)下午2:39 , www.hotapk.cn
 * 网络数据服务
 */
public class FDbService {

    /**
     * 获取数据库和sharedprefs列表
     *
     * @return
     */
    public static ResponseData getDbList() {
        ResponseData responseData = new ResponseData();
        try {
            List<String> templs = Arrays.asList(FDbManager.getAppContext().databaseList());
            List<String> rows = new ArrayList<>();
            for (int i = 0; i < templs.size(); i++) {
                if (!templs.get(i).matches(".*journal*.|undefined|.*xml|.*shared_prefs*.")) {
                    rows.add(templs.get(i));
                }
            }
            rows.add(Constant.SHAREDPREFS_XML);
            responseData.setRows(rows);
            responseData.setSuccessful(true);
        } catch (Exception e) {
            e.printStackTrace();
            responseData.setSuccessful(false);
            responseData.setError(e.getMessage());
        }
        return responseData;
    }

    /**
     * 获取table列表
     *
     * @param databaseName 数据库名称
     * @return
     */
    public static ResponseData getTableList(String databaseName) {
        ResponseData responseData = new ResponseData();
        try {
            List<String> rows = new ArrayList<>();
            if (databaseName.equals(Constant.SHAREDPREFS_XML)) {
                rows.addAll(SharedPrefsUtils.getSharedPreferenceXMl());
            } else {
                List<String> allTableNames = DatabaseUtils.getAllTableName(databaseName);
                for (int i = 0; i < allTableNames.size(); i++) {
                    if (!allTableNames.get(i).equals("android_metadata") && !allTableNames.get(i).equals("table_schema") && !allTableNames.get(i).equals("sqlite_sequence")) {
                        rows.add(allTableNames.get(i));
                    }
                }
            }
            responseData.setRows(rows);
            responseData.setSuccessful(true);
        } catch (Exception e) {
            e.printStackTrace();
            responseData.setSuccessful(false);
            responseData.setError(e.getMessage());

        }
        return responseData;
    }

    /**
     * 获取table数据
     *
     * @param databaseName
     * @param tableName
     * @return
     */
    public static ResponseData getTableDataList(String databaseName, String tableName) {
        ResponseData responseData = new ResponseData();

        try {
            if (databaseName.equals(Constant.SHAREDPREFS_XML)) {
                List<FieldInfor> allTablefield = new ArrayList<>();
                allTablefield.add(new FieldInfor("Key", "", false));
                allTablefield.add(new FieldInfor("Value", "", false));
                allTablefield.add(new FieldInfor("dataType", "", false));
                responseData.setAllTablefield(allTablefield);
                responseData.setDatas(SharedPrefsUtils.getPrefData(tableName));
            } else {
                responseData = DatabaseUtils.getAllTableData(DatabaseUtils.getDataBase(databaseName), tableName);
            }
            responseData.setSuccessful(true);
        } catch (Exception e) {
            e.printStackTrace();
            responseData.setSuccessful(false);
            responseData.setError(e.getMessage());
        }
        return responseData;
    }

    /**
     * 添加数据
     *
     * @param parms
     * @return
     */
    public static ResponseData addData(Map<String, String> parms) {
        ResponseData responseData = new ResponseData();
        boolean result;
        try {
            String dbname = parms.get("fdbname");
            String tablename = parms.get("ftablename");
            parms.remove("fdbname");
            parms.remove("ftablename");
            if (dbname.equals(Constant.SHAREDPREFS_XML)) {
                result = SharedPrefsUtils.addPrefData(tablename, parms);
            } else {
                result = DatabaseUtils.addData(dbname, tablename, parms);
            }
        } catch (Exception e) {
            e.printStackTrace();
            result = false;
            responseData.setError(e.getMessage());
        }
        responseData.setSuccessful(result);

        return responseData;
    }

    /**
     * 修改数据
     *
     * @param parms
     * @return
     */
    public static ResponseData editData(Map<String, String> parms) {
        ResponseData responseData = new ResponseData();

        boolean result;
        try {
            String dbname = parms.get("fdbname");
            String tablename = parms.get("ftablename");
            parms.remove("fdbname");
            parms.remove("ftablename");
            if (dbname.equals(Constant.SHAREDPREFS_XML)) {
                result = SharedPrefsUtils.addPrefData(tablename, parms);
            } else {
                result = DatabaseUtils.editData(dbname, tablename, parms);
            }
        } catch (Exception e) {
            e.printStackTrace();
            result = false;
            responseData.setError(e.getMessage());
        }
        responseData.setSuccessful(result);
        return responseData;
    }

    /**
     * 删除数据
     *
     * @param parms
     * @return
     */
    public static ResponseData delData(Map<String, String> parms) {
        ResponseData responseData = new ResponseData();
        boolean result;
        try {
            String dbname = parms.get("fdbname");
            String tablename = parms.get("ftablename");
            parms.remove("fdbname");
            parms.remove("ftablename");
            if (dbname.equals(Constant.SHAREDPREFS_XML)) {
                if (SharedPrefsUtils.clearByKey(tablename.replace(".xml", ""), parms.get("Key"))) {
                    result = true;
                } else {
                    result = false;
                }
            } else {
                result = DatabaseUtils.delData(dbname, tablename, parms);
            }
        } catch (Exception e) {
            e.printStackTrace();
            result = false;
            responseData.setError(e.getMessage());
        }
        responseData.setSuccessful(result);
        return responseData;

    }

    /**
     * 查询数据
     *
     * @param dbname
     * @return
     */
    public static String queryDb(String dbname) {
//        String dbname = parms.get("fdbname");
//        String tablename = parms.get("ftablename");
//        parms.remove("fdbname");
//        parms.remove("ftablename");
//        String result = FDatabaseUtils.delData(dbname, tablename, parms);
        return "";
    }

    /**
     * 数据库下载
     *
     * @param databaseName
     * @return
     */
    public static InputStream downloaddb(String databaseName) {
        if (databaseName.contains(Constant.SHAREDPREFS_XML)) {
            return FileUtils.file2Inp(SharedPrefsUtils.getSharedPreferencePath(databaseName.replace(Constant.SHAREDPREFS_XML, "")));
        }

        return FileUtils.file2Inp(FDbManager.getAppContext().getDatabasePath(databaseName).getAbsolutePath());
    }
}
