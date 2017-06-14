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
import com.noc.smsverify.utils.Utils;
import com.pratap.calendarview.views.DatePickerView;
import com.scalified.fab.ActionButton;

import java.util.ArrayList;

import java.util.List;

public class FormActivity extends AppCompatActivity  implements
        AdapterView.OnItemSelectedListener {

    Json json;
    Utils utils;
    Context context;
    Activity activity;
    TinyDB tinydb;

    ThongtinObj thongtinobj;
    String taikhoan="";
    String isdn="898028970";//test
    String currdate="";

    TextView txtisdn;
    EditText eSim, eHoten,eCmnd,esdtlienhe,eMail,eSonha,eTo,eDuong,eMaNV;

    ImageView listmannv;

    private ImageView btn_datengaysinh, btn_datengaycap;
    DatePickerView txtdatengaysinh, txtdatengaycap;
    Spinner spinnertinh,spinnerhuyen,spinnerxa,spinnerdoituong, spinnernoicap;
    Switch swgioitinh;
    TextView txtgioitinh;
    ImageView imgAddimg,imgstatus1;
    ImageView imgSign,imgstatus2;
    //private ActionButton fbutton;
    private ImageView fbutton;

    ArrayList<TinhObj> dmtinh=new ArrayList<TinhObj>();
    ArrayList<TinhObj> dmhuyen=new ArrayList<TinhObj>();
    ArrayList<TinhObj> dmxa=new ArrayList<TinhObj>();

    ArrayList<TinhObj> dmdoituong=new ArrayList<TinhObj>();

    List listtinh, listhuyen,listxa, listdoituong;
    //List<Map.Entry<String, String>> listimg = new ArrayList<Map.Entry<String, String>>();
    //Map<String,String> listimg = new HashMap<String, String>();
    CollimgObj collImage;

    //ArrayList<String> list_shopcode = new ArrayList<String>() ;
    ArrayList<String> list_manv = new ArrayList<String>() ;
    Boolean isdangky=false;
    Boolean ckAnh=false;
    Boolean ckChuky=false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form);
        json=new Json();
        utils=new Utils(this);
        context=this;
        activity= (Activity) this;
        tinydb=new TinyDB(this);
        //thongbaoloi("test");

        taikhoan=tinydb.getString("taikhoan");
        Bundle extras = getIntent().getExtras();

        if (extras != null) {
           isdn=extras.getString("isdn");
            isdangky=extras.getBoolean("isdangky");
        }
        Json.logi("isdn:"+isdn);
        if (isdangky)
        {
            eSim.setVisibility(View.GONE);
        }



        //list_shopcode=tinydb.getListString("shopcode");
        list_manv=tinydb.getListString("manv");

        thongtinobj=new ThongtinObj();
        //thongtinobj=new ThongtinObj("test");
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
        eSonha=(EditText) findViewById(R.id.txtsonha);
        eTo=(EditText) findViewById(R.id.txtto);
        eDuong=(EditText) findViewById(R.id.txtduong);
        //eShopcode=(EditText) findViewById(R.id.txtshopcode);
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
        //listshopcode=(ImageView) findViewById(R.id.listshopcode);

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

        if (isdangky)
        {
            txtisdn.setText("Đăng ký: "+isdn);
        }
        else
        {
            txtisdn.setText("Đấu nối: "+isdn);
        }


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

        fbutton=(ImageView) findViewById(R.id.action_button_submit);
        fbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Json.logi("click post");
                //Collect_info_test();
                currdate= Utils.getCurrentTimeStamp();
                Json.logi("getCurrentTimeStamp:"+ currdate);
                Collect_info();

                if (check_Collect_info()) {//thog tin hop le



                    String response = json.POST_Thongtinn(thongtinobj);
                    if (response.contains("OK")) {
                        //utils.thongbaoexit("Thông báo","Thành công");
                        My_UploadImg2server();
                        Toast.makeText(context, "Thành công", Toast.LENGTH_LONG).show();
                        activity.setResult(RESULT_OK);
                        activity.finish();

                    } else {
                        //activity.setResult(RESULT_CANCELED);
                        thongbaoloi("Thất bại:" + response);
                    }


                    //list_shopcode.add(eShopcode.getText().toString());
                    list_manv.add(eMaNV.getText().toString());

                    //tinydb.putListString("shopcode",list_shopcode);
                    tinydb.putListString("manv", list_manv);
                    //Json.logi("save shop code to db");
                }
                else
                {

                }
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
            dmhuyen = json.get_danhmuc(2, tinhobj.getParent());
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
            dmxa = json.get_danhmuc(3, tinhobj.getParent());
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
                ckAnh=true;
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
            else
            {
                ckAnh=false;
                imgstatus1.setImageResource(R.drawable.ic_report_problem_red_400_24dp);
            }
        }
        else if (requestCode==2)  //signature
            {
                if (resultCode == Activity.RESULT_OK) {
                    ckChuky=true;
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
                else
                {
                    ckChuky=false;
                    imgstatus2.setImageResource(R.drawable.ic_report_problem_red_400_24dp);
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

        thongtinobj.setpEZ(taikhoan);
        thongtinobj.setPisdn(isdn);
    }

    private Boolean check_Collect_info()
    {
        Boolean result=false;
        if (thongtinobj.getPserial().length()<13)
        {
            thongbaoloi("Serial Sim không hợp lệ");
        }
        else if (thongtinobj.getPhoten().length()==0)
        {
            thongbaoloi("Họ tên không được trống");
        }
        else if (thongtinobj.getPcmnd().length()==0)
        {
            thongbaoloi("CMND không được trống");
        }
        else if (thongtinobj.getPmaxa().length()==0)
        {
            thongbaoloi("Khu vực không hợp lệ");
        }
        else if (thongtinobj.getPngaysinh().length()==0)
        {
            thongbaoloi("Ngày sinh không hợp lệ");
        }
        else if (thongtinobj.getpNgayCapCMND().length()==0)
        {
            thongbaoloi("Ngày cấp CMND không hợp lệ");
        }
        else if (!ckAnh)
        {
            thongbaoloi("Chưa chọn ảnh hồ sơ");
        }
        else if (!ckChuky)
        {
            thongbaoloi("Chưa ký tên");
        }
        else {
            result = true;
        }

        return  result;
    }

    private void Collect_info()
    {
        //thongtinobj.setpEZ("939700303");//test
        thongtinobj.setpEZ(taikhoan);
        thongtinobj.setPisdn(isdn);

        if (isdangky)
        {
            thongtinobj.setpPhanLoai(2);
        }
        else
        {
            thongtinobj.setpPhanLoai(1);
        }

        thongtinobj.setPserial(eSim.getText().toString());
        thongtinobj.setPhoten(eHoten.getText().toString());
        thongtinobj.setPngaysinh(txtdatengaysinh.getText().toString());
        if (swgioitinh.isChecked())
        {
            thongtinobj.setpGioiTinh(1);
        }
        else
        {
            thongtinobj.setpGioiTinh(0);
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
        thongtinobj.setPduong("");

        thongtinobj.setpShop_Code("");
        thongtinobj.setpEmployee(eMaNV.getText().toString());


        thongtinobj.setPfile_cmnd1(isdn +"_"+currdate+ "/" + isdn + "_mattruoc.jpg");
        thongtinobj.setPfile_cmnd2(isdn +"_"+currdate+ "/" + isdn + "_matsau.jpg");
        thongtinobj.setPfile_hoso(isdn +"_"+currdate+ "/" + isdn + "_phieuthongtin.jpg");
        thongtinobj.setPfile_hinh(isdn +"_"+currdate+ "/" + isdn + "_anhnguoi.jpg");

        thongtinobj.setPfile_chuky(isdn +"_"+currdate+ "/" + isdn + "_chuky.jpg");

        thongtinobj.setPserial("8401161033617932");//test


    }

    private void My_UploadImg2server()
    {
        //truoc
        try
        {
            String img1=collImage.getImgtruoc();
            if (img1.length()>0) {
                Json.logi("img1:" + img1);
                new UploadFileToServer(img1, isdn, "mattruoc",currdate).execute();
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
                new UploadFileToServer(img2, isdn, "matsau",currdate).execute();
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
                new UploadFileToServer(img3, isdn, "phieu",currdate).execute();
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
                new UploadFileToServer(img4, isdn, "anhnguoi",currdate).execute();
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
                new UploadFileToServer(img5, isdn, "chuky",currdate).execute();
            }
        }
        catch (Exception e)
        {
            Json.logi("loi UploadImg2server:"+ e.toString());
        }

    }
    public void thongbaoloi(String noidung){
        android.app.AlertDialog.Builder builder = new android.app.AlertDialog.Builder(context);
        builder.setTitle("Lỗi");
        builder.setMessage(noidung)
                .setCancelable(false)
                .setPositiveButton("OK",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog,
                                                int id) {

                            }
                        });
        android.app.AlertDialog alert = builder.create();
        alert.show();

    }






}
