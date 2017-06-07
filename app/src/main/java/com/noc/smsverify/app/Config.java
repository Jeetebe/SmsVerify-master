package com.noc.smsverify.app;

/*
 * Created by defoliate on 14-10-2015.
 */
public class Config
{
    // server URL configuration
    //public static final String URL_REQUEST_SMS = "http://192.168.0.107:8000/regis/phone/";
    public static final String URL_REQUEST_SMS = "http://10.151.124.72:8086/FileUploaderRESTService/rest/dktttb/getcode";
    public static final String URL_VERIFY_OTP = "http://10.151.124.72:8086/FileUploaderRESTService/rest/dktttb/checkcode";
    public static final String URL_SUBMIT_CRED = "http://192.168.0.107:8000/register/credentials/";


    public static final String URL_GET_Danhmuc = "http://10.151.124.72:8086/FileUploaderRESTService/rest/dktttb/";
    public static final String URL_GET_Doituong = "http://10.151.124.72:8086/FileUploaderRESTService/rest/dktttb/getdoituong";

    public static final String SMS_ORIGIN = "ANHIVE";

    // special character to prefix the otp. Make sure this character appears only once in the sms
    public static final String OTP_DELIMITER = ":";
}
