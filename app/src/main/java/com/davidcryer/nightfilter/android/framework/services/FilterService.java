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
import com.davidcryer.nightfilter.android.helpers.WindowManagerLayoutParamsFactory;
import com.davidcryer.nightfilter.platformindependent.helpers.StateChecker;

public class FilterService extends Service {
    private final static int REQUEST_CODE_CANCEL = 20000;
    private final static int NOTIFICATION_ID = 1;
    private final static String ARG_INTENT_NOTIFICATION_CANCEL = "cancel";
    private View filterView;

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        if (intent.hasExtra(ARG_INTENT_NOTIFICATION_CANCEL)) {
            if (intent.getBooleanExtra(ARG_INTENT_NOTIFICATION_CANCEL, false)) {
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

        public void startForeground() {
            FilterService.this.startForeground();
        }
    }

    private void attachFilter(@ColorRes final int color) {
        if (filterView == null) {
            filterView = new View(this);
            windowManager().addView(filterView, WindowManagerLayoutParamsFactory.wholeScreenLayoutParams(this));
        }
        changeFilter(color);
    }

    private void changeFilter(@ColorRes final int color) {
        StateChecker.notNull(filterView, "filterView has not been set up");
        filterView.setBackgroundColor(ContextCompat.getColor(this, color));
    }

    private void removeFilter() {
        if (filterView != null) {
            windowManager().removeView(filterView);
            filterView = null;
        }
    }

    private WindowManager windowManager() {
        return ((WindowManager) getApplicationContext().getSystemService(WINDOW_SERVICE));
    }

    private void startForeground() {
        final Intent intent = new Intent(this, FilterService.class);
        intent.putExtra(ARG_INTENT_NOTIFICATION_CANCEL, true);
        final Notification notification = new NotificationCompat.Builder(this)
                .setSmallIcon(R.mipmap.ic_launcher)
                .setContentTitle("Night filter")
                .setContentText("Click to remove filter")
                .setContentIntent(PendingIntent.getService(this, REQUEST_CODE_CANCEL, intent, PendingIntent.FLAG_UPDATE_CURRENT))
                .setAutoCancel(true)
                .build();
        startForeground(NOTIFICATION_ID, notification);
    }
}
