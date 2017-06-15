package com.noc.smsverify.activity;

import android.content.Context;
import android.content.Intent;
import android.provider.SearchRecentSuggestions;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.SearchView;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.Toast;

import com.noc.smsverify.R;
import com.noc.smsverify.utils.Json;

import java.util.ArrayList;
import java.util.List;

import adapter.ListAdapter;

public class SearchActivity extends AppCompatActivity {


    ListAdapter adapter;
    ListView listView;
    SearchView searchview;

    List<String> arrayList= new ArrayList<>();
    Context context;
    Json json;
    String isdn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //activityMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        setContentView(R.layout.activity_search);
        json=new Json();
        context=this;

        listView=(ListView) findViewById(R.id.list_view) ;
        searchview =(SearchView) findViewById(R.id.search) ;




        adapter= new ListAdapter(context,arrayList);
        listView.setAdapter(adapter);




        //listView.setAdapter(adapter);
        searchview.setActivated(true);
        searchview.setQueryHint("Nhập SĐT tìm kiếm");
        searchview.onActionViewExpanded();
        searchview.setIconified(false);
        searchview.clearFocus();
        searchview.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                Json.logi("onQueryTextSubmit");
                arrayList=json.searchkhoso(query);
                if (arrayList.size()==0)
                {
                    Toast.makeText(context, "Không tìm thấy", Toast.LENGTH_LONG).show();
                }

                ListAdapter adapter= new ListAdapter(context,arrayList);
                adapter.notifyDataSetChanged();
                listView.setAdapter(adapter);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {

                //adapter.getFilter().filter(newText);

                return false;
            }
        });
        Bundle extras = getIntent().getExtras();

        if (extras != null) {
            isdn=extras.getString("isdn");
            searchview.setQuery(isdn,true);
            searchview.clearFocus();
        }
        Json.logi("isdn:"+isdn);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        overridePendingTransition(R.anim.null_animation, R.anim.slide_out_bottom);
//        if (requestCode == 300)// Form
//        {
//
//        }
        finish();
    }
}
