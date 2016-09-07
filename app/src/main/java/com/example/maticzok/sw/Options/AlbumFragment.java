package com.example.maticzok.sw.Options;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.maticzok.sw.Model.Album;
import com.example.maticzok.sw.R;
import com.example.maticzok.sw.Response.GetResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Maticzok on 2016-07-10.
 */
public class AlbumFragment extends Fragment {

        ListView albumList;
        AlbumAdapter albumAdapter;
        String userId;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_album,container,false);
        albumList = (ListView) view.findViewById(R.id.albumList);

        setView();

        albumList.setOnItemClickListener(albumListener);

        return view;
    }

    private AdapterView.OnItemClickListener albumListener = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
            ((OptionsActivity)getActivity()).getAlbumPhotos((Album)albumAdapter.getItem(i));
        }
    };

    void setView()
    {
        userId = getArguments().getString("userId");
        GetResponse.init();
        Call<List<Album>> albumcall = GetResponse.getApi().getAlbums(userId);

        albumcall.enqueue(new Callback<List<Album>>() {
            @Override
            public void onResponse(Call<List<Album>> call, Response<List<Album>> response) {
                albumAdapter = new AlbumAdapter(getActivity().getApplicationContext(), response.body());
                albumList.setAdapter(albumAdapter);
            }

            @Override
            public void onFailure(Call<List<Album>> call, Throwable t) {
                Log.d("Album conn fail",t.toString());
            }
        });
    }
}
