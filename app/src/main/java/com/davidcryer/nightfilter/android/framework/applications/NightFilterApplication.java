package com.davidcryer.nightfilter.android.framework.applications;

import android.app.Application;

import com.davidc.uiwrapper.UiWrapperRepositoryFactory;
import com.davidcryer.nightfilter.android.framework.uiwrapperrepositories.UiWrapperRepository;

public class NightFilterApplication extends Application implements UiWrapperRepositoryFactory<UiWrapperRepository> {
    private UiWrapperRepositoryFactory<UiWrapperRepository> uiWrapperRepositoryFactory;

    @Override
    public void onCreate() {
        super.onCreate();
        uiWrapperRepositoryFactory = ApplicationDependencyProvider.createUiWrapperRepositoryFactory();
    }

    @Override
    public UiWrapperRepository create() {
        return uiWrapperRepositoryFactory.create();
    }
}
