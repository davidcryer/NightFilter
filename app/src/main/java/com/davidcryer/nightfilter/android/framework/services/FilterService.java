package com.davidcryer.nightfilter.android.framework.services;

import android.app.Service;
import android.content.Intent;
import android.graphics.PixelFormat;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.WindowManager;

import com.davidcryer.nightfilter.platformindependent.helpers.StateChecker;

public class FilterService extends Service {
    private View filterView;

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

        public void attachFilter(final int color) {
            FilterService.this.attachFilter(color);
        }

        public void changeFilter(final int color) {
            FilterService.this.changeFilter(color);
        }

        public void unAttachFilter() {
            FilterService.this.removeFilter();
        }
    }

    private void attachFilter(final int color) {
        if (filterView == null) {
            filterView = new View(this);
            WindowManager.LayoutParams params = new WindowManager.LayoutParams(
                    WindowManager.LayoutParams.MATCH_PARENT,
                    WindowManager.LayoutParams.MATCH_PARENT,
                    WindowManager.LayoutParams.TYPE_SYSTEM_OVERLAY,
                    WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE,
                    PixelFormat.TRANSLUCENT);
            ((WindowManager) getSystemService(WINDOW_SERVICE)).addView(filterView, params);
        }
        changeFilter(color);
    }

    public void changeFilter(final int color) {
        StateChecker.notNull(filterView, "filterView has not been set up");
        filterView.setBackgroundResource(color);
    }

    private void removeFilter() {
        if (filterView != null) {
            ((WindowManager) getSystemService(WINDOW_SERVICE)).removeView(filterView);
            filterView = null;
        }
    }
}
