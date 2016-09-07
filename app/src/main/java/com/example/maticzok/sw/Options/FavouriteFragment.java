package com.example.maticzok.sw.Options;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.maticzok.sw.Model.User;
import com.example.maticzok.sw.R;
import com.example.maticzok.sw.SharedPreference;
import com.example.maticzok.sw.UserInfoDF;

import java.util.ArrayList;

/**
 * Created by Maticzok on 2016-07-07.
 */
public class FavouriteFragment extends Fragment {
    ListView favlist;
    UsersAdapter usersAdapter;
    SharedPreference sharedPreference;
    ArrayList<User> favusers;
    Context context;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_fav,container,false);
        favlist = (ListView) view.findViewById(R.id.favlist);
        setView();
        return view;
    }

    void setView()
    {
        context = getActivity().getApplicationContext();
        sharedPreference = new SharedPreference();
        favusers = sharedPreference.getFavorites(context);

         if(favusers!=null) {
            usersAdapter = new UsersAdapter(context, favusers);
          //one adapter to many fragments
            usersAdapter.setfav(true);
            favlist.setAdapter(usersAdapter);
        }


        favlist.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                UserInfoDF userInfoDF = new UserInfoDF();
                User user = (User)usersAdapter.getItem(i);
                userInfoDF.setUser(user);
                userInfoDF.show(getFragmentManager(),"UserInfoDF");


            }
        });



    }


}
