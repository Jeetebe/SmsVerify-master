package com.noc.smsverify.utils;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;

/**
 * Created by dung on 6/8/2017.
 */

public class Utils {
Context context;

    public Utils(Context context) {
        this.context = context;
    }

    public void thongbao(String title,String noidung){
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle(title);
        builder.setMessage(noidung)
                .setCancelable(false)
                .setPositiveButton("OK",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog,
                                                int id) {

                            }
                        });
        AlertDialog alert = builder.create();
        alert.show();

    }
}
