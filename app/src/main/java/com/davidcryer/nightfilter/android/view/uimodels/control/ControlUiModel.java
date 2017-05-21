package com.davidcryer.nightfilter.android.view.uimodels.control;

import android.annotation.TargetApi;
import android.os.Build;
import android.support.annotation.ColorRes;

import com.davidc.uiwrapper.UiModel;
import com.davidcryer.nightfilter.android.view.ui.control.ControlUi;

public interface ControlUiModel extends UiModel<ControlUi> {
    void showControl(final ControlUi ui);
    @TargetApi(Build.VERSION_CODES.M)
    void showPermissionNotGranted(final ControlUi ui);
}
