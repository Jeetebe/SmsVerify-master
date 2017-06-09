package com.noc.smsverify.activity;

import android.provider.SearchRecentSuggestions;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.SearchView;
import android.view.Menu;
import android.view.MenuItem;

import com.noc.smsverify.R;

public class SearchActivity extends AppCompatActivity {
    private SearchView searchView;
    private MenuItem searchItem;
    private SearchRecentSuggestions suggestions;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);


    }


    public boolean onCreateOptionsMenu(Menu menu) {

        searchItem = menu.add("Go");

        searchItem.setIcon(R.drawable.ic_search_white_36dp);

        MenuItemCompat.setActionView(searchItem, searchView);

        MenuItemCompat.setShowAsAction(searchItem,
                MenuItemCompat.SHOW_AS_ACTION_ALWAYS | MenuItemCompat.SHOW_AS_ACTION_COLLAPSE_ACTION_VIEW);

       // menu.add(0, R.id.menu_about, 0, R.string.lbl_about);

        return super.onCreateOptionsMenu(menu);
    }
}
