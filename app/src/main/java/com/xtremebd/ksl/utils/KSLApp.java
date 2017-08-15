package com.xtremebd.ksl.utils;

import android.app.Application;

import com.beardedhen.androidbootstrap.TypefaceProvider;

/**
 * Created by Mashnoor on 8/15/17.
 */

public class KSLApp extends Application {
    @Override public void onCreate() {
        super.onCreate();
        TypefaceProvider.registerDefaultIconSets();
    }
}
