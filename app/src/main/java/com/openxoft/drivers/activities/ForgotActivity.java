package com.openxoft.drivers.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.openxoft.drivers.R;
import com.openxoft.drivers.mvp.forgotpassword.presenter.ForgotPresenter;
import com.openxoft.drivers.mvp.forgotpassword.presenter.ForgotPresenterImpl;
import com.openxoft.drivers.mvp.forgotpassword.view.ForgotView;
import com.openxoft.drivers.util.WarningMessages;

/**
 * Created by openxoft on 19/05/17.
 */

public class ForgotActivity extends BaseActivity implements ForgotView{


    ForgotPresenter forgotPresenter;
    EditText username;
    Button submit;
    @Override
    int getContentView() {
       return R.layout.activity_forgoutpassword;
    }


    @Override
    protected void onViewReady(Bundle bundle, Intent intent) {
        super.onViewReady(bundle, intent);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        initViews();
        forgotPresenter=new ForgotPresenterImpl(this);
    }

    @Override
    public void onClick(View v) {
        startActivity(new Intent(this,LoginActivity.class));

    }

    @Override
    public void setUserNameError() {
            username.requestFocus();
            username.setError(WarningMessages.WRONG_USER_NAME);
    }

    @Override
    public void setOnSuccess() {
        Log.d("I am in Success","Success");

    }
    private void initViews()
    {
        username=(EditText)findViewById(R.id.et_username);
        submit=(Button)findViewById(R.id.btnsubmit);
        submit.setOnClickListener(this);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId()==android.R.id.home)
        {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }
}
