package com.noc.smsverify.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.noc.smsverify.R;
import com.noc.smsverify.object.TinhObj;
import com.noc.smsverify.utils.Json;

import java.util.ArrayList;
import java.util.List;

public class FormActivity extends AppCompatActivity  implements
        AdapterView.OnItemSelectedListener {

    Json json;
    Context context;

    private ImageView btn_datengaysinh, btn_datengaycap;

    Spinner spinnertinh,spinnerhuyen,spinnerxa,spinnerdoituong;
    Switch swgioitinh;
    TextView txtgioitinh;
    ImageView imgAddimg,imgstatus;


    ArrayList<TinhObj> dmtinh=new ArrayList<TinhObj>();
    ArrayList<TinhObj> dmhuyen=new ArrayList<TinhObj>();
    ArrayList<TinhObj> dmxa=new ArrayList<TinhObj>();

    ArrayList<TinhObj> dmdoituong=new ArrayList<TinhObj>();

    List listtinh, listhuyen,listxa, listdoituong;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form);
        json=new Json();
        context=this;
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


        spinnertinh = (Spinner) findViewById(R.id.spinnertinh);
        spinnerhuyen = (Spinner) findViewById(R.id.spinnerhuyen);
        spinnerxa = (Spinner) findViewById(R.id.spinnerxa);
        spinnerdoituong = (Spinner) findViewById(R.id.spinnerdoituong);

        swgioitinh=(Switch) findViewById(R.id.swgioitinh) ; final Boolean bnam=true;
        txtgioitinh= (TextView) findViewById(R.id.txtgioitinh) ;

        imgAddimg=(ImageView) findViewById(R.id.imgAddimg) ;
        imgstatus=(ImageView) findViewById(R.id.imgstatus) ;

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
                activity.startActivityForResult(intent, 100);
                activity.overridePendingTransition(R.anim.slide_in_bottom, R.anim.null_animation);
            }
        });


//        btn_datengaysinh= (ImageView) findViewById(R.id.btndatengaysinh);
//        btn_datengaycap= (ImageView) findViewById(R.id.btndatengaycap);
//
//
//        btn_datengaysinh.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View arg0) {
//
//            }
//        });
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int position, long id) {
        Spinner spinner = (Spinner) adapterView;
        if(spinner.getId() == R.id.spinnertinh)
        {
            //do this
            TinhObj tinhobj = (TinhObj) dmtinh.get(position);
            Toast.makeText(getApplicationContext(), tinhobj.getName(), Toast.LENGTH_LONG).show();
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
            Toast.makeText(getApplicationContext(), tinhobj.getName(), Toast.LENGTH_LONG).show();
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

//    @Override
//    public void onItemSelected(AdapterView<?> arg0, View arg1, int position,long id) {

//    }
//
//    @Override
//    public void onNothingSelected(AdapterView<?> arg0) {
//        // TODO Auto-generated method stub
//
//    }

}
