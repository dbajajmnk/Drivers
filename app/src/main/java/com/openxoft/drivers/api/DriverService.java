package com.openxoft.drivers.api;

import com.openxoft.drivers.mvp.assignedbookings.model.DutiesForDriver;
import com.openxoft.drivers.mvp.bookingdetail.model.BookingDetailResponse;
import com.openxoft.drivers.mvp.login.model.DriverLoginResponse;

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
    Observable<DriverLoginResponse>login(@Field(ApiParams.USER_NAME)String username, @Field(ApiParams.PASSWORD)String password,@Field(ApiParams.DEVICE_ID)String deviceId,@Field(ApiParams.KEY_TAG) String tag);
    @FormUrlEncoded
    @POST(ApiParams.SUB_URL)
    Call<DutiesForDriver>getDuties(@Field(ApiParams.KEY_DRIVER_GUIDE_ID)String dgid,@Field(ApiParams.KEY_TYPE)String type,@Field(ApiParams.KEY_TAG)String tag);
    @FormUrlEncoded
    @POST(ApiParams.SUB_URL)
    Call<BookingDetailResponse>getDutyDetail(@Field(ApiParams.KEY_BID)String bid, @Field(ApiParams.KEY_BDID)String bdid, @Field(ApiParams.KEY_TAG)String tag);


}
