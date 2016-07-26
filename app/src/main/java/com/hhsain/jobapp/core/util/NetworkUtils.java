package com.hhsain.jobapp.core.util;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by hhsain on 24/07/16.
 */
public class NetworkUtils {
    public static boolean isOnline(Context context){
        ConnectivityManager cm =
                (ConnectivityManager)context.getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        boolean isConnected = activeNetwork != null &&
                activeNetwork.isConnectedOrConnecting();

        return isConnected;
    }

    public static String formatDate(Date d){
        SimpleDateFormat format = new SimpleDateFormat("dd MMMMM yyyy hh:mm");

        return format.format(d);
    }

}
