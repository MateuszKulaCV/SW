package com.example.maticzok.sw.Response;

import com.example.maticzok.sw.Model.Album;
import com.example.maticzok.sw.Model.Photo;
import com.example.maticzok.sw.Model.Post;
import com.example.maticzok.sw.Model.User;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * TODO
 * PUT/DELETE
 */
public interface GetResponseInterface {

    @GET("users/")
    Call<List<User>> getResp();

    @GET("posts/")
    Call<List<Post>> getPosts(@Query("userId") String userId);

    @GET("albums/")
    Call<List<Album>> getAlbums(@Query("userId") String userId);

    @GET("photos/")
    Call<List<Photo>> getPhotos(@Query("albumId") String albumId);


}
