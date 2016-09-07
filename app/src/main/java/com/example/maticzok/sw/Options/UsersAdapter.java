package com.example.maticzok.sw.Options;


import android.app.Activity;
import android.app.PendingIntent;
import android.content.Context;
import android.content.SharedPreferences;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.Filter;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import com.example.maticzok.sw.Model.User;
import com.example.maticzok.sw.R;
import com.example.maticzok.sw.SharedPreference;
import com.example.maticzok.sw.Options.OptionsActivity;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Maticzok on 2016-07-08.
 */
public class UsersAdapter extends BaseAdapter {

    Filter namefilter,nofilter;
    Context context;
    List<User> users;
    List<User> usersmem;
    SharedPreference sharedPreference;
        //check if favoritefragment
    boolean iffav = false;

    public UsersAdapter(Context context,List<User> users)
    {
        this.context = context;
        this.users = users;
        this.usersmem = users;
        sharedPreference = new SharedPreference();
    }

    public void setfav(boolean t){this.iffav=t;}

    public void setUsers( List<User> users)
    {
        this.users = users;
    }

    public  List<User> getUsersmem()
    {
        return usersmem;
    }

    void swap(List<User> users)
    {
        this.users = users;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return users.size();
    }

    @Override
    public Object getItem(int i) {
        return users.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(final int position, View view, ViewGroup viewGroup) {
        final ViewHolder holder;

        if(view==null)
        {
            holder = new ViewHolder();
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.singlerow_user,viewGroup,false);
            holder.userId = (TextView) view.findViewById(R.id.userId);
            holder.userName = (TextView) view.findViewById(R.id.userName);
            holder.button = (Button) view.findViewById(R.id.optionbtn);
            view.setTag(holder);
        }else
        {
            holder = (ViewHolder) view.getTag();
        }

        holder.userId.setText(users.get(position).getId());
        holder.userName.setText(users.get(position).getUsername());

        //adding and deleting fav
        holder.button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View view) {
                final PopupMenu popupMenu = new PopupMenu(context,holder.button);
                popupMenu.getMenuInflater().inflate(R.menu.context_menu,popupMenu.getMenu());

                //Setting visibility depends on fav
                MenuItem addfav = (MenuItem) popupMenu.getMenu().findItem(R.id.addtofav);
                MenuItem delfav = (MenuItem) popupMenu.getMenu().findItem(R.id.deletefav);
                if(checkFavoriteItem(users.get(position)))
                {
                    addfav.setVisible(false);
                    delfav.setVisible(true);
                }else
                {
                    addfav.setVisible(true);
                    delfav.setVisible(false);
                }


                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem menuItem) {
                        int i = menuItem.getItemId();
                        if (i==R.id.addtofav)
                        {
                            sharedPreference.addFavorite(context,users.get(position));
                            Toast.makeText(context,"added",Toast.LENGTH_SHORT).show();
                            return true;
                        }else if (i== R.id.deletefav)
                        {
                            sharedPreference.removeFavorite(context,users.get(position));
                            Toast.makeText(context,"deleted",Toast.LENGTH_SHORT).show();

                            //swaping fav if its favouritefragment
                            if(iffav)
                            {
                                swap(sharedPreference.getFavorites(context));
                            }
                            return true;
                        }else
                        {
                            return onMenuItemClick(menuItem);
                        }
                    }
                });
                popupMenu.show();

            }
        });


        return view;
    }


    private class ViewHolder
    {
            TextView userId,userName;
        Button button;
    }

    public boolean checkFavoriteItem(User user) {
        boolean check = false;
        ArrayList<User> favorites = sharedPreference.getFavorites(context);
        if (favorites != null) {
            for (User u:favorites) {
                if (u.getId().equals(user.getId())) {
                    check = true;
                    break;
                }
            }
        }
        return check;
    }



    public Filter getNameFilter()
    {
        if(namefilter == null)
        {
            namefilter = new NameFilter();
        }
        return namefilter;
    }

    private class NameFilter extends Filter
    {
        @Override
        protected FilterResults performFiltering(CharSequence charSequence) {
            FilterResults filterResults = new FilterResults();
            List<User> usersfilter = users;
            if(charSequence == null || charSequence.length() ==0)
            {
                filterResults.count = usersfilter.size();
                filterResults.values = usersfilter;
            }else
            {
                ArrayList<User> filteredusers = new ArrayList<User>();
                for(User u:usersfilter)
                {
                    if(u.getUsername().toLowerCase().contains(charSequence.toString().toLowerCase()))
                    {
                        filteredusers.add(u);
                    }
                }
                filterResults.values = filteredusers;
                filterResults.count = filteredusers.size();
            }

            return filterResults;
        }

        @Override
        protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
        if(filterResults.count==0)
        {

            notifyDataSetInvalidated();
        }else
        {
            users =(List<User>) filterResults.values;
            notifyDataSetChanged();
        }

        }

    }


    public Filter getNOFilter()
    {
        if(nofilter == null)
        {
            nofilter = new NOFilter();
        }
        return nofilter;
    }



    private class NOFilter extends Filter
    {
        @Override
        protected FilterResults performFiltering(CharSequence charSequence) {
            FilterResults filterResults = new FilterResults();
            List<User> usersfilter = users;
            if(charSequence == null || charSequence.length() ==0)
            {
                filterResults.count = usersfilter.size();
                filterResults.values = usersfilter;
            }else
            {
                ArrayList<User> filteredusers = new ArrayList<User>();
                for(User u:usersfilter)
                {
                    if(u.getId().toLowerCase().contains(charSequence.toString().toLowerCase()))
                    {
                        filteredusers.add(u);
                    }
                }
                filterResults.values = filteredusers;
                filterResults.count = filteredusers.size();
            }

            return filterResults;
        }

        @Override
        protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
            if(filterResults.count==0)
            {

                notifyDataSetInvalidated();
            }else
            {
                users =(List<User>) filterResults.values;
                notifyDataSetChanged();
            }

        }

    }
}
