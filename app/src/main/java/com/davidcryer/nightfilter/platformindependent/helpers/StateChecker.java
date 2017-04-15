package com.davidcryer.nightfilter.platformindependent.helpers;

public class StateChecker {

    public static void notNull(final Object o, final String errorMessage) {
        if (o == null) {
            throw new IllegalStateException(errorMessage);
        }
    }
}
