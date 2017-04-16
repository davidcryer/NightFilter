package com.davidcryer.nightfilter.android.framework.services;

import android.app.Service;
import android.content.Intent;
import android.graphics.PixelFormat;
import android.graphics.drawable.ColorDrawable;
import android.os.IBinder;
import android.support.annotation.ColorRes;
import android.support.annotation.IntDef;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutCompat;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.davidcryer.nightfilter.android.helpers.DisplayHelper;
import com.davidcryer.nightfilter.platformindependent.helpers.StateChecker;

public class FilterService extends Service {
    private View filterView;

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        return START_STICKY;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        removeFilter();
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return binder;
    }

    private final Binder binder = new Binder();

    public class Binder extends android.os.Binder {

        public boolean isFilterAttached() {
            return filterView != null;
        }

        public void attachFilter(@ColorRes final int color) {
            FilterService.this.attachFilter(color);
        }

        public void changeFilter(@ColorRes final int color) {
            FilterService.this.changeFilter(color);
        }

        public void unAttachFilter() {
            FilterService.this.removeFilter();
        }
    }

    private void attachFilter(@ColorRes final int color) {
        if (filterView == null) {
            filterView = new View(this);
            final WindowManager windowManager = ((WindowManager) getSystemService(WINDOW_SERVICE));
            final DisplayMetrics metrics = DisplayHelper.metrics(windowManager);
            WindowManager.LayoutParams params = new WindowManager.LayoutParams(
                    metrics.widthPixels,
                    metrics.heightPixels,
                    WindowManager.LayoutParams.TYPE_SYSTEM_OVERLAY,
                    WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE
                            | WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE
                            | WindowManager.LayoutParams.FLAG_LAYOUT_IN_SCREEN
                            | WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
                    PixelFormat.TRANSLUCENT);
            params.gravity = Gravity.TOP & Gravity.START;
            windowManager.addView(filterView, params);
        }
        changeFilter(color);
    }

    private void changeFilter(@ColorRes final int color) {
        StateChecker.notNull(filterView, "filterView has not been set up");
        filterView.setBackgroundColor(ContextCompat.getColor(this, color));
    }

    private void removeFilter() {
        if (filterView != null) {
            ((WindowManager) getSystemService(WINDOW_SERVICE)).removeView(filterView);
            filterView = null;
        }
    }
}
