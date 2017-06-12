package com.noc.smsverify.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.noc.smsverify.R;
import com.noc.smsverify.utils.Json;

/**
 * Created by defoliate on 21-10-2015.
 */
public class HomeActivity extends Activity
{
    EditText editText;
    Button btntimkiem;
    Context context;
    Json json;
    @Override
    protected void onCreate (Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        context=this;
        json=new Json();


        btntimkiem=(Button) findViewById(R.id.btbtimkiem);
        editText=(EditText) findViewById(R.id.editText1);

        btntimkiem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String text=editText.getText().toString().trim();
                if (text.length()>=9)
                {
                    Boolean dk=json.POST_checktbdangky(text);
                    if (dk)//can dang ky TTTB
                    {
                        Json.logi("dangky:"+text);
                        Intent intent = new Intent(context, FormActivity.class);
                        intent.putExtra("isdn", text);
                        Activity activity = (Activity) context;
                        activity.startActivityForResult(intent, 500);
                        activity.overridePendingTransition(R.anim.slide_in_bottom, R.anim.null_animation);
                    }

                }
                else {
                    Json.logi("timkiem:"+text);
                    Intent intent = new Intent(context, FormActivity.class);
                    intent.putExtra("isdn", text);
                    Activity activity = (Activity) context;
                    activity.startActivityForResult(intent, 500);
                    activity.overridePendingTransition(R.anim.slide_in_bottom, R.anim.null_animation);
                }

            }
        });


    }
}
