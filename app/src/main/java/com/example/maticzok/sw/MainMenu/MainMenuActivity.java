package com.example.maticzok.sw.MainMenu;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.maticzok.sw.Options.OptionsActivity;
import com.example.maticzok.sw.R;

import java.util.ArrayList;
import java.util.Arrays;


/**
 * Created by Maticzok on 2016-07-08.
 */
public class MainMenuActivity extends AppCompatActivity {


    ListView menu;
    MenuAdapter menuAdapter;
    ArrayList<String> items = new ArrayList<>();


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mainmenu);
        menu = (ListView) findViewById(R.id.mainmenu);
        setView();
        menu.setOnItemClickListener(menuListener);
    }

    void setView()
    {
        setMenuList();
       menuAdapter = new MenuAdapter(getApplicationContext(),items);
        menu.setAdapter(menuAdapter);

    }
    private AdapterView.OnItemClickListener menuListener = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
            Intent intent = new Intent(getApplicationContext(), OptionsActivity.class);
            intent.putExtra("option",items.get(i));
            startActivity(intent);
        }
    };
    private
    void setMenuList()
    {
        items.addAll( Arrays.asList(getResources().getStringArray(R.array.OptionItems)));
        /*
        items.add("Favourite");
        items.add("Search");
        items.add("Options");
        */
    }

    @Override
    public void onBackPressed() {
        //YOU SHALL NOT BACKPRESS!
    }
}
