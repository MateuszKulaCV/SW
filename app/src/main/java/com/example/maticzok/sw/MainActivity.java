package com.example.maticzok.sw;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.maticzok.sw.MainMenu.MainMenuActivity;

/**
 * Miała byc animacja jak w pokedexie, ale pokeapi.co nie dziala :C
 * Mialo byc sterowanie Listview'em przez Buttony
 * listview.smoothScrollToPostition( +1 albo -1 zalezne od wcisnietego);
 * tak samo jak zatwierdzanie opcji.
 * Ale to juz do zrobienia jak bedzie działał pokeapi.
 */

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Intent intent = new Intent(getApplicationContext(), MainMenuActivity.class);
        startActivity(intent);


    }


}
