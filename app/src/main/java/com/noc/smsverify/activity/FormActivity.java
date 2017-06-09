package com.noc.smsverify.activity;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.noc.smsverify.R;
import com.noc.smsverify.app.Config;
import com.noc.smsverify.object.CollimgObj;
import com.noc.smsverify.object.ThongtinObj;
import com.noc.smsverify.object.TinhObj;
import com.noc.smsverify.utils.AndroidMultiPartEntity;
import com.noc.smsverify.utils.Json;
import com.noc.smsverify.utils.TinyDB;
import com.noc.smsverify.utils.UploadFileToServer;
import com.pratap.calendarview.views.DatePickerView;
import com.scalified.fab.ActionButton;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.entity.mime.content.StringBody;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.w3c.dom.Text;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;

public class FormActivity extends AppCompatActivity  implements
        AdapterView.OnItemSelectedListener {

    Json json;
    Context context;
    TinyDB tinydb;

    ThongtinObj thongtinobj;
    String isdn="939170707";

    TextView txtisdn;
    EditText eSim, eHoten,eCmnd,esdtlienhe,eMail,eDiachi,eShopcode,eMaNV;

    ImageView listshopcode,listmannv;

    private ImageView btn_datengaysinh, btn_datengaycap;
    DatePickerView txtdatengaysinh, txtdatengaycap;
    Spinner spinnertinh,spinnerhuyen,spinnerxa,spinnerdoituong, spinnernoicap;
    Switch swgioitinh;
    TextView txtgioitinh;
    ImageView imgAddimg,imgstatus1;
    ImageView imgSign,imgstatus2;
    private ActionButton fbutton;

    ArrayList<TinhObj> dmtinh=new ArrayList<TinhObj>();
    ArrayList<TinhObj> dmhuyen=new ArrayList<TinhObj>();
    ArrayList<TinhObj> dmxa=new ArrayList<TinhObj>();

    ArrayList<TinhObj> dmdoituong=new ArrayList<TinhObj>();

    List listtinh, listhuyen,listxa, listdoituong;
    //List<Map.Entry<String, String>> listimg = new ArrayList<Map.Entry<String, String>>();
    //Map<String,String> listimg = new HashMap<String, String>();
    CollimgObj collImage;

    ArrayList<String> list_shopcode = new ArrayList<String>() ;
    ArrayList<String> list_manv = new ArrayList<String>() ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form);
        json=new Json();
        context=this;
        tinydb=new TinyDB(this);

        list_shopcode=tinydb.getListString("shopcode");
        list_manv=tinydb.getListString("manv");

        thongtinobj=new ThongtinObj("test");
        collImage=new CollimgObj();

        dmtinh=json.get_danhmuc(1,"");
        listtinh = new ArrayList();
        for (TinhObj tinh: dmtinh
             ) {
            listtinh.add(tinh.getName());
            //Json.logi(tinh.getName());
        }
        dmdoituong=json.get_doituong();
        listdoituong = new ArrayList();
        for (TinhObj tinh: dmdoituong
                ) {
            listdoituong.add(tinh.getName());
            //Json.logi(tinh.getName());
        }
