package com.davidcryer.nightfilter.android.view.ui.control.fragments;

import android.os.Bundle;

import com.davidc.uiwrapper.UiFragment;
import com.davidcryer.nightfilter.android.framework.uiwrapperrepositories.UiWrapperRepository;
import com.davidcryer.nightfilter.android.view.ui.control.ControlUi;

public class ControlFragment extends UiFragment<UiWrapperRepository, ControlUi.Listener> {

    public static ControlFragment newInstance() {
        return new ControlFragment();
    }

    private final ControlUi ui = new ControlUi() {

    };

    @Override
    protected ControlUi.Listener bind(UiWrapperRepository uiWrapperRepository, String instanceId, Bundle savedInstanceState) {
        return uiWrapperRepository.bind(ui, instanceId, savedInstanceState);
    }

    @Override
    protected void unbind(UiWrapperRepository uiWrapperRepository, String instanceId, Bundle outState, boolean isConfigurationChange) {
        uiWrapperRepository.unbind(ui, instanceId, outState, isConfigurationChange);
    }
}
