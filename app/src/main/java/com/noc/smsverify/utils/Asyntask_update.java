package com.noc.smsverify.utils;


        import android.app.ProgressDialog;
        import android.content.Context;
        import android.content.Intent;
        import android.net.Uri;
        import android.os.AsyncTask;
        import android.os.Handler;
        import android.util.Log;
        import android.widget.ImageView;
        import android.widget.TextView;

        import com.noc.smsverify.app.Config;

        import java.io.BufferedInputStream;
        import java.io.File;
        import java.io.FileOutputStream;
        import java.io.InputStream;
        import java.io.OutputStream;
        import java.net.URL;
        import java.net.URLConnection;



public class Asyntask_update extends AsyncTask<String, Void, String> {
    Context context;

    String path ="";
    ProgressDialog progressDialog;
    private int progressStatus = 0;
    private Handler handler = new Handler();
    public Asyntask_update(Context context, int version) {
        this.context=context;
        path = "/sdcard/mobiquiz_v"+Integer.toString(version)+".apk";
    }
    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        progressDialog = new ProgressDialog(context);
        progressDialog.setTitle("Please Wait..");
        progressDialog.setMessage("Downloading File ...");
        progressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
        progressDialog.setCancelable(false);
        progressDialog.setMax(100);
        progressDialog.show();
    }
    @Override
    protected String doInBackground(String... strings) {
        try {
            URL url = new URL(Config.URL_appUpdate);
            URLConnection connection = url.openConnection();
            connection.connect();

            int fileLength = connection.getContentLength();

            // download the file
            InputStream input = new BufferedInputStream(url.openStream());
            OutputStream output = new FileOutputStream(path);
           Json.logi("path"+path);
            byte data[] = new byte[1024];
            long total = 0;
            int count;
            while ((count = input.read(data)) != -1) {
                total += count;
                //publishProgress((int) (total * 100 / fileLength));
                progressStatus=(int) (total * 100 / fileLength);
                progressDialog.setProgress(progressStatus);
                Log.d("MyActivity", "process:"+ (int) (total * 100 / fileLength));
                output.write(data, 0, count);
            }

            output.flush();
            output.close();
            input.close();
        } catch (Exception e) {
            Log.e("MyActivity", "Well that didn't work out so well...");
            Log.e("MyActivity", e.getMessage());
        }

        return path;
    }

    @Override
    protected void onPostExecute(String temp) {
        progressDialog.dismiss();
        Intent i = new Intent();
        i.setAction(Intent.ACTION_VIEW);
        i.setDataAndType(Uri.fromFile(new File(path)), "application/vnd.android.package-archive" );
        Log.d("MyActivity", "About to install new .apk");
        this.context.startActivity(i);
    }

}
