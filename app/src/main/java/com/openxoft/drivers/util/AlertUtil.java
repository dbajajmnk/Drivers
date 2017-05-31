package com.openxoft.drivers.util;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.provider.Settings;
import android.support.v7.app.AlertDialog;

import com.openxoft.drivers.activities.LoginActivity;
import com.openxoft.drivers.api.ApiParams;

/**
 * Created by openxoft on 19/05/17.
 */

public class AlertUtil {

    public static void showAlertDailog(final Context context,
                                       String... values)
    {
        AlertDialog.Builder builder=new AlertDialog.Builder(context).
                setTitle(values[0]).
                setMessage(values[1]).setPositiveButton(values[2], new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                AppPreferenceData.clearData(context, ApiParams.TAG_DRIVER_LOGIN);
                context.startActivity(new Intent(context, LoginActivity.class));

            }
        }).setNegativeButton(values[3], new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        AlertDialog alertDialog=builder.create();
        alertDialog.show();
    }
    public static void showAlertToEnableLocation(final Context context,
                                       String... values)
    {
        AlertDialog.Builder builder=new AlertDialog.Builder(context).
                setTitle(values[0]).
                setMessage(values[1]).setPositiveButton(values[2], new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

               Intent intent=new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);

            }
        }).setNegativeButton(values[3], new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        AlertDialog alertDialog=builder.create();
        alertDialog.show();
    }
}
