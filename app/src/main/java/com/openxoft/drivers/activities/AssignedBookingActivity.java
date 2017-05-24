package com.openxoft.drivers.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.google.gson.Gson;
import com.openxoft.drivers.R;

import com.openxoft.drivers.adapter.BookingAdapter;
import com.openxoft.drivers.api.ApiParams;
import com.openxoft.drivers.listener.BookingInterface;
import com.openxoft.drivers.mvp.assignedbookings.model.Booking;
import com.openxoft.drivers.mvp.assignedbookings.model.DriveLoginResponse;
import com.openxoft.drivers.mvp.assignedbookings.presenter.AssignedBookingPresenter;
import com.openxoft.drivers.mvp.assignedbookings.presenter.AssingedBookingPresenterImpl;
import com.openxoft.drivers.mvp.assignedbookings.view.AssignedBookingListView;

import com.openxoft.drivers.mvp.login.model.DriverLoginResponse;
import com.openxoft.drivers.util.AlertUtil;
import com.openxoft.drivers.util.JsonUtil;

import java.util.List;

/**
 * Created by openxoft on 19/05/17.
 */

public class AssignedBookingActivity extends BaseActivity implements AssignedBookingListView, BookingInterface {


    AssignedBookingPresenter assignedBookingPresenter;
    RecyclerView recyclerView;
    BookingAdapter bookingAdapter;
    List<Booking> bookingList;


    @Override
    int getContentView() {
        return R.layout.activity_assignedbookinglist;
    }

    @Override
    protected void onViewReady(Bundle bundle, Intent intent) {
        super.onViewReady(bundle, intent);
        initViews();
        assignedBookingPresenter = new AssingedBookingPresenterImpl(this);
        String jsonData = JsonUtil.loadJsonFromAsset("document.json", this);
        if (jsonData != null) {

            DriveLoginResponse driveLoginResponse = new Gson().fromJson(JsonUtil.loadJsonFromAsset("document.json", this), DriveLoginResponse.class);
            assignedBookingPresenter.loadData(driveLoginResponse);

        }


    }

    @Override
    public void onClick(View v) {

    }

    private void initViews() {
        recyclerView = (RecyclerView) findViewById(R.id.recyclerviewprovider);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));


    }

    private void fillData(DriveLoginResponse driveLoginResponse) {
        bookingList = driveLoginResponse.getData();
        bookingAdapter = new BookingAdapter(bookingList);
        recyclerView.setAdapter(bookingAdapter);
    }

    @Override
    public void setOnGetData(DriveLoginResponse driveLoginResponse) {
        fillData(driveLoginResponse);

    }

    @Override
    public void setOnServerError() {

    }

    @Override
    public void setOnEmptyResponseError() {

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
        Intent intent = new Intent(AssignedBookingActivity.this, AssignedBookingDetailActivity.class);
        intent.putExtra(ApiParams.KEY_BOOKING_DETAIL, bookingList.get(position));
        startActivity(intent);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu,menu);
        return true;
    }
}
