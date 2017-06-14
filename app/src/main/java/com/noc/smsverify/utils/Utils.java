package com.noc.smsverify.utils;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.StrictMode;
import android.provider.Settings;
import android.telephony.TelephonyManager;

import com.noc.smsverify.app.Config;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by dung on 6/8/2017.
 */

public class Utils {
static Context context;

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
    public void thongbaoexit(String title,String noidung){
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle(title);
        builder.setMessage(noidung)
                .setCancelable(false)
                .setPositiveButton("OK",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog,
                                                int id) {
                                Activity activity=(Activity) context;
                                activity.finish();
                            }
                        });
        AlertDialog alert = builder.create();
        alert.show();

    }
    public static String get_deviceinfor(){
        String android_id = Settings.Secure.getString(context.getContentResolver(),
                Settings.Secure.ANDROID_ID);
        return android_id;
    }
    public static String get_ismi(){
        String subscriberid="";
        //Log.i("MyActivity","begin imsi:"+subscriberid);
        try
        {
            TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
            subscriberid = telephonyManager.getSubscriberId();
        }
        catch (Exception e){}
        return subscriberid;
    }
    public static boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager = (ConnectivityManager) context
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager
                .getActiveNetworkInfo();
        return activeNetworkInfo != null;
    }
    public void log2server(String logstr,String ez){
        if (android.os.Build.VERSION.SDK_INT > 9) {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }
        try {
            HttpClient httpclient = new DefaultHttpClient();
            //String str1=Constants.urllog+"?imsi="+imsi+"&action="+action+"&smsdata="+smsdata+"&cell="+cell;
            String str1= Config.urlpostlog2server+logstr+"&ez="+ez;
            //Log.i("MyActivity", str1);
            HttpPost httppost = new HttpPost(str1);

            List<NameValuePair> namevaluepairs = new ArrayList<NameValuePair>(2);
            namevaluepairs.add((NameValuePair) new BasicNameValuePair("imsi","123"));

            httppost.setEntity(new UrlEncodedFormEntity(namevaluepairs));
            HttpResponse response = httpclient.execute(httppost);
        } catch (ClientProtocolException e) {
            // TODO Auto-generated catch block
        } catch (IOException e) {
            // TODO Auto-generated catch block
        }

    }

    public static String getCurrentTimeStamp(){
        try {

            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmm");
            String currentDateTime = dateFormat.format(new Date()); // Find todays date

            return currentDateTime;
        } catch (Exception e) {
            e.printStackTrace();

            return null;
        }
    }
}
