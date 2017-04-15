package com.davidcryer.nightfilter.android.view.ui.control.fragments;

import android.annotation.TargetApi;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.IBinder;
import android.provider.Settings;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.davidc.uiwrapper.UiFragment;
import com.davidcryer.nightfilter.R;
import com.davidcryer.nightfilter.android.framework.services.FilterService;
import com.davidcryer.nightfilter.android.framework.uiwrapperrepositories.UiWrapperRepository;
import com.davidcryer.nightfilter.android.view.ui.control.ControlUi;

import butterknife.BindView;

public class ControlFragment extends UiFragment<UiWrapperRepository, ControlUi.Listener> {
    private final static int REQUEST_CODE_OVERLAY_PERMISSION = 1234;
    private FilterService.Binder serviceBind;
    @BindView(R.id.toggleFilterButton)
    private View toggleFilterButton;
    @BindView(R.id.updateFilterButton)
    private View updateFilterButton;
    @BindView(R.id.colorFilterEditText)
    private EditText colorFilterEditText;

    public static ControlFragment newInstance() {
        return new ControlFragment();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void onStart() {
        super.onStart();
        final Intent intent = new Intent(getActivity(), FilterService.class);
        getActivity().bindService(intent, filterServiceConnection, Context.BIND_AUTO_CREATE);
    }

    @Override
    public void onStop() {
        super.onStop();
        getActivity().unbindService(filterServiceConnection);
    }

    private final ServiceConnection filterServiceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            serviceBind = (FilterService.Binder) service;
            if (hasEventsListener()) {
                eventsListener().onFilterServiceConnected(ui);
            }
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            serviceBind = null;
        }
    };

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (requestCode == REQUEST_CODE_OVERLAY_PERMISSION) {
                if (hasEventsListener()) {
                    eventsListener().onOverlayPermissionReturned(ui, Settings.canDrawOverlays(getActivity()));
                }
            }
        }
    }

    private final ControlUi ui = new ControlUi() {
        @Override
        @TargetApi(Build.VERSION_CODES.M)
        public boolean hasOverlayPermission() {
            return Settings.canDrawOverlays(getActivity());
        }

        @Override
        @TargetApi(Build.VERSION_CODES.M)
        public void requestOverlayPermission() {
            Intent intent = new Intent(Settings.ACTION_MANAGE_OVERLAY_PERMISSION, Uri.parse("package:" + getActivity().getPackageName()));
            startActivityForResult(intent, REQUEST_CODE_OVERLAY_PERMISSION);
        }

        @Override
        public boolean isFilterAttached() {
            return serviceBind.isFilterAttached();
        }

        @Override
        public void attachFilter(int color) {
            serviceBind.attachFilter(color);
        }

        @Override
        public void changeFilter(int color) {
            serviceBind.changeFilter(color);
        }

        @Override
        public void unAttachFilter() {
            serviceBind.unAttachFilter();
        }

        @Override
        public void showRequestingPermissionState() {

        }

        @Override
        public void animateInRequestingPermissionState() {

        }

        @Override
        public void showFilterState() {

        }

        @Override
        public void animateInFilterState() {

        }

        @Override
        public void animateInFilterStateFromRequestingPermissionState() {

        }

        @Override
        public void showPermissionNotGranted() {

        }

        @Override
        public void animateInPermissionNotGrantedFromRequestingPermissionState() {

        }
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
