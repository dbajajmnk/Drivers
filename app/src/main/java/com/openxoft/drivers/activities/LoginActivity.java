package com.openxoft.drivers.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.openxoft.drivers.R;

/**
 * Created by openxoft on 19/05/17.
 */

public class LoginActivity extends BaseActivity {

    Button login;
    TextView forgot;
    EditText username,password;


    @Override
    int getContentView() {
        return R.layout.activity_login;
    }

    @Override
    protected void onViewReady(Bundle bundle, Intent intent) {
        super.onViewReady(bundle, intent);
        login=(Button)findViewById(R.id.btn_login);
        forgot=(TextView)findViewById(R.id.txt_forgotpassword);
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

                break;
            case R.id.txt_forgotpassword:

                break;
        }

    }

    private void callForgotActivity()
    {
        startActivity(new Intent(this,ForgotActivity.class));
    }
}
