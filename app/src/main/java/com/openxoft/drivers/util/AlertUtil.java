package com.openxoft.drivers.util;

import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;

/**
 * Created by openxoft on 19/05/17.
 */

public class AlertUtil {

    public static void showAlertDailog(Context context,
                                       String... values)
    {
        AlertDialog.Builder builder=new AlertDialog.Builder(context).
                setTitle(values[0]).
                setMessage(values[1]).setPositiveButton(values[2], new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        AlertDialog alertDialog=builder.create();
        alertDialog.show();
    }
}
