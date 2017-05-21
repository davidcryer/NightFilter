package com.davidcryer.nightfilter.android.view.ui.control;

import android.annotation.TargetApi;
import android.os.Build;
import android.support.annotation.ColorRes;

import com.davidc.uiwrapper.Ui;

public interface ControlUi extends Ui {
    @TargetApi(Build.VERSION_CODES.M)
    boolean hasOverlayPermission();
    @TargetApi(Build.VERSION_CODES.M)
    void requestOverlayPermission();
    boolean isFilterAttached();
    void attachFilter(@ColorRes final int color);
    void unAttachFilter();
    void showBlankState();
    void showControlState();
    void animateInControlStateFromBlankState();
    @TargetApi(Build.VERSION_CODES.M)
    void showPermissionNotGranted();
    @TargetApi(Build.VERSION_CODES.M)
    void animateInPermissionNotGrantedFromBlankState();

    interface Listener extends Ui.Listener {
        void onFilterServiceConnected(final ControlUi ui);
        @TargetApi(Build.VERSION_CODES.M)
        void onOverlayPermissionReturned(final ControlUi ui, final boolean permissionGranted);
        void onFilterToggled(final ControlUi ui);
    }
}
