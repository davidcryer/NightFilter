package com.davidcryer.nightfilter.platformindependent.helpers;

public class ThrowIf {

    public static void isNull(final Object o, final String errorMessage) {
        if (o == null) {
            throw new IllegalStateException(errorMessage);
        }
    }
}
