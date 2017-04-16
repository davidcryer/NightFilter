package com.davidcryer.nightfilter.android.view.uimodels.control;

public class ControlUiModelFactoryImpl implements ControlUiModelFactory {
    private final static ControlUiModelImpl.UiState DEFAULT_UI_STATE = ControlUiModelImpl.UiState.SHOW_BLANK;

    @Override
    public ControlUiModel create() {
        return new ControlUiModelImpl(DEFAULT_UI_STATE);
    }
}
