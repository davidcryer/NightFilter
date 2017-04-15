package com.davidcryer.nightfilter.android.view.ui.control;

import android.annotation.TargetApi;
import android.os.Build;

import com.davidc.uiwrapper.Ui;

public interface ControlUi extends Ui {
    @TargetApi(Build.VERSION_CODES.M)
    boolean hasOverlayPermission();
    @TargetApi(Build.VERSION_CODES.M)
    void requestOverlayPermission();
    boolean isFilterAttached();
    void attachFilter(final int color);
    void changeFilter(final int color);
    void unAttachFilter();

    interface Listener extends Ui.Listener {
        @TargetApi(Build.VERSION_CODES.M)
        void onOverlayPermissionReturned(final boolean permissionGranted);
        void onFilterServiceConnected();
        void onFilterColorChanged(final int color);
        void onFilterToggled();
    }
}
