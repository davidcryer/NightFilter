package com.davidcryer.nightfilter.android.view.uimodels.control;

import android.annotation.TargetApi;
import android.os.Build;

import com.davidc.uiwrapper.UiModel;
import com.davidcryer.nightfilter.android.view.ui.control.ControlUi;

public interface ControlUiModel extends UiModel<ControlUi> {
    void onFilterServiceConnected(final ControlUi ui);
    @TargetApi(Build.VERSION_CODES.M)
    void onOverlayPermissionReturned(final ControlUi ui, final boolean permissionGranted);
    void onFilterColorChanged(final ControlUi ui, final int color);
    void onFilterToggled(final ControlUi ui);
}
