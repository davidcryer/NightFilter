package com.davidcryer.nightfilter.android.framework.uiwrapperrepositories;

import android.os.Bundle;

import com.davidc.uiwrapper.UiWrapper;
import com.davidcryer.nightfilter.android.view.ui.control.ControlUi;
import com.davidcryer.nightfilter.android.view.uiwrappers.UiWrapperFactory;

import java.util.HashMap;
import java.util.Map;

class UiWrapperRepositoryImpl extends UiWrapperRepository {
    private final UiWrapperFactory uiWrapperFactory;
    private final Map<String, UiWrapper<ControlUi, ControlUi.Listener>> controlUiWrapperMap = new HashMap<>();

    UiWrapperRepositoryImpl(UiWrapperFactory uiWrapperFactory) {
        this.uiWrapperFactory = uiWrapperFactory;
    }

    @Override
    public ControlUi.Listener bind(ControlUi ui, String instanceId, final Bundle savedState) {
        return bind(ui, instanceId, controlUiWrapperMap, new UiWrapperProvider<ControlUi, ControlUi.Listener>() {
            @Override
            public UiWrapper<ControlUi, ControlUi.Listener> uiWrapper() {
                return uiWrapperFactory.createControlUiWrapper(savedState);
            }
        });
    }

    @Override
    public void unbind(ControlUi ui, String instanceId, Bundle outState, boolean isConfigurationChange) {
        unbind(instanceId, controlUiWrapperMap, outState, isConfigurationChange);
    }
}
