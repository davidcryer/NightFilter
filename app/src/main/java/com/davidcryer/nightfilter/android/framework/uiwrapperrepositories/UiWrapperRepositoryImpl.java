package com.davidcryer.nightfilter.android.framework.uiwrapperrepositories;

import android.os.Bundle;
import android.support.annotation.NonNull;

import com.davidc.uiwrapper.UiWrapper;
import com.davidcryer.nightfilter.android.view.ui.control.ControlUi;
import com.davidcryer.nightfilter.android.view.uimodels.control.ControlUiModel;
import com.davidcryer.nightfilter.android.view.uiwrappers.UiWrapperFactory;

import java.util.HashMap;
import java.util.Map;

class UiWrapperRepositoryImpl extends UiWrapperRepository {
    private final UiWrapperFactory uiWrapperFactory;
    private final Map<String, UiWrapper<ControlUi, ControlUi.Listener, ControlUiModel>> controlUiWrapperMap = new HashMap<>();

    UiWrapperRepositoryImpl(UiWrapperFactory uiWrapperFactory) {
        this.uiWrapperFactory = uiWrapperFactory;
    }

    @Override
    public ControlUi.Listener bind(ControlUi ui, String instanceId, final Bundle savedState) {
        return bind(ui, instanceId, controlUiWrapperMap, new UiWrapperProvider<ControlUi, ControlUi.Listener, ControlUiModel>() {
            @Override
            @NonNull
            public UiWrapper<ControlUi, ControlUi.Listener, ControlUiModel> uiWrapper() {
                return uiWrapperFactory.createControlUiWrapper(savedState);
            }
        });
    }

    @Override
    public void unbind(ControlUi ui, String instanceId, Bundle outState, boolean isConfigurationChange) {
        unbind(instanceId, controlUiWrapperMap, outState, isConfigurationChange);
    }
}
