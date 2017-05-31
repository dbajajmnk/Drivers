package com.openxoft.drivers.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.google.android.gms.common.api.Api;
import com.google.gson.Gson;
import com.openxoft.drivers.R;

import com.openxoft.drivers.adapter.BookingAdapter;
import com.openxoft.drivers.api.ApiParams;
import com.openxoft.drivers.api.DriverServiceImp;
import com.openxoft.drivers.listener.BookingInterface;
import com.openxoft.drivers.mvp.assignedbookings.model.DutiesForDriver;
import com.openxoft.drivers.mvp.assignedbookings.model.Duty;
import com.openxoft.drivers.mvp.assignedbookings.presenter.AssignedBookingPresenter;
import com.openxoft.drivers.mvp.assignedbookings.presenter.AssingedBookingPresenterImpl;
import com.openxoft.drivers.mvp.assignedbookings.view.AssignedBookingListView;

import com.openxoft.drivers.mvp.login.model.DriverLoginResponse;
import com.openxoft.drivers.mvp.login.model.User;
import com.openxoft.drivers.util.AlertUtil;
import com.openxoft.drivers.util.AppPreferenceData;
import com.openxoft.drivers.util.JsonUtil;

import java.util.List;

/**
 * Created by openxoft on 19/05/17.
 */

public class AssignedBookingActivity extends BaseActivity implements  BookingInterface,AssignedBookingListView {



    RecyclerView recyclerView;
    BookingAdapter dutiesAdapter;
    List<Duty> bookingList;

  AssignedBookingPresenter assignedBookingPresenter;





    @Override
    int getContentView() {
        return R.layout.activity_assignedbookinglist;
    }



    @Override
    protected void onViewReady(Bundle bundle, Intent intent) {
        super.onViewReady(bundle, intent);
        initViews();
        assignedBookingPresenter=new AssingedBookingPresenterImpl(this);
        DriverLoginResponse driverLoginResponse= new Gson().fromJson(AppPreferenceData.getString(this,ApiParams.TAG_DRIVER_LOGIN,ApiParams.TAG_DRIVER_LOGIN),DriverLoginResponse.class);
        User user=driverLoginResponse.getData().get(0);
        DriverServiceImp.getValues(this,user.getDGID().toString(),user.getDGTYPE());








    }


    @Override
    public void onClick(View v) {

    }

    private void initViews() {
        recyclerView = (RecyclerView) findViewById(R.id.recyclerviewprovider);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));


    }

    private void fillData(DutiesForDriver dutiesForDriver) {
        bookingList = dutiesForDriver.getData();
        dutiesAdapter = new BookingAdapter(bookingList);
        recyclerView.setAdapter(dutiesAdapter);
    }

    public void validateData(DutiesForDriver dutiesForDriver)
    {
        assignedBookingPresenter.loadData(dutiesForDriver);
    }




    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId()==R.id.action_logout)
        {
            AlertUtil.showAlertDailog(this,new String[]{"Warning","Are u Sure you want to Logout?","Yes","No"});
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onViewDetail(int position) {
        Duty duty=bookingList.get(position);
        Intent intent = new Intent(AssignedBookingActivity.this, AssignedBookingDetailActivity.class);
        intent.putExtra(ApiParams.KEY_BID,duty.getBID().toString());
        intent.putExtra(ApiParams.KEY_BDID,duty.getDGBBDID().toString());
        startActivity(intent);


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu,menu);
        return true;
    }


    @Override
    public void setOnGetData(DutiesForDriver driveLoginResponse) {
        fillData(driveLoginResponse);

    }

    @Override
    public void setOnServerError() {
        Log.d("Error","Error on Server");

    }

    @Override
    public void setOnEmptyResponseError() {

        Log.d("Emapty Response","Blank Response");

    }
}
