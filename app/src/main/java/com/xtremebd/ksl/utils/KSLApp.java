package com.xtremebd.ksl.utils;

import android.app.Application;

import com.beardedhen.androidbootstrap.TypefaceProvider;
import com.xtremebd.ksl.R;

import uk.co.chrisjenx.calligraphy.CalligraphyConfig;

/**
 * Created by Mashnoor on 8/15/17.
 */

public class KSLApp extends Application {
    @Override public void onCreate() {
        super.onCreate();
        TypefaceProvider.registerDefaultIconSets();
        CalligraphyConfig.initDefault(new CalligraphyConfig.Builder()
                .setDefaultFontPath("fonts/Meta-Bold-Lf.ttf")
                .setFontAttrId(R.attr.fontPath)
                .build()
        );
    }
}
