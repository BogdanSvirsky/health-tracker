package com.example.health_tracker;

import android.content.res.Resources;

public class Utils {
    public static int RESULT_LOGIN = 1, RESULT_WIDGETS = 2;

    public static int pxToDp(int px) {
        return (int) (px / Resources.getSystem().getDisplayMetrics().density);
    }

    public static int dpToPx(int dp) {
        return (int) (dp * Resources.getSystem().getDisplayMetrics().density);
    }
}
