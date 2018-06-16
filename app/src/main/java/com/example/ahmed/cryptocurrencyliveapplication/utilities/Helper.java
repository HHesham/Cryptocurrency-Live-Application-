package com.example.ahmed.cryptocurrencyliveapplication.utilities;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

import com.example.ahmed.cryptocurrencyliveapplication.R;

import java.text.DecimalFormat;
import java.text.NumberFormat;

public class Helper {

    public static boolean isNetworkAvailable(Context context) {
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }

    public static String formateDouble(double val){
        NumberFormat formatter = new DecimalFormat("#0.00000");
        return formatter.format(val);
    }

    public static void hideKeyboard(Activity activity){
        View view = activity.getCurrentFocus();
        if (view != null) {
            InputMethodManager imm = (InputMethodManager)activity.getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }


    public static ProgressDialog getProgressDialog(Context mContext){
        ProgressDialog myProgressDialog = new ProgressDialog(((AppCompatActivity) mContext), ProgressDialog.THEME_HOLO_DARK);
        myProgressDialog.setMessage(mContext.getResources().getString(R.string.loading));
        myProgressDialog.setCanceledOnTouchOutside(false);
        myProgressDialog.setCancelable(true);
        return myProgressDialog;
    }
}
