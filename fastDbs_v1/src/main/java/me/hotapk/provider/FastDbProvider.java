package me.hotapk.provider;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import me.hotapk.fastDbs.FDbManager;

public class FastDbProvider extends ContentProvider {


    public FastDbProvider() {
    }

    @Override
    public boolean onCreate() {
        new Thread(new Runnable() {
            @Override
            public void run() {


                try {
                    FDbManager.init(FastDbProvider.this.getContext()).startServer();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();

        return true;
    }

    @Override
    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {
        return null;
    }

    @Override
    public String getType(Uri uri) {
        return null;
    }

    @Override
    public Uri insert(Uri uri, ContentValues values) {
        return null;
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        return 0;
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection, String[] selectionArgs) {
        return 0;
    }


}
