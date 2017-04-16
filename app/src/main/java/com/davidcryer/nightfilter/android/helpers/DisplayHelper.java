package com.davidcryer.nightfilter.android.helpers;

import android.content.Context;
import android.graphics.Point;
import android.view.WindowManager;

public class DisplayHelper {

    public static Metrics metrics(final Context context) {
        final Point point = new Point();
        ((WindowManager) context.getSystemService(Context.WINDOW_SERVICE)).getDefaultDisplay().getRealSize(point);
        return new Metrics(point.x, point.y);
    }

    public static class Metrics {
        public final int width;
        public final int height;

        Metrics(int width, int height) {
            this.width = width;
            this.height = height;
        }
    }
}
