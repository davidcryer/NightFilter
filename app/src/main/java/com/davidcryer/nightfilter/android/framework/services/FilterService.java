package com.davidcryer.nightfilter.android.framework.services;

import android.app.Notification;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.ColorRes;
import android.support.annotation.Nullable;
import android.support.v4.app.NotificationCompat;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.view.WindowManager;

import com.davidcryer.nightfilter.R;
import com.davidcryer.nightfilter.android.helpers.AlphaAnimationHelper;
import com.davidcryer.nightfilter.android.helpers.WindowManagerLayoutParamsFactory;
import com.davidcryer.nightfilter.platformindependent.helpers.ObjectChecker;

public class FilterService extends Service {
    private final static int ANIMATION_DURATION_FADE_MS = 200;
    private final static int REQUEST_CODE_CANCEL = 20000;
    private final static int NOTIFICATION_ID = 1;
    private final static String ARG_INTENT_NOTIFICATION_CANCEL = "cancel";
    private View filterView;

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        if (intent != null && intent.hasExtra(ARG_INTENT_NOTIFICATION_CANCEL)) {
            if (intent.getBooleanExtra(ARG_INTENT_NOTIFICATION_CANCEL, false)) {
                stopForeground(true);
                stopSelf();
            }
        }
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

    @Override
    public void onRebind(Intent intent) {
        super.onRebind(intent);
        stopForeground(true);
    }

    @Override
    public boolean onUnbind(Intent intent) {
        if (isFilterAttached()) {
            startForeground();
        }
        return true;
    }

    private void startForeground() {
        final Intent intent = new Intent(this, FilterService.class).putExtra(ARG_INTENT_NOTIFICATION_CANCEL, true);
        final Notification notification = new NotificationCompat.Builder(this)
                .setSmallIcon(R.mipmap.ic_launcher)
                .setContentTitle("Night filter")
                .setContentText("Click to remove filter")
                .setContentIntent(PendingIntent.getService(this, REQUEST_CODE_CANCEL, intent, PendingIntent.FLAG_UPDATE_CURRENT))
                .build();
        startForeground(NOTIFICATION_ID, notification);
    }

    private final Binder binder = new Binder();

    public class Binder extends android.os.Binder {

        public boolean isFilterAttached() {
            return FilterService.this.isFilterAttached();
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

    private boolean isFilterAttached() {
        return filterView != null;
    }

    private void attachFilter(@ColorRes final int color) {
        if (filterView == null) {
            filterView = new View(this);
            filterView.setAlpha(0);
            windowManager().addView(filterView, WindowManagerLayoutParamsFactory.wholeScreenLayoutParams(this));
        }
        AlphaAnimationHelper.fadeIn(filterView, ANIMATION_DURATION_FADE_MS);
        changeFilter(color);
    }

    private void changeFilter(@ColorRes final int color) {
        ObjectChecker.notNull(filterView, "filterView has not been set up");
        filterView.setBackgroundColor(ContextCompat.getColor(this, color));
    }

    private void removeFilter() {
        if (filterView != null) {
            AlphaAnimationHelper.fadeOut(filterView, ANIMATION_DURATION_FADE_MS, View.GONE, new Runnable() {
                @Override
                public void run() {
                    windowManager().removeView(filterView);
                    filterView = null;
                }
            });
        }
    }

    private WindowManager windowManager() {
        return ((WindowManager) getApplicationContext().getSystemService(WINDOW_SERVICE));
    }
}
