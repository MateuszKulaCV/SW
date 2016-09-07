package com.example.maticzok.sw.Options;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.maticzok.sw.Model.Album;
import com.example.maticzok.sw.R;

import java.util.List;

/**
 * Created by Maticzok on 2016-07-10.
 */
public class AlbumAdapter extends BaseAdapter {
    Context context;
    List<Album> albumList;

    public AlbumAdapter(Context context, List<Album> albumList)
    {
        this.context = context;
        this.albumList = albumList;
    }

    @Override
    public int getCount() {
        return albumList.size();
    }

    @Override
    public Object getItem(int i) {
        return albumList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int position, View view, ViewGroup viewGroup)
    {
        ViewHolder holder;
        if(view==null)
        {
         holder = new ViewHolder();
            LayoutInflater layoutInflater =(LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = layoutInflater.inflate(R.layout.singlerow_album,viewGroup,false);
            holder.AlbumId = (TextView) view.findViewById(R.id.albumId);
            holder.AlbumName = (TextView) view.findViewById(R.id.albumName);
            view.setTag(holder);

        }else
        {
            holder = (ViewHolder) view.getTag();
        }
            holder.AlbumId.setText(albumList.get(position).getId());
            holder.AlbumName.setText(albumList.get(position).getTitle());


        return view;
    }
    private class ViewHolder
    {
        TextView AlbumId,AlbumName;
    }

}
