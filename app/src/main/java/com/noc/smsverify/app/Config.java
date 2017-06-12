package com.noc.smsverify.app;

/*
 * Created by defoliate on 14-10-2015.
 */
public class Config
{
    public static final int currversion=1;
    public static final String appname= "DKTTTB_V1";

    public static final String SERVER_IP = "http://10.151.124.81:8080";
    public static final String urlpostlog2server=SERVER_IP+ "/FileUploaderRESTService/rest/dktttb/postlog2server?log=" ;
    public static final String URL_REQUEST_SMS = SERVER_IP+"/FileUploaderRESTService/rest/dktttb/getcode";
    public static final String URL_VERIFY_OTP = SERVER_IP+"/FileUploaderRESTService/rest/dktttb/checkcode";

    public static final String URL_POST_THONGTIN = SERVER_IP+"/FileUploaderRESTService/rest/dktttb/postthongtin";
    public static final String FILE_UPLOAD_URL = SERVER_IP+"/FileUploaderRESTService/rest/upload";

    public static final String URL_GET_Danhmuc = SERVER_IP+"/FileUploaderRESTService/rest/dktttb/";
    public static final String URL_GET_Doituong = SERVER_IP+"/FileUploaderRESTService/rest/dktttb/getdoituong";
    public static final String URL_SEARCHKHOSO = SERVER_IP+"/FileUploaderRESTService/rest/dktttb/searchkhoso?searchstr=";
    public static final String URL_GET_LOGS = SERVER_IP+"/FileUploaderRESTService/rest/dktttb/getlogs?ez=";

    public static final String URL_CheckTBdangky = SERVER_IP+"/FileUploaderRESTService/rest/dktttb/checktbdangky";
    public static final String URL_CheckEZlogin = SERVER_IP+"/FileUploaderRESTService/rest/dktttb/checkezlogin";

    public static final String SMS_ORIGIN = "ANHIVE";

    // special character to prefix the otp. Make sure this character appears only once in the sms
    public static final String OTP_DELIMITER = ":";



    public static final String URLIP="http://123.30.100.126:8081";
    public static final String URL_appUpdate = URLIP+"/Restapi/dktttb.apk";
    //public static final String urlpostlog2server=URLIP+ "/Restapi/rest/GeneralService/postlog2server?logstr=" ;
    public static final String url_apppara=URLIP+ "/Restapi/rest/GeneralService/getparaapp/dktttb?device=";
}
