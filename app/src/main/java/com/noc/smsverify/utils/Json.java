package com.noc.smsverify.utils;

import android.os.StrictMode;
import android.util.Log;

import com.google.gson.Gson;
import com.noc.smsverify.app.Config;
import com.noc.smsverify.object.ThongtinObj;
import com.noc.smsverify.object.TinhObj;

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

    public ArrayList<TinhObj> get_danhmuc(int loai, String id){
        JSONArray alarm = new JSONArray();
        ArrayList<TinhObj> studentArray1 = new ArrayList<TinhObj>();
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        String fullUrl="";
        switch (loai)
        {
            case 1://tinh
                fullUrl=Config.URL_GET_Danhmuc+"getdmtinh?loai=1&id=1";
                break;
            case 2://huyen
                fullUrl=Config.URL_GET_Danhmuc+"getdmtinh?loai=2&id="+id;
                break;
            case 3://xa
                fullUrl=Config.URL_GET_Danhmuc+"getdmtinh?loai=3&id="+id;
                break;
        }

        try {
            studentArray1.clear();
            try {
                JSONfunctions jParser = new JSONfunctions();
                alarm = jParser.getJSONfromURL(fullUrl);
                logi("url:"+fullUrl);
            } catch (Exception e) {}
            for(int i = 0; i < alarm.length(); i++){
                JSONObject c = alarm.getJSONObject(i);
                TinhObj tinhObj=new TinhObj();
                tinhObj.setName(c.getString("name"));
                tinhObj.setId(c.getString("id"));
                studentArray1.add(tinhObj);
            }
        } catch (JSONException e) {
            logi("loi:"+ e.toString());
        }
        return studentArray1;
    }
    public ArrayList<TinhObj> get_doituong(){
        JSONArray alarm = new JSONArray();
        ArrayList<TinhObj> studentArray1 = new ArrayList<TinhObj>();
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        String fullUrl=Config.URL_GET_Doituong;

        try {
            studentArray1.clear();
            try {
                JSONfunctions jParser = new JSONfunctions();
                alarm = jParser.getJSONfromURL(fullUrl);
                logi("url:"+fullUrl);
            } catch (Exception e) {}
            for(int i = 0; i < alarm.length(); i++){
                JSONObject c = alarm.getJSONObject(i);
                TinhObj tinhObj=new TinhObj();
                tinhObj.setName(c.getString("name"));
                tinhObj.setId(c.getString("id"));
                studentArray1.add(tinhObj);
            }
        } catch (JSONException e) {
            logi("loi:"+ e.toString());
        }
        return studentArray1;
    }

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
    public boolean POST_Thongtinn(ThongtinObj thongtinObj){

        String url=Config.URL_POST_THONGTIN;
        logi("POST_Thongtinn:"+Config.URL_POST_THONGTIN);
        InputStream inputStream = null;
        String result = "";
        try {
            HttpClient httpclient = new DefaultHttpClient();
            HttpPost httpPost = new HttpPost(url);
            String json = "";
            json=gson.toJson(thongtinObj);
            StringEntity se = new StringEntity(json);
            httpPost.setEntity(se);
            httpPost.setHeader("Accept", "application/json");
            httpPost.setHeader("Content-type", "application/json;charset=utf8");
            HttpResponse httpResponse = httpclient.execute(httpPost);
            inputStream = httpResponse.getEntity().getContent();
            if(inputStream != null)
                result = convertInputStreamToString(inputStream);
            else
                result = "Did not work!";

        } catch (Exception e) {
            Log.d("InputStream", e.getLocalizedMessage());
        }
        logi("nhan dc result:"+ result);
        boolean b=false;
        if (result.contains("200"))
        {
            b=true;
        }

        return b;
    }



    public static void logi(String str){
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
