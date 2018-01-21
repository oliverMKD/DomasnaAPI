package com.oliver.domasnaapi.other;

import android.content.Context;
import android.content.DialogInterface;

/**
 * Created by Oliver on 1/21/2018.
 */

public class GlobalFunctions {
    public static void showAlertDialogWithOneButton(Context context, String title, String description, DialogInterface.OnClickListener onClickListener) {
        final android.support.v7.app.AlertDialog.Builder builder1 = new android.support.v7.app.AlertDialog.Builder(context);
        builder1.setTitle(title);
        builder1.setMessage(description);
        builder1.setNeutralButton("OK", onClickListener);
        builder1.setCancelable(true);
        builder1.show();
    }


    public static String getUrl(String url) {
        String[] temp = url.split("/");

        String final1 = "";

        for (int i = 4; i < temp.length; i++) {
            final1 = final1 + temp[i] + "/";
        }

        return final1;
    }
}
