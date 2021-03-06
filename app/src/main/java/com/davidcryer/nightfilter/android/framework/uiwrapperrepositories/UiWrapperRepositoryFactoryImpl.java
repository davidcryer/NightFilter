package com.davidcryer.nightfilter.android.framework.uiwrapperrepositories;

import android.support.annotation.NonNull;

import com.davidc.uiwrapper.UiWrapperRepositoryFactory;
import com.davidcryer.nightfilter.android.view.uiwrappers.UiWrapperFactory;

public class UiWrapperRepositoryFactoryImpl implements UiWrapperRepositoryFactory<UiWrapperRepository> {
    private final UiWrapperFactory uiWrapperFactory;

    public UiWrapperRepositoryFactoryImpl(UiWrapperFactory uiWrapperFactory) {
        this.uiWrapperFactory = uiWrapperFactory;
    }

    @Override
    @NonNull
    public UiWrapperRepository create() {
        return new UiWrapperRepositoryImpl(uiWrapperFactory);
    }
}
