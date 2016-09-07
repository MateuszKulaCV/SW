package com.example.maticzok.sw.Options;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;

import com.example.maticzok.sw.Model.Photo;
import com.example.maticzok.sw.R;
import com.example.maticzok.sw.Response.GetResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Maticzok on 2016-07-10.
 */
public class PhotoFragment extends Fragment {
    GridView photogrid;
    PhotoAdapter photoAdapter;
    String albumId;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
       View view = inflater.inflate(R.layout.fragment_photos,container,false);
        photogrid = (GridView) view.findViewById(R.id.photogrid);
       setView();

        return view;
    }


    void setView()
    {
        albumId = getArguments().getString("albumId");
        GetResponse.init();
        Call<List<Photo>> photocall = GetResponse.getApi().getPhotos(albumId);
        photocall.enqueue(new Callback<List<Photo>>() {
            @Override
            public void onResponse(Call<List<Photo>> call, Response<List<Photo>> response) {
                photoAdapter = new PhotoAdapter(getActivity().getApplicationContext(),response.body());
                photogrid.setAdapter(photoAdapter);
            }

            @Override
            public void onFailure(Call<List<Photo>> call, Throwable t) {
                Log.d("Photofragment",t.toString());
            }
        });

    }
}
