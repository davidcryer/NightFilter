package com.davidcryer.nightfilter.android.view.uimodels.control;

import android.annotation.TargetApi;
import android.os.Build;

import com.davidc.uiwrapper.UiModel;
import com.davidcryer.nightfilter.android.view.ui.control.ControlUi;

public interface ControlUiModel extends UiModel<ControlUi> {
    @TargetApi(Build.VERSION_CODES.M)
    void requestOverlayPermission(final ControlUi ui);
    void attachFilter(final ControlUi ui, final int color);
    void changeFilter(final ControlUi ui, final int color);
    void unAttachFilter(final ControlUi ui);
    void animateInFilterState(final ControlUi ui);
    @TargetApi(Build.VERSION_CODES.M)
    void animateInPermissionNotGranted(final ControlUi ui);
}
