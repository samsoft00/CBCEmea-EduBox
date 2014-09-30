package com.cbcmobileapp.edubox.utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Base64;

/**
 * Created by CBC on 16-Aug-14.
 */
public class Utility {

    public static Bitmap decodeBase64(String input)
    {
        byte[] decodedByte = Base64.decode(input, Base64.DEFAULT);
        return BitmapFactory.decodeByteArray(decodedByte, 0, decodedByte.length);
    }
    //Parse Information
    public static final String APPLICATION_KEY = "null";
    public static final String CLIENT_KEY = "null";

    public static boolean isConnectionAvailable(Context mContext){
        boolean status=false;
        try{
            ConnectivityManager cm = (ConnectivityManager) mContext.getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo netInfo = cm.getNetworkInfo(0);
            if (netInfo != null && netInfo.getState() == NetworkInfo.State.CONNECTED) {
                status= true;
            }else {
                netInfo = cm.getNetworkInfo(1);
                if(netInfo!=null && netInfo.getState() == NetworkInfo.State.CONNECTED)
                    status= true;
            }

        }catch(Exception e){
            e.printStackTrace();
            return false;
        }
        return status;
    }
}
