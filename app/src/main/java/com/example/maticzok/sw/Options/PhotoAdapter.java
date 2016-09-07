package com.example.maticzok.sw.Options;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.example.maticzok.sw.Model.Photo;
import com.example.maticzok.sw.R;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by Maticzok on 2016-07-10.
 */
public class PhotoAdapter extends BaseAdapter {
    List<Photo> photos;
    Context context;

    public PhotoAdapter(Context context, List<Photo> photos)
    {
        this.context= context;
        this.photos = photos;
    }
    @Override
    public int getCount() {
        return photos.size();
    }

    @Override
    public Object getItem(int position) {
        return photos.get(position);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {
       ViewHolder holder;
        if(view == null)
        {
            holder = new ViewHolder();
            LayoutInflater layoutInflater =(LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = layoutInflater.inflate(R.layout.singlerow_photo,viewGroup,false);
            holder.imageView = (ImageView) view.findViewById(R.id.photo);
            view.setTag(holder);
        }else
        {
            holder = (ViewHolder) view.getTag();
        }

        //nie dziala łączenie sie przez https, trzeba napisac nowego klienta do picasso
        //dziala na 50% bo trzeba "recznie" dekodowac url
        try {

            String bg = photos.get(position).getThumbnailUrl().substring(24);
            Log.d("url",bg);
            Picasso.with(context.getApplicationContext()).load("https://placeholdit.imgix.net/~text?txtsize=14&bg="+bg+"&txt="+bg+"&w=150&h=150").into(holder.imageView);
        }catch (IllegalArgumentException e)
        {
            Log.d("picasso",e.toString());
        }

            return view;
    }


    private class ViewHolder
    {
        ImageView imageView;

    }
}
