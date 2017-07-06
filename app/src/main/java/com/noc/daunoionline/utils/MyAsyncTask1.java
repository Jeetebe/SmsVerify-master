package com.noc.daunoionline.utils;


import android.content.Context;
import android.os.AsyncTask;

public class MyAsyncTask1 extends AsyncTask<String, Void, String> {

    public MyAsyncTask1(Context context) {

    }
    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        //Log.i("MyActivity","begin load data: tophit");
      
    }
    @Override
    protected String doInBackground(String... strings) {

    	      
        return null;
    }

    @Override
    protected void onPostExecute(String temp) {

    }
}
