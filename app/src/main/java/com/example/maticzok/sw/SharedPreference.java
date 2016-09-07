package com.example.maticzok.sw;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import com.example.maticzok.sw.Model.User;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

/**
 * Created by Maticzok on 2016-07-09.
 */
public class SharedPreference {
    public static final String PREFS_NAME = "USERS_APP";
    public static final String FAVORITES = "User_Fav";

    public SharedPreference()
    {
        super();
    }
    public void saveFavorites(Context context, List<User> favorites)
    {
        SharedPreferences setting;
        SharedPreferences.Editor editor;


        setting = context.getSharedPreferences(PREFS_NAME,Context.MODE_PRIVATE);
        editor = setting.edit();
        Gson gson = new Gson();
        String jsonfav = gson.toJson(favorites);

        editor.putString(FAVORITES,jsonfav);
        editor.commit();
    }

    public void addFavorite(Context context, User user)
    {
        List<User> users = getFavorites(context);
        if(users==null)
        {
            users = new ArrayList<User>();
        }
        users.add(user);
        saveFavorites(context,users);
    }

    public void removeFavorite(Context context, User user)
    {
        ArrayList<User> users = getFavorites(context);
        if(users!=null)
        {
            int i=1;
            for(Iterator<User> it = users.iterator();it.hasNext();)
            {
                User nextUser = it.next();

                Log.d("iterator",nextUser.getId()+" "+user.getId());
                if(nextUser.getId().equals(user.getId()))
                {
                    it.remove();
                    Log.d("removerd",user.getName());
                }
            }
            saveFavorites(context,users);
        }
    }

    public ArrayList<User> getFavorites(Context context)
    {
        SharedPreferences settings;
        List<User> fav;
        settings = context.getSharedPreferences(PREFS_NAME,Context.MODE_PRIVATE);
        if(settings.contains(FAVORITES))
        {
            String jsonfav = settings.getString(FAVORITES,null);
            Gson gson = new Gson();
            User[] temp = gson.fromJson(jsonfav,User[].class);
            fav = Arrays.asList(temp);
            fav = new ArrayList<User>(fav);
        }else
        {
            return null;
        }
    return (ArrayList<User>) fav;
    }
    //method only for testing sharedpref
    public void clearpref(Context context)
    {
        SharedPreferences setting;
        SharedPreferences.Editor editor;
        setting = context.getSharedPreferences(SharedPreference.PREFS_NAME,Context.MODE_PRIVATE);
        editor = setting.edit();
        editor.clear().commit();
    }
}
