package com.noc.smsverify.utils;

import android.os.StrictMode;
import android.util.Log;

import com.google.gson.Gson;
import com.noc.smsverify.app.Config;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;


public class Json {
    Gson gson=new Gson();

//    public ArrayList<ChudeObj> get_chude(){
//        JSONArray alarm = new JSONArray();
//        ArrayList<ChudeObj> studentArray1 = new ArrayList<ChudeObj>();
//        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
//        StrictMode.setThreadPolicy(policy);
//        try {
//            studentArray1.clear();
//            try {
//                JSONfunctions jParser = new JSONfunctions();
//                String fullUrl=Const.url_getchude;
//                alarm = jParser.getJSONfromURL(fullUrl);
//            } catch (Exception e) {}
//            for(int i = 0; i < alarm.length(); i++){
//                JSONObject c = alarm.getJSONObject(i);
//                ChudeObj chudeobj=new ChudeObj();
//                //logi("chude:"+c.getString("chude"));
//                int chudeid=c.getInt("chudeId");   chudeobj.setChudeId(chudeid);
//                String chude = c.getString("chude"); chudeobj.setChude(chude);
//                String ico=c.getString("ico"); chudeobj.setIco(ico);
//                studentArray1.add(chudeobj);
//            }
//        } catch (JSONException e) {
//            logi("loi:"+ e.toString());
//        }
//        return studentArray1;
//    }


    /////////////////////////////////////////////////////////////////////////post

    public String POST_getcode(String isdn){
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        logi("POST_getcode");
        String url= Config.URL_REQUEST_SMS;
        InputStream inputStream = null;
        String result = "";
        try {
            HttpClient httpclient = new DefaultHttpClient();
            HttpPost httpPost = new HttpPost(url);
            String json = "";
            json=gson.toJson(isdn);
            StringEntity se = new StringEntity(json);
            // 6. set httpPost Entity
            httpPost.setEntity(se);
            // 7. Set some headers to inform server about the type of the content
            httpPost.setHeader("Accept", "application/json");
            httpPost.setHeader("Content-type", "application/json;charset=utf8");
            // 8. Execute POST request to the given URL
            HttpResponse httpResponse = httpclient.execute(httpPost);
            // 9. receive response as inputStream
            inputStream = httpResponse.getEntity().getContent();
            // 10. convert inputstream to string
            if(inputStream != null)
                result = convertInputStreamToString(inputStream);
            else
                result = "Did not work!";

        } catch (Exception e) {
            //Log.d("InputStream", e.getLocalizedMessage());
            logi("loi:"+e.toString());
        }
        logi("nhan dc userId:"+ result);
        return result;
    }


    public void logi(String str){
        Log.i("MyActivity:",str);
    }
    private static String convertInputStreamToString(InputStream inputStream) throws IOException {
        BufferedReader bufferedReader = new BufferedReader( new InputStreamReader(inputStream));
        String line = "";
        String result = "";
        while((line = bufferedReader.readLine()) != null)
            result += line;
        inputStream.close();
        return result;

    }
}
