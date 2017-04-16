package com.davidcryer.nightfilter.android.helpers;

import android.content.res.Resources;
import android.util.DisplayMetrics;
import android.view.WindowManager;

public class DisplayHelper {

    public static DisplayMetrics metrics(final WindowManager windowManager) {
        final DisplayMetrics displayMetrics = new DisplayMetrics();
        windowManager.getDefaultDisplay().getRealMetrics(displayMetrics);
        return displayMetrics;
    }

    public static DisplayMetrics metrics() {
        return Resources.getSystem().getDisplayMetrics();
    }
}
