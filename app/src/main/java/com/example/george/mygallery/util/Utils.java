package com.example.george.mygallery.util;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Point;
import android.os.Build;
import android.view.Display;
import android.view.WindowManager;

/**
 * Utils defines some useful common methods for the App.
 *
 * @author George Lin
 */
public class Utils {

    private Utils() {
        throw new RuntimeException("Utils.class is not instantiable.");
    }

    /**
     * Get screen width
     */
    @TargetApi(13)
    public static int getScreenWidth(Context context) {
        int screenWidth;
        WindowManager wm = (WindowManager) context
            .getSystemService(Context.WINDOW_SERVICE);
        Display display = wm.getDefaultDisplay();

        if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB_MR2) {
            final Point point = new Point();
            display.getSize(point);
            screenWidth = point.x;
        } else { // For older version
            screenWidth = display.getWidth();
        }
        return screenWidth;
    }

}
