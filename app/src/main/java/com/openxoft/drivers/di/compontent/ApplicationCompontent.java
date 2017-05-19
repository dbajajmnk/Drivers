package com.openxoft.drivers.di.compontent;

import com.openxoft.drivers.di.module.ApplicationModule;

import javax.inject.Singleton;

import dagger.Component;


/**
 * Created by openxoft on 19/05/17.
 */
@Singleton
@Component(modules = ApplicationModule.class)

public interface ApplicationCompontent {
}
