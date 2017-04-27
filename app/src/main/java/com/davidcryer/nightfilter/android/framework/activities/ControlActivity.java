package com.davidcryer.nightfilter.android.framework.activities;

import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBar;

import com.davidc.uiwrapper.SingleContentContainerWithAppBarActivity;
import com.davidcryer.nightfilter.android.framework.uiwrapperrepositories.UiWrapperRepository;
import com.davidcryer.nightfilter.android.view.ui.control.fragments.ControlFragment;

public class ControlActivity extends SingleContentContainerWithAppBarActivity<UiWrapperRepository> {

    @Override
    protected void setupActionBar(@NonNull ActionBar actionBar) {

    }

    @Override
    @NonNull
    protected Fragment initialFragment() {
        return ControlFragment.newInstance();
    }
}
