package com.openxoft.drivers.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.openxoft.drivers.R;
import com.openxoft.drivers.api.ApiParams;
import com.openxoft.drivers.api.DriverServiceImp;
import com.openxoft.drivers.customviews.BookingDetailPanel;
import com.openxoft.drivers.mvp.assignedbookings.model.DutiesForDriver;
import com.openxoft.drivers.mvp.assignedbookings.model.Duty;
import com.openxoft.drivers.mvp.bookingdetail.model.Booking;
import com.openxoft.drivers.mvp.bookingdetail.model.BookingDetailResponse;
import com.openxoft.drivers.mvp.bookingdetail.presenter.BookingDetailPresenter;
import com.openxoft.drivers.mvp.bookingdetail.presenter.BookingDetailPresenterImpl;
import com.openxoft.drivers.mvp.bookingdetail.view.BookingDetailView;

/**
 * Created by openxoft on 19/05/17.
 */

public class AssignedBookingDetailActivity extends BaseActivity implements BookingDetailView {

    ImageView imageView;
    private CollapsingToolbarLayout collapsingToolbarLayout = null;
    BookingDetailPresenter bookingDetailPresenter;
    View view;

    @Override
    int getContentView() {
        return R.layout.activity_bookingdetail;
    }

    @Override
    protected void onViewReady(Bundle bundle, Intent intent) {
        super.onViewReady(bundle, intent);
        imageView = (ImageView) findViewById(R.id.service_image);
        toolbarSetup();
        bookingDetailPresenter = new BookingDetailPresenterImpl(this);
        if(getIntent().getExtras()!=null)
        {
            String bookingid=getIntent().getExtras().getString(ApiParams.KEY_BID);
            String dbid=getIntent().getExtras().getString(ApiParams.KEY_BDID);
            Log.d("Bid",bookingid);
            Log.d("BDid",dbid);
            DriverServiceImp.getDutyDetail(this,bookingid,dbid,bookingDetailPresenter);
        }



    }



    @Override
    public void onClick(View v) {

    }

    @Override
    public void setOnError() {
        Toast.makeText(this,"Sorry No Detail Found ",Toast.LENGTH_LONG).show();
        finish();

    }

    @Override
    public void setOnSuccess(BookingDetailResponse booking) {


        Log.d("I am in Success", "Success");
        fillData(booking.getData().get(0));

    }

    private void fillData(Booking booking) {


        LinearLayout linearLayout = (LinearLayout) findViewById(R.id.mainPanel);
        Glide.with(AssignedBookingDetailActivity.this).load(booking.getSERVICESIMGPATH()).into(imageView);

        String bookingDescription = ApiParams.KEY_BOOKING_ID + String.valueOf(booking.getPICKUPDETAILS()) +
                "\n" + booking.getSERVICE() +
                "\n" + ApiParams.KEY_TITLE_STATUS + booking.getBDSTATUS() +
                "\n" + ApiParams.KEY_TITLE_PAX + "(" + booking.getPax() + ")" + ": " + booking.getPAXNAME() +
                "\n" + ApiParams.KEY_TITLE_CONF + booking.getBDConfirmationNo() +
                "\n" + ApiParams.KEY_SUPPLIED_BY + booking.getSUPCompany();

        BookingDetailPanel bookingDetailPanel = new BookingDetailPanel(AssignedBookingDetailActivity.this, getString(R.string.titlebookingdetail), bookingDescription, false);


        BookingDetailPanel bookingDetailPanel3 = new BookingDetailPanel(AssignedBookingDetailActivity.this, getString(R.string.Remarks), booking.getSLGREMARK(), false);

        BookingDetailPanel dropoffPanel = new BookingDetailPanel(AssignedBookingDetailActivity.this, getString(R.string.dropoffdetails), booking.getDROPOFFDETAILS(), false);
        BookingDetailPanel pickupdetail = new BookingDetailPanel(AssignedBookingDetailActivity.this, getString(R.string.pickupdetails), booking.getPICKUPDETAILS(), false);
        linearLayout.addView(bookingDetailPanel.getView());

        if (!booking.getPICKUPDETAILS().isEmpty()) {
            linearLayout.addView(pickupdetail.getView());
        }
        if (!booking.getDROPOFFDETAILS().isEmpty()) {
            linearLayout.addView(dropoffPanel.getView());
        }

        if (!booking.getSLGREMARK().isEmpty()) {
            linearLayout.addView(bookingDetailPanel3.getView());
        }



    }

    private void toolbarSetup() {

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
     actionBar.setTitle("Duty Detail");
        collapsingToolbarLayout = (CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                break;
            case R.id.action_logout:

                break;
        }
        return super.onOptionsItemSelected(item);


    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu,menu);
        return true;
    }
}
