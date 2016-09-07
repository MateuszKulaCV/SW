package com.example.maticzok.sw.Options;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.example.maticzok.sw.Model.Post;
import com.example.maticzok.sw.R;
import com.example.maticzok.sw.Response.GetResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Maticzok on 2016-07-09.
 */
public class PostFragment extends Fragment {
    String userId;
    ListView postList;
    PostAdapter postAdapter;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_post,container,false);
        postList = (ListView) view.findViewById(R.id.postlist);
        setView();
     return view;

    }
    void setView()
    {
        userId = getArguments().getString("userId");
        GetResponse.init();
        Call<List<Post>> postCall = GetResponse.getApi().getPosts(userId);
        postCall.enqueue(new Callback<List<Post>>() {
            @Override
            public void onResponse(Call<List<Post>> call, Response<List<Post>> response) {
                postAdapter = new PostAdapter(getActivity().getApplicationContext(),response.body());
                postList.setAdapter(postAdapter);
            }

            @Override
            public void onFailure(Call<List<Post>> call, Throwable t) {
                Log.d("Postfragment/Onfailure:",t.toString());
            }
        });
    }
}
