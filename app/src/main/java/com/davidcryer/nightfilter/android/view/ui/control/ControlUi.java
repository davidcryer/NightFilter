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
    void showRequestingPermissionState();
    void animateInRequestingPermissionState();
    void showFilterState();
    void animateInFilterState();
    void animateInFilterStateFromRequestingPermissionState();
    void showPermissionNotGranted();
    void animateInPermissionNotGrantedFromRequestingPermissionState();

    interface Listener extends Ui.Listener {
        void onFilterServiceConnected(final ControlUi ui);
        @TargetApi(Build.VERSION_CODES.M)
        void onOverlayPermissionReturned(final ControlUi ui, final boolean permissionGranted);
        void onFilterColorChanged(final ControlUi ui, final int color);
        void onFilterToggled(final ControlUi ui);
    }
}
