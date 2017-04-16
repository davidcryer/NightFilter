package com.davidcryer.nightfilter.android.helpers;

import android.content.Context;
import android.graphics.PixelFormat;
import android.graphics.Point;
import android.view.Gravity;
import android.view.WindowManager;

public class WindowManagerLayoutParamsFactory {

    public static WindowManager.LayoutParams wholeScreenLayoutParams(final Context context) {
        final Metrics metrics = WindowManagerLayoutParamsFactory.metrics(context.getApplicationContext());
        final WindowManager.LayoutParams params = new WindowManager.LayoutParams(
                metrics.width,
                metrics.height,
                WindowManager.LayoutParams.TYPE_SYSTEM_OVERLAY,
                WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE
                        | WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE
                        | WindowManager.LayoutParams.FLAG_LAYOUT_IN_SCREEN
                        | WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
                PixelFormat.TRANSLUCENT
        );
        params.gravity = Gravity.TOP;
        return params;
    }

    private static Metrics metrics(final Context context) {
        final Point point = new Point();
        ((WindowManager) context.getSystemService(Context.WINDOW_SERVICE)).getDefaultDisplay().getRealSize(point);
        return new Metrics(point.x, point.y);
    }

    private static class Metrics {
        private final int width;
        private final int height;

        Metrics(int width, int height) {
            this.width = width;
            this.height = height;
        }
    }
}
