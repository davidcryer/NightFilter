package com.davidcryer.nightfilter.android.view.uiwrappers;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.ColorRes;

import com.davidc.uiwrapper.UiWrapper;
import com.davidcryer.nightfilter.R;
import com.davidcryer.nightfilter.android.view.ui.control.ControlUi;
import com.davidcryer.nightfilter.android.view.uimodels.control.ControlUiModel;
import com.davidcryer.nightfilter.android.view.uimodels.control.ControlUiModelFactory;

class ControlUiWrapper extends UiWrapper<ControlUi, ControlUi.Listener, ControlUiModel> {
    private final static String ARG_VIEW_MODEL = ControlUiModel.class.getSimpleName();

    private ControlUiWrapper(ControlUiModel uiModel) {
        super(uiModel);
    }

    static ControlUiWrapper newInstance(final ControlUiModelFactory uiModelFactory) {
        return new ControlUiWrapper(uiModelFactory.create());
    }

    static ControlUiWrapper savedElseNewInstance(final ControlUiModelFactory uiModelFactory, final Bundle savedState) {
        final ControlUiModel uiModel = savedState.getParcelable(ARG_VIEW_MODEL);
        return uiModel == null ? newInstance(uiModelFactory) : new ControlUiWrapper(uiModel);
    }

    @Override
    protected ControlUi.Listener uiListener() {
        return uiListener;
    }

    private final ControlUi.Listener uiListener = new ControlUi.Listener() {
        @Override
        public void onFilterServiceConnected(ControlUi ui) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                if (!ui.hasOverlayPermission()) {
                    uiModel().requestOverlayPermission(ui);
                    return;
                }
            }
            uiModel().animateInControlState(ui);
        }

        @Override
        public void onOverlayPermissionReturned(ControlUi ui, boolean permissionGranted) {
            if (permissionGranted) {
                uiModel().animateInControlState(ui);
            } else {
                uiModel().animateInPermissionNotGranted(ui);
            }
        }

        @Override
        public void onFilterColorChanged(ControlUi ui, @ColorRes int color) {
            uiModel().changeFilter(ui, color);
        }

        @Override
        public void onFilterToggled(ControlUi ui) {
            if (ui.isFilterAttached()) {
                uiModel().unAttachFilter(ui);
            } else {
                uiModel().attachFilter(ui, R.color.filter_blue);
            }
        }
    };
}
