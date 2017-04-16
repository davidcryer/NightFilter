package com.davidcryer.nightfilter.android.view.uimodels.control;

import android.annotation.TargetApi;
import android.os.Build;
import android.os.Parcel;
import android.support.annotation.ColorRes;

import com.davidcryer.nightfilter.android.view.ui.control.ControlUi;

class ControlUiModelImpl implements ControlUiModel {
    enum UiState {SHOW_BLANK, SHOW_CONTROL, SHOW_PERMISSION_DENIED}
    private UiState uiState;

    ControlUiModelImpl(final UiState uiState) {
        this.uiState = uiState;
    }

    private ControlUiModelImpl(final Parcel parcel) {
        uiState = (UiState) parcel.readSerializable();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeSerializable(uiState);
    }

    final static Creator<ControlUiModel> CREATOR = new Creator<ControlUiModel>() {
        @Override
        public ControlUiModel createFromParcel(Parcel source) {
            return new ControlUiModelImpl(source);
        }

        @Override
        public ControlUiModel[] newArray(int size) {
            return new ControlUiModel[size];
        }
    };

    @TargetApi(Build.VERSION_CODES.M)
    @Override
    public void requestOverlayPermission(ControlUi ui) {
        if (ui != null) {
            ui.requestOverlayPermission();
        }
    }

    @Override
    public void attachFilter(ControlUi ui, @ColorRes int color) {
        if (ui != null) {
            ui.attachFilter(color);
        }
    }

    @Override
    public void changeFilter(ControlUi ui, @ColorRes int color) {
        if (ui != null) {
            ui.changeFilter(color);
        }
    }

    @Override
    public void unAttachFilter(ControlUi ui) {
        if (ui != null) {
            ui.unAttachFilter();
        }
    }

    @Override
    public void animateInControlState(ControlUi ui) {
        uiState = UiState.SHOW_CONTROL;
        if (ui != null) {
            ui.animateInControlState();
        }
    }

    @TargetApi(Build.VERSION_CODES.M)
    @Override
    public void animateInPermissionNotGranted(ControlUi ui) {
        uiState = UiState.SHOW_PERMISSION_DENIED;
        if (ui != null) {
            ui.animateInPermissionNotGranted();
        }
    }

    @Override
    public void onto(ControlUi ui) {
        switch (uiState) {
            case SHOW_BLANK: {
                ui.showBlankState();
                break;
            }
            case SHOW_CONTROL: {
                ui.showControlState();
                break;
            }
            case SHOW_PERMISSION_DENIED: {
                ui.showPermissionNotGranted();
                break;
            }
        }
    }
}