//EditText eSim, eHoten,eCmnd,esdtlienhe,eMail,eDiachi;
        txtisdn=(TextView) findViewById(R.id.txtisdn);
        eSim=(EditText) findViewById(R.id.txtserialsim);
        eHoten=(EditText) findViewById(R.id.txthoten);
        eCmnd=(EditText) findViewById(R.id.txtcmnd);
        esdtlienhe=(EditText) findViewById(R.id.txtsdtlienhe);
        eMail=(EditText) findViewById(R.id.txtemail);
        eDiachi=(EditText) findViewById(R.id.txtdiachi);
        eShopcode=(EditText) findViewById(R.id.txtshopcode);
        eMaNV=(EditText) findViewById(R.id.txtmanv);

        spinnertinh = (Spinner) findViewById(R.id.spinnertinh);
        spinnerhuyen = (Spinner) findViewById(R.id.spinnerhuyen);
        spinnerxa = (Spinner) findViewById(R.id.spinnerxa);
        spinnerdoituong = (Spinner) findViewById(R.id.spinnerdoituong);
        spinnernoicap=(Spinner) findViewById(R.id.spinnernoicap);
        swgioitinh=(Switch) findViewById(R.id.swgioitinh) ; final Boolean bnam=true;
        txtgioitinh= (TextView) findViewById(R.id.txtgioitinh) ;

        txtdatengaysinh= (DatePickerView) findViewById(R.id.txtdatengaysinh) ;
        txtdatengaycap= (DatePickerView) findViewById(R.id.txtdatengaycap) ;

        imgAddimg=(ImageView) findViewById(R.id.imgAddimg) ;
        imgstatus1=(ImageView) findViewById(R.id.imgstatus1) ;
        imgSign=(ImageView) findViewById(R.id.imgSign) ;
        imgstatus2=(ImageView) findViewById(R.id.imgstatus2) ;

        listmannv=(ImageView) findViewById(R.id.listmannv);
        listshopcode=(ImageView) findViewById(R.id.listshopcode);

        txtdatengaysinh.setHint("Ngày sinh*");
        txtdatengaycap.setHint("Ngày cấp*");

        ArrayAdapter adaptnoicap = new ArrayAdapter(this,android.R.layout.simple_spinner_item,listtinh);
        spinnernoicap.setAdapter(adaptnoicap);

        ArrayAdapter adapdoituong = new ArrayAdapter(this,android.R.layout.simple_spinner_item,listdoituong);
        spinnerdoituong.setAdapter(adapdoituong);
        spinnerdoituong.setOnItemSelectedListener(this);

        ArrayAdapter adapttinh = new ArrayAdapter(this,android.R.layout.simple_spinner_item,listtinh);
        spinnertinh.setAdapter(adapttinh);
        spinnertinh.setOnItemSelectedListener(this);
        spinnerhuyen.setOnItemSelectedListener(this);

        swgioitinh.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked)
                {
                    txtgioitinh.setText("Giới tính: Nữ");
                }
                else
                {
                    txtgioitinh.setText("Giới tính: Nam");
                }
            }
        });
        imgAddimg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                Intent intent = new Intent(context, ImgPickerActivity.class);
                Activity activity = (Activity) context;
                activity.startActivityForResult(intent, 1);
                activity.overridePendingTransition(R.anim.slide_in_bottom, R.anim.null_animation);
            }
        });
        imgSign.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                Intent intent = new Intent(context, SignatureActivity.class);
                Activity activity = (Activity) context;
                activity.startActivityForResult(intent, 2);
                activity.overridePendingTransition(R.anim.slide_in_bottom, R.anim.null_animation);
            }
        });

        fbutton=(ActionButton) findViewById(R.id.action_button_submit);
        fbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                thongtinobj.setpEZ("123");
