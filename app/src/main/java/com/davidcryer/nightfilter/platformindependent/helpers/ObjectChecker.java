package com.davidcryer.nightfilter.platformindependent.helpers;

public class ObjectChecker {

    public static void notNull(final Object o, final String errorMessage) {
        if (o == null) {
            throw new IllegalStateException(errorMessage);
        }
    }
}
