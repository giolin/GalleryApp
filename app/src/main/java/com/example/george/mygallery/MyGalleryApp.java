package com.example.george.mygallery;

import android.app.Application;

import timber.log.Timber;

/**
 * Created by george on 2014/11/6.
 */
public class MyGalleryApp extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        if (BuildConfig.DEBUG) {
            Timber.plant(new Timber.DebugTree());
        } else {
            // TODO Crashlytics.start(this);
            // TODO Timber.plant(new CrashlyticsTree());
        }
    }
}
