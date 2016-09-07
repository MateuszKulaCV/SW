package com.example.maticzok.sw.Options;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.maticzok.sw.Model.Post;
import com.example.maticzok.sw.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Maticzok on 2016-07-09.
 */
public class PostAdapter extends BaseAdapter {
    List<Post> posts;
    Context context;

    public PostAdapter(Context context, List<Post> posts)
    {
        this.context = context;
        this.posts = posts;
    }


    @Override
    public int getCount() {
        return posts.size();
    }

    @Override
    public Object getItem(int i) {
        return  posts.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {
        ViewHolder holder;
        if(view==null)
        {
            holder = new ViewHolder();
            LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = layoutInflater.inflate(R.layout.singlerow_post,viewGroup,false);
            holder.posttitle = (TextView) view.findViewById(R.id.posttitle);
            holder.postbody = (TextView) view.findViewById(R.id.postbody);
            view.setTag(holder);
        }else
        {
            holder = (ViewHolder) view.getTag();
        }
        holder.posttitle.setText("Title: "+posts.get(position).getTitle());
        holder.postbody.setText(posts.get(position).getBody());

        return view;
    }

    private class ViewHolder
    {
        TextView posttitle,postbody;
    }

}
