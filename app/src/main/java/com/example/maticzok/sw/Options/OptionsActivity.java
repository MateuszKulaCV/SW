package com.example.maticzok.sw.Options;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.widget.FrameLayout;
import android.widget.TextView;


import com.example.maticzok.sw.Model.Album;
import com.example.maticzok.sw.Model.User;
import com.example.maticzok.sw.R;

/**
 * Created by Maticzok on 2016-07-07.
 */
public class OptionsActivity extends AppCompatActivity {
   TextView optionname;
    FrameLayout frameLayout;
    Fragment fragment;
   FragmentManager fragmentManager;
    String option;


    @Override
    public void onBackPressed() {
        if(getSupportFragmentManager().getBackStackEntryCount()>1)
        {
            getSupportFragmentManager().popBackStack();
        }else
        {
            super.onBackPressed();
        }

    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_options);
        optionname = (TextView) findViewById(R.id.option);
        frameLayout = (FrameLayout) findViewById(R.id.flContent);
        option = getIntent().getExtras().getString("option");
        optionname.setText(option);
        setFragment();
    }


    void setFragment()
    {
        switch(option)
        {
            case "Favourite":
                fragment = new FavouriteFragment();
                break;
            case "Search":
                fragment = new SearchFragment();
            break;
            case "Options":
                fragment = new OtherFragment();
                break;
        }

        fragmentManager = getSupportFragmentManager();

        //onbackpressed [tick]
        //back to menu not to empty flContent
        if(fragmentManager.getFragments()!=null)
        {
            fragmentManager.beginTransaction().replace(R.id.flContent, fragment).addToBackStack(null).commit();
        }else
        {
            fragmentManager.beginTransaction().replace(R.id.flContent, fragment).commit();
        }
    }

    public void getUserPost(User user)
    {

        optionname.setText(user.getUsername());
        Bundle data = new Bundle();
        data.putString("userId",user.getId());
        fragment = new PostFragment();
        fragment.setArguments(data);
        fragmentManager.beginTransaction().replace(R.id.flContent,fragment).addToBackStack(null).commit();

    }

    public void getUserAlbum(User user)
    {
        optionname.setText(user.getUsername());
        Bundle data = new Bundle();
        data.putString("userId",user.getId());
        fragment = new AlbumFragment();
        fragment.setArguments(data);
        fragmentManager.beginTransaction().replace(R.id.flContent,fragment).addToBackStack(null).commit();

    }

    public void getAlbumPhotos(Album album)
    {
        optionname.setText(album.getTitle());
        Bundle data = new Bundle();
        data.putString("albumId",album.getId());
        fragment = new PhotoFragment();
        fragment.setArguments(data);
        fragmentManager.beginTransaction().replace(R.id.flContent,fragment).addToBackStack(null).commit();

    }
}
