package com.openxoft.drivers.api;

import io.reactivex.Observable;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * Created by openxoft on 19/05/17.
 */

public interface DriverService {

    @FormUrlEncoded
    @POST(ApiParams.SUB_URL)
    //Call<String> login(@Field(ApiParams.USER_NAME)String username,@Field(ApiParams.PASSWORD)String password);
    Observable<String>login(@Field(ApiParams.USER_NAME)String username,@Field(ApiParams.PASSWORD)String password);

}