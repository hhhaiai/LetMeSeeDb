package com.amitshekhar;

import android.arch.persistence.db.SupportSQLiteDatabase;
import android.content.Context;
import android.util.Log;
import android.util.Pair;

import com.amitshekhar.server.ClientServer;
import com.amitshekhar.sqlite.DBFactory;
import com.amitshekhar.utils.NetworkUtils;

import java.io.File;
import java.util.HashMap;

/**
 * Created by amitshekhar on 15/11/16.
 */
public class DebugDB {

    private static final String TAG = DebugDB.class.getSimpleName();
    private static final int DEFAULT_PORT = 8080;
    private static ClientServer clientServer;
    private static String addressLog = "not available";

    private DebugDB() {
        // This class in not publicly instantiable
    }

    public static void initialize(Context context, DBFactory dbFactory) {
        int portNumber;

        try {
            portNumber = Integer.valueOf(context.getString(com.amitshekhar.R.string.PORT_NUMBER));
        } catch (NumberFormatException ex) {
            Log.e(TAG, "PORT_NUMBER should be integer", ex);
            portNumber = DEFAULT_PORT;
            Log.i(TAG, "Using Default port : " + DEFAULT_PORT);
        }
        clientServer = new ClientServer(context, portNumber, dbFactory);
        clientServer.start();
        addressLog = NetworkUtils.getAddressLog(context, portNumber);
        Log.d(TAG, addressLog);
    }

    public static String getAddressLog() {
        Log.d(TAG, addressLog);
        return addressLog;
    }

    public static void shutDown() {
        if (clientServer != null) {
            clientServer.stop();
            clientServer = null;
        }
    }

    public static void setCustomDatabaseFiles(HashMap<String, Pair<File, String>> customDatabaseFiles) {
        if (clientServer != null) {
            clientServer.setCustomDatabaseFiles(customDatabaseFiles);
        }
    }

    public static void setInMemoryRoomDatabases(HashMap<String, SupportSQLiteDatabase> databases) {
        if (clientServer != null) {
            clientServer.setInMemoryRoomDatabases(databases);
        }
    }

    public static boolean isServerRunning() {
        return clientServer != null && clientServer.isRunning();
    }

}
