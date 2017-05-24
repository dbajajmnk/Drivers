package com.openxoft.drivers.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.openxoft.drivers.R;
import com.openxoft.drivers.api.ApiParams;
import com.openxoft.drivers.customviews.BookingDetailPanel;
import com.openxoft.drivers.mvp.assignedbookings.model.Booking;
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
        Booking booking = getIntent().getExtras().getParcelable(ApiParams.KEY_BOOKING_DETAIL);
        Log.d("Booking id", booking.getBDBOOKDATE());
        if (booking != null) {
            bookingDetailPresenter.validateData(booking);
        }

    }

    @Override
    public void onClick(View v) {

    }

    @Override
    public void setOnError() {

    }

    @Override
    public void setOnSuccess(Booking booking) {


        Log.d("I am in Success", "Success");
        fillData(booking);

    }

    private void fillData(Booking booking) {


        LinearLayout linearLayout = (LinearLayout) findViewById(R.id.mainPanel);
        Glide.with(AssignedBookingDetailActivity.this).load(booking.getSERVICESIMGPATH()).into(imageView);

        String bookingDescription = ApiParams.KEY_BOOKING_ID + String.valueOf(booking.getBDBOOKINGID()) +
                "\n" + booking.getSERVICE() +
                "\n" + ApiParams.KEY_TITLE_STATUS + booking.getBDSTATUS() +
                "\n" + ApiParams.KEY_TITLE_PAX + "(" + booking.getPax() + ")" + ": " + booking.getPAXNAME() +
                "\n" + ApiParams.KEY_TITLE_CONF + booking.getBDConfirmationNo() +
                "\n" + ApiParams.KEY_SUPPLIED_BY + booking.getSUPCompany();

        BookingDetailPanel bookingDetailPanel = new BookingDetailPanel(AssignedBookingDetailActivity.this, getString(R.string.titlebookingdetail), bookingDescription, false);
        BookingDetailPanel bookingDetailPanel2 = new BookingDetailPanel(AssignedBookingDetailActivity.this, getString(R.string.description), booking.getSLGDESCRIPTION(), true);

        BookingDetailPanel bookingDetailPanel3 = new BookingDetailPanel(AssignedBookingDetailActivity.this, getString(R.string.Remarks), booking.getSLGREMARK(), false);
        BookingDetailPanel bookingDetailPanel4 = new BookingDetailPanel(AssignedBookingDetailActivity.this, getString(R.string.policy), booking.getCANCELLATIONPOLICY(), false);
        BookingDetailPanel dropoffPanel = new BookingDetailPanel(AssignedBookingDetailActivity.this, getString(R.string.dropoffdetails), booking.getDROPOFFDETAILS(), false);
        BookingDetailPanel pickupdetail = new BookingDetailPanel(AssignedBookingDetailActivity.this, getString(R.string.pickupdetails), booking.getPICKUPDETAILS(), false);
        linearLayout.addView(bookingDetailPanel.getView());

        if (!booking.getPICKUPDETAILS().isEmpty()) {
            linearLayout.addView(pickupdetail.getView());
        }
        if (!booking.getDROPOFFDETAILS().isEmpty()) {
            linearLayout.addView(dropoffPanel.getView());
        }
        linearLayout.addView(bookingDetailPanel2.getView());
        if (!booking.getSLGREMARK().isEmpty()) {
            linearLayout.addView(bookingDetailPanel3.getView());
        }
        linearLayout.addView(bookingDetailPanel4.getView());


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
