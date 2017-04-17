package com.davidcryer.nightfilter.android.helpers;

import android.view.View;

public class AlphaAnimationHelper {

    public static void fadeOut(final View view, final long maxDuration, final int finalVisibility, final Runnable endAction) {
        view.animate()
                .alpha(0)
                .setDuration(alphaAnimationDuration(view.getAlpha(), 0, maxDuration))
                .withEndAction(new Runnable() {
                    @Override
                    public void run() {
                        view.setVisibility(finalVisibility);
                        view.setAlpha(1);
                        if (endAction != null) {
                            endAction.run();
                        }
                    }
                })
                .start();
    }

    public static void fadeIn(final View view, final long maxDuration) {
        view.animate()
                .alpha(1)
                .setDuration(alphaAnimationDuration(view.getAlpha(), 1, maxDuration))
                .withStartAction(new Runnable() {
                    @Override
                    public void run() {
                        view.setVisibility(View.VISIBLE);
                    }
                })
                .start();
    }

    private static long alphaAnimationDuration(final float start, final float end, final long maxDuration) {
        return (long) (Math.abs(start - end) * maxDuration);
    }
}
