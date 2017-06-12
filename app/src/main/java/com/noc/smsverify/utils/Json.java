package com.noc.smsverify.utils;

import android.os.StrictMode;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.google.gson.Gson;
import com.noc.smsverify.app.Config;
import com.noc.smsverify.app.MyApplication;
import com.noc.smsverify.object.AppPara;
import com.noc.smsverify.object.LogObj;
import com.noc.smsverify.object.ThongtinObj;
import com.noc.smsverify.object.TinhObj;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class Json {
    Gson gson=new Gson();

    public AppPara get_Apppara(){
        String device="";
        JSONArray alarm = new JSONArray();
        AppPara app=new AppPara();
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        try {

            JSONfunctions jParser = new JSONfunctions();
            //Log.i("MyActivity", Constants.urlezinfor);
            String url=Config.url_apppara+device;
            //alarm = jParser.getJSONfromURL(Const.apppara_v5+"funring");
            alarm = jParser.getJSONfromURL(url);
        } catch (Exception e) {
            //e.printStackTrace();
        }
        try  {
            JSONObject c = alarm.getJSONObject(0);
            app.set_secure(c.getString("secure"));
            app.set_version(c.getString("version"));
            app.set_para1(c.getString("para1"));
            app.set_para2(c.getString("para2"));
            app.set_para3(c.getString("para3"));
            app.set_para4(c.getString("para4"));

        } catch (JSONException e) {
            //e.printStackTrace();
        }
        return app;
    }

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
    public ArrayList<String> searchkhoso(String searchstr){
        JSONArray alarm = new JSONArray();
        ArrayList<String> studentArray1 = new ArrayList<String>();
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        String fullUrl=Config.URL_SEARCHKHOSO+searchstr;

        try {
            studentArray1.clear();
            try {
                JSONfunctions jParser = new JSONfunctions();
                alarm = jParser.getJSONfromURL(fullUrl);
                logi("url:"+fullUrl);
            } catch (Exception e) {
                Json.logi("loi"+e.toString());
            }
            Json.logi("alarm"+alarm.length());
            for(int i = 0; i < alarm.length(); i++){
                JSONObject c = alarm.getJSONObject(i);
                String isdn=c.getString("isdn");
                studentArray1.add(isdn);
            }
        } catch (JSONException e) {
            logi("loi:"+ e.toString());
        }
        return studentArray1;
    }

    public ArrayList<LogObj> get_Logs(String ez){
        JSONArray alarm = new JSONArray();
        ArrayList<LogObj> studentArray1 = new ArrayList<LogObj>();
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        String fullUrl=Config.URL_GET_LOGS+ez;

        try {
            studentArray1.clear();
            try {
                JSONfunctions jParser = new JSONfunctions();
                alarm = jParser.getJSONfromURL(fullUrl);
                logi("url:"+fullUrl);
            } catch (Exception e) {
                Json.logi("loi"+e.toString());
            }
            Json.logi("alarm"+alarm.length());
            for(int i = 0; i < alarm.length(); i++){
                JSONObject c = alarm.getJSONObject(i);
                LogObj obj=new LogObj();
                obj.setEz(ez);
                obj.setIsdn(c.getString("isdn"));
                obj.setLoai(c.getString("loai"));
                obj.setNgay(c.getString("ngay"));
                obj.setStatus(c.getString("status"));
                obj.setGhichu(c.getString("ghichu"));
                studentArray1.add(obj);
            }
        } catch (JSONException e) {
            logi("loi:"+ e.toString());
        }
        return studentArray1;
    }
    /////////////////////////////////////////////////////////////////////////post

//    public String POST_getcode(String isdn){
//        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
//        StrictMode.setThreadPolicy(policy);
//        logi("POST_getcode");
//        String url= Config.URL_REQUEST_SMS;
//        InputStream inputStream = null;
//        String result = "";
//        try {
//            HttpClient httpclient = new DefaultHttpClient();
//            HttpPost httpPost = new HttpPost(url);
//            String json = "";
//            json=gson.toJson(isdn);
//            StringEntity se = new StringEntity(json);
//            // 6. set httpPost Entity
//            httpPost.setEntity(se);
//            // 7. Set some headers to inform server about the type of the content
//            httpPost.setHeader("Accept", "application/json");
//            httpPost.setHeader("Content-type", "application/json;charset=utf8");
//            // 8. Execute POST request to the given URL
//            HttpResponse httpResponse = httpclient.execute(httpPost);
//            // 9. receive response as inputStream
//            inputStream = httpResponse.getEntity().getContent();
//            // 10. convert inputstream to string
//            if(inputStream != null)
//                result = convertInputStreamToString(inputStream);
//            else
//                result = "Did not work!";
//
//        } catch (Exception e) {
//            //Log.d("InputStream", e.getLocalizedMessage());
//            logi("loi:"+e.toString());
//        }
//        logi("nhan dc userId:"+ result);
//        return result;
//    }

    public Boolean POST_checktbdangky(String isdn){
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        logi("POST_checktbdangky");
        Boolean b=false;
        String url= Config.URL_CheckTBdangky;
        InputStream inputStream = null;
        String result = "";
        try {
            HttpClient httpclient = new DefaultHttpClient();
            HttpPost httpPost = new HttpPost(url);
            String json = "";
            //json=gson.toJson(isdn);
            json="{\"isdn\":\""+isdn+"\"}";
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
            if (result.contains("dangky"))
            {
                b=true;
            }

        } catch (Exception e) {
            //Log.d("InputStream", e.getLocalizedMessage());
            logi("loi:"+e.toString());
        }
        logi("nhan dc userId:"+ result);
        return b;
    }
    public Boolean POST_checkezlogtin(String isdn){
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        logi("POST_checkezlogtin");
        Boolean b=false;
        String url= Config.URL_CheckEZlogin;
        InputStream inputStream = null;
        String result = "";
        try {
            HttpClient httpclient = new DefaultHttpClient();
            HttpPost httpPost = new HttpPost(url);
            String json = "";
            //json=gson.toJson(isdn);
            json="{\"isdn\":\""+isdn+"\"}";
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
            if (result.contains("200"))
            {
                b=true;
            }

        } catch (Exception e) {
            //Log.d("InputStream", e.getLocalizedMessage());
            logi("loi:"+e.toString());
        }
        logi("nhan dc code:"+ result);
        return b;
    }
    public String POST_Thongtinn(ThongtinObj thongtinObj){

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

        return result;
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
