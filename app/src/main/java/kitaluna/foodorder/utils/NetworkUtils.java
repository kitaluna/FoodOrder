package kitaluna.foodorder.utils;

import android.content.Context;
import android.net.ConnectivityManager;

/**
 * Created by Van Hien on 1/2/2018.
 */

public class NetworkUtils {
    public static boolean isNetworkAvailable(Context ctx) {
        ConnectivityManager connMgr = (ConnectivityManager)ctx.getSystemService(Context.CONNECTIVITY_SERVICE);
        if(connMgr.getNetworkInfo(ConnectivityManager.TYPE_WIFI).isConnected() ||
                connMgr.getNetworkInfo(ConnectivityManager.TYPE_MOBILE).isConnected()){
            return true;
        }

        return false;
    }
}
