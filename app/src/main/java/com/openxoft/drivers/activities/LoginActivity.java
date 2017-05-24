package com.openxoft.drivers.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.iid.FirebaseInstanceId;
import com.google.gson.Gson;
import com.openxoft.drivers.R;

import com.openxoft.drivers.api.ApiParams;
import com.openxoft.drivers.api.DriverServiceImp;
import com.openxoft.drivers.mvp.login.model.DriverLoginResponse;
import com.openxoft.drivers.mvp.login.presenter.loginImplentation.LoginPresenterImpl;
import com.openxoft.drivers.mvp.login.presenter.loginImplentation.loginInterfaces.LoginPresenter;
import com.openxoft.drivers.mvp.login.view.LoginView;
import com.openxoft.drivers.util.AppPreferenceData;
import com.openxoft.drivers.util.WarningMessages;

/**
 * Created by openxoft on 19/05/17.
 */

public class LoginActivity extends BaseActivity implements LoginView{

    Button login;
    TextView forgot;
    EditText username,password;
   LoginPresenter loginPresenter;
    private CollapsingToolbarLayout collapsingToolbarLayout = null;

    @Override
    int getContentView() {
        return R.layout.activity_login;
    }

    @Override
    protected void onViewReady(Bundle bundle, Intent intent) {
        super.onViewReady(bundle, intent);
        login=(Button)findViewById(R.id.btn_login);

        forgot=(TextView)findViewById(R.id.txt_forgotpassword);
        loginPresenter=new LoginPresenterImpl(this);
        Log.d("Device Token", FirebaseInstanceId.getInstance().getToken());
        username=(EditText)findViewById(R.id.et_username);
        password=(EditText)findViewById(R.id.et_password);
        forgot.setOnClickListener(this);
        login.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId())

        {
            case R.id.btn_login:
                loginPresenter.validate(username.getText().toString(),password.getText().toString());
                break;
            case R.id.txt_forgotpassword:
                callForgotActivity();
                break;
        }

    }
    private void toolbarSetup() {

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("Login Detail");
        collapsingToolbarLayout = (CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar);

    }

    private void callForgotActivity()
    {
        startActivity(new Intent(this,ForgotActivity.class));
    }

    @Override
    public void setUserNameError() {

        username.requestFocus();
        username.setError(WarningMessages.WRONG_USER_NAME);

    }

    @Override
    public void setPasswordError() {
        password.requestFocus();
        password.setError(WarningMessages.EMPTY_PASSWORD);
    }

    @Override
    public void navigateToAssignedBookingList() {

        DriverServiceImp.login(this,username.getText().toString(),password.getText().toString());

    }
   public  void callAssignedBookingAndSaveUserData(DriverLoginResponse driverLoginResponse)
    {
        String data=new Gson().toJson(driverLoginResponse,DriverLoginResponse.class);
        AppPreferenceData.saveStringData(this, ApiParams.TAG_DRIVER_LOGIN,ApiParams.TAG_DRIVER_LOGIN,data);
        AppPreferenceData.saveStringData(this,ApiParams.TAG_TEMP_LOGIN,ApiParams.TAG_TEMP_LOGIN,data);
        startActivity(new Intent(this,AssignedBookingActivity.class));
    }
}
