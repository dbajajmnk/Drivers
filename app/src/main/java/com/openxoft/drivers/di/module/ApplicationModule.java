package com.openxoft.drivers.di.module;

import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import java.util.concurrent.TimeUnit;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by openxoft on 19/05/17.
 */
@Module
public class ApplicationModule {

    private String baseUrl;


    public ApplicationModule(String baseUrl)
    {
        this.baseUrl=baseUrl;
    }

    @Singleton
    @Provides
    RxJava2CallAdapterFactory providerRxJava2CallAdapterFactory()
    {

        return RxJava2CallAdapterFactory.create();
    }

    @Singleton
    @Provides
   OkHttpClient provideHttpClient()
    {
        return new OkHttpClient.Builder().connectTimeout(20, TimeUnit.SECONDS).readTimeout(20,TimeUnit.SECONDS).build();
    }

    @Singleton
    @Provides
    Retrofit provideReftrofit(OkHttpClient okHttpClient, GsonConverterFactory gsonConverterFactory, RxJava2CallAdapterFactory rxJava2CallAdapterFactory)
    {
        return new Retrofit.Builder().baseUrl(baseUrl).addConverterFactory(gsonConverterFactory).addCallAdapterFactory(rxJava2CallAdapterFactory).client(okHttpClient).build();
    }

    @Singleton
    @Provides
    GsonConverterFactory gsonConverterFactory()
    {
        return  GsonConverterFactory.create();
    }




}
