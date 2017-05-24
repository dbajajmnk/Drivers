package com.openxoft.drivers.util;

import android.app.ProgressDialog;
import android.content.Context;

/**
 * Created by openxoft on 19/05/17.
 */

public class ProgressUtil {

    private static ProgressDialog mProgressDialog=null;

    public static void showProgressDialog(String tile, String message, Context context)
    {
        mProgressDialog=new ProgressDialog(context);
        mProgressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        mProgressDialog.setTitle(tile);
        mProgressDialog.setMessage(message);
        mProgressDialog.show();
    }
    public static void hideProgressDialog()
    {
        if(mProgressDialog!=null && mProgressDialog.isShowing())
        {
            mProgressDialog.dismiss();
        }
    }

}
