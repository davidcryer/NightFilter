package com.davidcryer.nightfilter.android.view.uimodels;

import com.davidcryer.nightfilter.android.view.uimodels.control.ControlUiModelFactory;
import com.davidcryer.nightfilter.android.view.uimodels.control.ControlUiModelFactoryImpl;

public class UiModelFactoryFactoryImpl implements UiModelFactoryFactory {

    @Override
    public ControlUiModelFactory createControlUiModelFactory() {
        return new ControlUiModelFactoryImpl();
    }
}
