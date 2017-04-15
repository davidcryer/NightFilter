package com.davidcryer.nightfilter.android.framework.uiwrapperrepositories;

import android.os.Bundle;

import com.davidc.uiwrapper.BaseUiWrapperRepository;
import com.davidcryer.nightfilter.android.view.ui.control.ControlUi;

public abstract class UiWrapperRepository extends BaseUiWrapperRepository {
    public abstract ControlUi.Listener bind(final ControlUi ui, final String instanceId, final Bundle savedState);
    public abstract void unbind(final ControlUi ui, final String instanceId, final Bundle outState, final boolean isConfigurationChange);
}
