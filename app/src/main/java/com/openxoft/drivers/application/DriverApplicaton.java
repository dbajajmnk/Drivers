package com.openxoft.drivers.application;

import android.app.Application;

import dagger.internal.DaggerCollections;

/**
 * Created by openxoft on 19/05/17.
 */

public class DriverApplicaton extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        intalizeComponents();
    }

    private void intalizeComponents() {

        //DaggerApplicationCompontent.builder().create();
    }

    @Override
    public void onTerminate() {
        super.onTerminate();
    }
}
