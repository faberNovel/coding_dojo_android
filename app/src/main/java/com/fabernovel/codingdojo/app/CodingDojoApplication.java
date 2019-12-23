package com.fabernovel.codingdojo.app;

import android.app.Application;
import com.fabernovel.codingdojo.BuildConfig;
import timber.log.Timber;

public class CodingDojoApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        if (BuildConfig.DEBUG) {
            Timber.plant(new Timber.DebugTree());
        }
    }
}
