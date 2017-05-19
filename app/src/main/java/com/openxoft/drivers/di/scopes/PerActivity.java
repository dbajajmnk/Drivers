package com.openxoft.drivers.di.scopes;

/**
 * Created by openxoft on 19/05/17.
 */

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import javax.inject.Scope;


@Scope
@Retention(RetentionPolicy.RUNTIME)
public @interface PerActivity {

}
