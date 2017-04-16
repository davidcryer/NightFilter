package com.davidcryer.nightfilter.android.helpers;

import android.content.res.Resources;
import android.util.DisplayMetrics;

public class DisplayHelper {

    public static DisplayMetrics metrics() {
        return Resources.getSystem().getDisplayMetrics();
    }
}
