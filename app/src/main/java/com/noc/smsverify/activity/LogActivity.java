package com.noc.smsverify.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import com.noc.smsverify.R;
import com.noc.smsverify.object.LogObj;
import com.noc.smsverify.utils.Json;
import com.noc.smsverify.utils.TinyDB;

import java.util.ArrayList;
import java.util.List;

import adapter.ListAdapter;
import adapter.ListLogAdapter;

public class LogActivity extends AppCompatActivity {

    Json json;
    TinyDB tinyDB;
    String taikhoan;
    ListView listViewlog;
    ListLogAdapter adapter;
    List<LogObj> arrayList= new ArrayList<LogObj>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log);
        json=new Json();
        tinyDB=new TinyDB(this);
        taikhoan=tinyDB.getString("taikhoan");

        arrayList=json.get_Logs(taikhoan);
        listViewlog=(ListView) findViewById(R.id.listlog) ;
        adapter= new ListLogAdapter(this,arrayList);
        listViewlog.setAdapter(adapter);
    }
}
