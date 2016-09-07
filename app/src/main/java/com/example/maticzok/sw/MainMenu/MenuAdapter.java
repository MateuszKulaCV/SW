package com.example.maticzok.sw.MainMenu;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.maticzok.sw.R;

import java.util.ArrayList;

/**
 * Created by Maticzok on 2016-07-08.
 * Custom MenuAdapter
 */
public class MenuAdapter extends BaseAdapter {
    ArrayList<String> items;
    Context context;

    public MenuAdapter(Context context, ArrayList<String> items)
    {
        this.context = context;
        this.items = items;
    }

    @Override
    public int getCount() {
        return items.size();
    }

    @Override
    public Object getItem(int position) {
        return items.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {
        ViewHolder holder;

        if(view == null)
        {
            holder = new ViewHolder();
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.singlerow_menu,viewGroup,false);
            holder.icon = (ImageView) view.findViewById(R.id.menuicon);
            holder.optionname = (TextView) view.findViewById(R.id.menuoptionname);

            view.setTag(holder);
        }else
        {
            holder = (ViewHolder) view.getTag();
        }
            holder.optionname.setText(items.get(position));


        return view;
    }

    private class ViewHolder
    {
        ImageView icon;
        TextView optionname;
    }


}
