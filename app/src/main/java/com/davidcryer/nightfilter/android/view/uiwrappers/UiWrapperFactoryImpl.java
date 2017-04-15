package com.davidcryer.nightfilter.android.view.uiwrappers;

import android.os.Bundle;

import com.davidcryer.nightfilter.android.view.uimodels.UiModelFactoryFactory;

public class UiWrapperFactoryImpl implements UiWrapperFactory {
    private final UiModelFactoryFactory uiModelFactoryFactory;

    public UiWrapperFactoryImpl(UiModelFactoryFactory uiModelFactoryFactory) {
        this.uiModelFactoryFactory = uiModelFactoryFactory;
    }

    @Override
    public ControlUiWrapper createControlUiWrapper(Bundle savedState) {
        return savedState == null
                ? ControlUiWrapper.newInstance(uiModelFactoryFactory.createControlUiModelFactory())
                : ControlUiWrapper.savedElseNewInstance(uiModelFactoryFactory.createControlUiModelFactory(), savedState);
    }
}
