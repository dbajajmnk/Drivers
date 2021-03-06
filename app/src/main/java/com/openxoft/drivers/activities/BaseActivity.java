package com.openxoft.drivers.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.CallSuper;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.openxoft.drivers.R;
import com.openxoft.drivers.util.KeyboardUtils;

/**
 * Created by openxoft on 19/05/17.
 */

public abstract  class BaseActivity extends AppCompatActivity implements View.OnClickListener{

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getContentView());
        onViewReady(savedInstanceState,getIntent());
    }

    @CallSuper
    protected void onViewReady(Bundle bundle, Intent intent)
    {

    }
    abstract int getContentView();

    @Override
    public void finish() {
        super.finish();
        onOverridePendingTransitionExit();
    }

    @Override
    public void startActivity(Intent intent) {
        super.startActivity(intent);
        onOverridePendingTransitionEnter();
    }
    protected void onOverridePendingTransitionEnter()
    {
            overridePendingTransition(R.anim.slide_from_right,R.anim.slide_to_left);
    }
    protected void onOverridePendingTransitionExit()
    {
        overridePendingTransition(R.anim.slide_fom_left,R.anim.slide_to_right);
    }

}


