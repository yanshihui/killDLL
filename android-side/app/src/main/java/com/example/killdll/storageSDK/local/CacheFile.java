package com.example.killdll.storageSDK.local;

import android.content.Context;
import android.content.SharedPreferences;

public class CacheFile {

    private static final String loginCacheFile = "login_data";
    private static final String tokenKey = "token";

    public static void saveToken(Context context, String token) {
        SharedPreferences.Editor editor = context.getSharedPreferences(loginCacheFile, Context.MODE_PRIVATE).edit();
        editor.putString(tokenKey, token);
        editor.apply();
    }

    public static String getToken(Context context) {
        SharedPreferences preferences = context.getSharedPreferences(loginCacheFile, Context.MODE_PRIVATE);
        return preferences.getString(tokenKey, null);
    }

    public static boolean validateLocalLoginState(Context context) {
        SharedPreferences preferences = context.getSharedPreferences(loginCacheFile, Context.MODE_PRIVATE);
        return !preferences.getString(tokenKey, null).equals(null);
    }

}
