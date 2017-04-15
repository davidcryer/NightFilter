package com.davidcryer.nightfilter.android.view.uiwrappers;

import android.os.Bundle;

public interface UiWrapperFactory {
    ControlUiWrapper createControlUiWrapper(final Bundle savedState);
}