//                thongtinobj.setPhoten("Duy");
//                thongtinobj.setPisdn("939170707");
                Collect_info_test();
                json.POST_Thongtinn(thongtinobj);
                //My_UploadImg2server();

                list_shopcode.add(eShopcode.getText().toString());
                list_manv.add(eMaNV.getText().toString());

                tinydb.putListString("shopcode",list_shopcode);
                tinydb.putListString("manv",list_manv);
                Json.logi("save shop code to db");
            }
        });
        listshopcode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                final CharSequence[] cs = list_shopcode.toArray(new CharSequence[list_shopcode.size()]);
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setTitle("Chọn Shop code");
                //builder.setItems(colors, new DialogInterface.OnClickListener() {
                builder.setItems(cs, new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // the user clicked on colors[which]
                        Json.logi("click:"+cs[which].toString());
                        eShopcode.setText(cs[which].toString());
                    }
                });
                builder.show();
            }
        });
        listmannv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                final CharSequence[] cs = list_manv.toArray(new CharSequence[list_manv.size()]);
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setTitle("Chọn mã nhân viên ");
                //builder.setItems(colors, new DialogInterface.OnClickListener() {
                builder.setItems(cs, new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // the user clicked on colors[which]
                        Json.logi("click:"+cs[which].toString());
                        eMaNV.setText(cs[which].toString());
                    }
                });
                builder.show();

            }

        });
}

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int position, long id) {
        Spinner spinner = (Spinner) adapterView;
        if(spinner.getId() == R.id.spinnertinh)
        {
            //do this
            TinhObj tinhobj = (TinhObj) dmtinh.get(position);
            //Toast.makeText(getApplicationContext(), tinhobj.getName(), Toast.LENGTH_LONG).show();
            Json.logi("tinh:" + tinhobj.getName());
            dmhuyen.clear();
            dmhuyen = json.get_danhmuc(2, tinhobj.getId());
            listhuyen = new ArrayList();
            for (TinhObj tinh : dmhuyen
                    ) {
                listhuyen.add(tinh.getName());
                //Json.logi(tinh.getName());
            }
            ArrayAdapter adapthuyen = new ArrayAdapter(this, android.R.layout.simple_spinner_item, listhuyen);
            spinnerhuyen.setAdapter(adapthuyen);
        }
        else if(spinner.getId() == R.id.spinnerhuyen)
        {
            //do Huyen
            TinhObj tinhobj = (TinhObj) dmhuyen.get(position);
           // Toast.makeText(getApplicationContext(), tinhobj.getName(), Toast.LENGTH_LONG).show();
            Json.logi("huyen:" + tinhobj.getName());
            dmxa.clear();
            dmxa = json.get_danhmuc(3, tinhobj.getId());
            listxa = new ArrayList();
            for (TinhObj tinh : dmxa
                    ) {
                listxa.add(tinh.getName());
                //Json.logi(tinh.getName());
            }
            ArrayAdapter adaptxa = new ArrayAdapter(this, android.R.layout.simple_spinner_item, listxa);
            spinnerxa.setAdapter(adaptxa);
        }

    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        overridePendingTransition(R.anim.null_animation, R.anim.slide_out_bottom);
        Json.logi("nhan dc requestCode:"+requestCode);
        Json.logi("nhan dc resultCode:"+resultCode);
        if (requestCode==1)// chon image
        {
            if (resultCode == Activity.RESULT_OK) {
//                //ArrayList<String> myList = (ArrayList<String>) getIntent().getSerializableExtra("mylist");
//                //ArrayList<String> myList = (ArrayList<String>) data.getSerializableExtra("mylist");
//                Map<String,String> listimg = (Map<String,String>) data.getSerializableExtra("mylist");
//                Json.logi("nhan dc mylist size:" + listimg.size());
                CollimgObj collimgObj=(CollimgObj) data.getSerializableExtra("mylist");
                Json.logi("nhan dc mattruoc:" + collimgObj.getImgtruoc());
                    collImage.setImgtruoc(collimgObj.getImgtruoc());
                    collImage.setImgsau(collimgObj.getImgsau());
                    collImage.setImgphieu(collimgObj.getImgphieu());
                    collImage.setImganhnguoi(collimgObj.getImganhnguoi());
                imgstatus1.setImageResource(R.drawable.ic_check_circle_light_green_800_24dp);
//                if (listimg.size() > 0) {
//                    imgstatus1.setImageResource(R.drawable.ic_check_circle_light_green_800_24dp);
//                }
//                else {
//                    imgstatus1.setImageResource(R.drawable.ic_report_problem_red_400_24dp);
//                }
            }
        }
        else if (requestCode==2)  //signature
            {
                if (resultCode == Activity.RESULT_OK) {
                    String signpath = data.getStringExtra("signpath");
                    Json.logi("nhan dc signpath:" + signpath);
                    if (signpath.length()>0)
                    {
                        imgstatus2.setImageResource(R.drawable.ic_check_circle_light_green_800_24dp);
                        collImage.setImgchuky(signpath);
                    }
                    else
                    {
                        imgstatus2.setImageResource(R.drawable.ic_report_problem_red_400_24dp);
                    }
                }
            }
        }

    private void Collect_info_test() {
        String doituong=dmdoituong.get(spinnerdoituong.getSelectedItemPosition()).getId();
        thongtinobj.setPdoituong(doituong);

        String tinh=dmtinh.get(spinnertinh.getSelectedItemPosition()).getId();
        thongtinobj.setPmatinh(tinh);
        String huyen=dmhuyen.get(spinnerhuyen.getSelectedItemPosition()).getId();
        thongtinobj.setPmahuyen(huyen);
        String xa=dmxa.get(spinnerxa.getSelectedItemPosition()).getId();
        thongtinobj.setPmaxa(xa);

        String noicap=dmtinh.get(spinnernoicap.getSelectedItemPosition()).getId();
        thongtinobj.setpNoiCapCMND(noicap);
    }


    private void Collect_info()
    {
        thongtinobj.setpEZ("939700303");//test
        thongtinobj.setPisdn(isdn);

        thongtinobj.setPisdn(txtisdn.getText().toString());
        thongtinobj.setPserial(eSim.getText().toString());
        thongtinobj.setPhoten(eHoten.getText().toString());
        thongtinobj.setPngaysinh(txtdatengaysinh.getText().toString());
        if (swgioitinh.isChecked())
        {
            thongtinobj.setpGioiTinh(0);
        }
        else
        {
            thongtinobj.setpGioiTinh(1);
        }
        thongtinobj.setPcmnd(eCmnd.getText().toString());
        String noicap=dmtinh.get(spinnernoicap.getSelectedItemPosition()).getId();
        thongtinobj.setpNoiCapCMND(noicap);
        thongtinobj.setpNgayCapCMND(txtdatengaycap.getText().toString());
        String doituong=dmdoituong.get(spinnerdoituong.getSelectedItemPosition()).getId();
        thongtinobj.setPdoituong(doituong);
        String tinh=dmtinh.get(spinnertinh.getSelectedItemPosition()).getId();
        thongtinobj.setPmatinh(tinh);
        String huyen=dmhuyen.get(spinnerhuyen.getSelectedItemPosition()).getId();
        thongtinobj.setPmahuyen(huyen);
        String xa=dmxa.get(spinnerxa.getSelectedItemPosition()).getId();
        thongtinobj.setPmaxa(xa);

        thongtinobj.setPsdt_lienhe(esdtlienhe.getText().toString());
        thongtinobj.setpEmail(eMail.getText().toString());
        thongtinobj.setPduong(eDiachi.getText().toString());

        thongtinobj.setpShop_Code(eShopcode.getText().toString());
        thongtinobj.setpEmployee(eMaNV.getText().toString());


        thongtinobj.setPfile_cmnd1(isdn + "/" + isdn + "_mattruoc.jpg");
        thongtinobj.setPfile_cmnd2(isdn + "/" + isdn + "_matsau.jpg");
        thongtinobj.setPfile_hoso(isdn + "/" + isdn + "_phieuthongtin.jpg");
        thongtinobj.setPfile_hinh(isdn + "/" + isdn + "_anhnguoi.jpg");





    }

    private void My_UploadImg2server()
    {
        //truoc
        try
        {
            String img1=collImage.getImgtruoc();
            if (img1.length()>0) {
                Json.logi("img1:" + img1);
                new UploadFileToServer(img1, isdn, "mattruoc").execute();
            }
        }
        catch (Exception e)
        {
            Json.logi("loi UploadImg2server:"+ e.toString());
        }
        //sau
        try
        {
            String img2=collImage.getImgsau();
            if (img2.length()>0) {
                Json.logi("img2:" + img2);
                new UploadFileToServer(img2, isdn, "matsau").execute();
            }
        }
        catch (Exception e)
        {
            Json.logi("loi UploadImg2server:"+ e.toString());
        }
        //phieu
        try
        {
            String img3=collImage.getImgphieu();
            if (img3.length()>0) {
                Json.logi("img3:" + img3);
                new UploadFileToServer(img3, isdn, "phieu").execute();
            }
        }
        catch (Exception e)
        {
            Json.logi("loi UploadImg2server:"+ e.toString());
        }
        //nguoi
        try
        {
            String img4=collImage.getImganhnguoi();
            if (img4.length()>0) {
                Json.logi("img4:" + img4);
                new UploadFileToServer(img4, isdn, "anhnguoi").execute();
            }
        }
        catch (Exception e)
        {
            Json.logi("loi UploadImg2server:"+ e.toString());
        }
        //chuKy
        try
        {
            String img5=collImage.getImgchuky();
            if (img5.length()>0) {
                Json.logi("img5:" + img5);
                new UploadFileToServer(img5, isdn, "chuky").execute();
            }
        }
        catch (Exception e)
        {
            Json.logi("loi UploadImg2server:"+ e.toString());
        }

    }






}
