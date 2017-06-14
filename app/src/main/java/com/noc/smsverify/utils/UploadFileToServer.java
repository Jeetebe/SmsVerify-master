package com.noc.smsverify.utils;

import android.os.AsyncTask;

import com.noc.smsverify.app.Config;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.entity.mime.content.StringBody;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

import java.io.File;
import java.io.IOException;

/**
 * Uploading the file to server
 * */
public class UploadFileToServer extends AsyncTask<Void, Integer, String> {
    long totalSize = 0;
    private String filePath = null;
    private String isdn=null;
    private String loai=null;
    private String currdate=null;

    public UploadFileToServer( String filePath) {
        this.filePath = filePath;
    }

    public UploadFileToServer(String filePath, String isdn, String loai, String currdate ) {
        this.filePath = filePath;
        this.isdn = isdn;
        this.loai = loai;
        this.currdate=currdate;
    }

    @Override
    protected void onPreExecute() {
        // setting progress bar to zero
        //progressBar.setProgress(0);
        super.onPreExecute();
    }

    @Override
    protected void onProgressUpdate(Integer... progress) {
        // Making progress bar visible
        //progressBar.setVisibility(View.VISIBLE);

        // updating progress bar value
        //progressBar.setProgress(progress[0]);

        // updating percentage value
        //txtPercentage.setText(String.valueOf(progress[0]) + "%");
    }

    @Override
    protected String doInBackground(Void... params) {
        return uploadFile();
    }

    @SuppressWarnings("deprecation")
    private String uploadFile() {
        String responseString = null;

        Json.logi("UploadFileToServer:"+ filePath);


        HttpClient httpclient = new DefaultHttpClient();
        HttpPost httppost = new HttpPost(Config.FILE_UPLOAD_URL);

        try {
            AndroidMultiPartEntity entity = new AndroidMultiPartEntity(
                    new AndroidMultiPartEntity.ProgressListener() {

                        @Override
                        public void transferred(long num) {
                            publishProgress((int) ((num / (float) totalSize) * 100));
                        }
                    });

            File sourceFile = new File(filePath);

            // Adding file data to http body
            entity.addPart("file", new FileBody(sourceFile));
            entity.addPart("isdn",
                    new StringBody(isdn));
            entity.addPart("loai", new StringBody(loai));
            entity.addPart("currdate", new StringBody(currdate));

            totalSize = entity.getContentLength();
            httppost.setEntity(entity);

            // Making server call
            HttpResponse response = httpclient.execute(httppost);
            HttpEntity r_entity = response.getEntity();

            int statusCode = response.getStatusLine().getStatusCode();
            if (statusCode == 200) {
                // Server response
                responseString = EntityUtils.toString(r_entity);
            } else {
                responseString = "Error occurred! Http Status Code: "
                        + statusCode;
            }

        } catch (ClientProtocolException e) {
            responseString = e.toString();
        } catch (IOException e) {
            responseString = e.toString();
        }

        return responseString;

    }

    @Override
    protected void onPostExecute(String result) {
        //Log.e(TAG, "Response from server: " + result);
        Json.logi("Response from server: " + result);
        // showing the server response in an alert dialog
        //showAlert(result);

        super.onPostExecute(result);
    }

}