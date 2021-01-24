package com.example.killdll.storageSDK.utils;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;

import java.io.ByteArrayOutputStream;

public class BitmapUtils {

    public static String bitmapToString(Bitmap pic) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        pic.compress(Bitmap.CompressFormat.JPEG, 100, baos);
        byte[] buffer = baos.toByteArray();
        return Base64.encodeToString(buffer, 0, buffer.length, Base64.NO_WRAP);
    }

    public static Bitmap stringToBitmap(String encodedStr) {
        byte[] buffer = Base64.decode(encodedStr, Base64.NO_WRAP);
        return BitmapFactory.decodeByteArray(buffer, 0, buffer.length);

    }
}