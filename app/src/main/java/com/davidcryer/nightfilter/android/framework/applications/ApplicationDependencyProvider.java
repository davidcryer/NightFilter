package com.davidcryer.nightfilter.android.framework.applications;

import com.davidc.uiwrapper.UiWrapperRepositoryFactory;
import com.davidcryer.nightfilter.android.framework.uiwrapperrepositories.UiWrapperRepository;
import com.davidcryer.nightfilter.android.framework.uiwrapperrepositories.UiWrapperRepositoryFactoryImpl;
import com.davidcryer.nightfilter.android.view.uiwrappers.UiWrapperFactory;
import com.davidcryer.nightfilter.android.view.uiwrappers.UiWrapperFactoryImpl;
import com.davidcryer.nightfilter.android.view.uimodels.UiModelFactoryFactory;
import com.davidcryer.nightfilter.android.view.uimodels.UiModelFactoryFactoryImpl;

class ApplicationDependencyProvider {

    private ApplicationDependencyProvider() {

    }

    static UiWrapperRepositoryFactory<UiWrapperRepository> createUiWrapperRepositoryFactory() {
        return new UiWrapperRepositoryFactoryImpl(createUiWrapperFactory());
    }

    private static UiWrapperFactory createUiWrapperFactory() {
        return new UiWrapperFactoryImpl(createUiModelFactoryFactory());
    }

    private static UiModelFactoryFactory createUiModelFactoryFactory() {
        return new UiModelFactoryFactoryImpl();
    }
}
