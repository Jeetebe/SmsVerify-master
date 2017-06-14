package com.noc.smsverify.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.SearchView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.noc.smsverify.R;
import com.noc.smsverify.object.ThongtinObj;
import com.noc.smsverify.utils.Json;
import com.noc.smsverify.utils.TinyDB;

public class MainActivity extends AppCompatActivity {
    EditText editText;
    Button btntimkiem;
    ImageView btnlog;
    Context context;
    TextView txttaikhoan;
    Switch aSwitch;
    Json json;
    TinyDB tinyDB;
    String taikhoan;

    Boolean isdangky=false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        context=this;
        json=new Json();
        tinyDB=new TinyDB(this);

        Boolean islogin=tinyDB.getBoolean("login");
        if (!islogin)
        {
            Intent i = new Intent(MainActivity.this,
                    SmsActivity.class);
            //startActivity(i);
            startActivityForResult(i, 100);//Login
            //finish();
        }
        btnlog=(ImageView) findViewById(R.id.btnlog) ;
        aSwitch= (Switch) findViewById(R.id.switchlogout);
        txttaikhoan=(TextView) findViewById(R.id.txttaikhoan);
        btntimkiem=(Button) findViewById(R.id.btbtimkiem);
        editText=(EditText) findViewById(R.id.editText1);


        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void afterTextChanged(Editable s) {}

            @Override
            public void beforeTextChanged(CharSequence s, int start,
                                          int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start,
                                      int before, int count) {
                String text=editText.getText().toString().trim();
                //isdangky=false;
                if (text.length()>=9 && text.length()<=12 )
                {
                    isdangky=json.POST_checktbdangky(text);
                    if (isdangky)//can dang ky TTTB
                    {
                        Json.logi("dangky:"+text);
                        btntimkiem.setText("Đăng ký");
                    }else{
                        btntimkiem.setText("Tìm kiếm");
                    }


                }
                else{
                    btntimkiem.setText("Tìm kiếm");
                }
            }
        });

        aSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
              tinyDB.putBoolean("login",false);
                tinyDB.putString("taikhoan","");
                finish();
            }
        });
        btntimkiem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String text=editText.getText().toString().trim();
                isdangky=false;
                if (text.length()>=9)
                {
                    isdangky=json.POST_checktbdangky(text);
                    if (isdangky)//can dang ky TTTB
                    {


                        Json.logi("dangky:"+text);
                        Intent intent = new Intent(context, FormActivity.class);
                        intent.putExtra("isdn", text);
                        intent.putExtra("isdangky", isdangky);
                        Activity activity = (Activity) context;
                        activity.startActivityForResult(intent, 500);
                        activity.overridePendingTransition(R.anim.slide_in_bottom, R.anim.null_animation);
                    }
                }
               if (!isdangky)
               {
                    Json.logi("timkiem:"+text);
                    Intent intent = new Intent(context, SearchActivity.class);
                    intent.putExtra("isdn", text);
                    Activity activity = (Activity) context;
                    activity.startActivityForResult(intent, 500);
                    activity.overridePendingTransition(R.anim.slide_in_bottom, R.anim.null_animation);
                }

            }
        });
        btnlog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    Intent intent = new Intent(context, LogActivity.class);
                    Activity activity = (Activity) context;
                    activity.startActivityForResult(intent, 600);
                    activity.overridePendingTransition(R.anim.slide_in_bottom, R.anim.null_animation);

            }
        });
//        Bundle extras = getIntent().getExtras();
//        if (extras != null) {
//            taikhoan=extras.getString("taikhoan");
//            tinyDB.putString("taikhoan",taikhoan);
//            tinyDB.putBoolean("login",true);
//        }
//        Json.logi("taikhoan:"+taikhoan);
        taikhoan=tinyDB.getString("taikhoan");
        txttaikhoan.setText(taikhoan);


//        ThongtinObj test=new ThongtinObj("898019122");
//        String strtest= json.POST_TEST(test);
//        Toast.makeText(context, strtest, Toast.LENGTH_LONG).show();
//        Json.logi("nhan:"+strtest);

    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        overridePendingTransition(R.anim.null_animation, R.anim.slide_out_bottom);
        if (requestCode==100)// Login
        {
            if (tinyDB.getBoolean("login")) {
                taikhoan = tinyDB.getString("taikhoan");
                txttaikhoan.setText(taikhoan);
            } else {
                Json.logi("thoat");
                finish();
            }
        }
    }
}
