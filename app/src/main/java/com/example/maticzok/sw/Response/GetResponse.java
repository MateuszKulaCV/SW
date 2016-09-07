package com.example.maticzok.sw.Response;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Maticzok on 2016-07-08.
 */
public class GetResponse {
    public static final  String url = "http://jsonplaceholder.typicode.com/";
    static GetResponse getResponse;
    GetResponseInterface getResponseInterface;

    public GetResponse()
    {
        Retrofit retrofit = new Retrofit.Builder().addConverterFactory(GsonConverterFactory.create())
                .baseUrl(url)
                .build();

         getResponseInterface = retrofit.create(GetResponseInterface.class);
    }

    public static void init()
    {
        getResponse = new GetResponse();
    }

    public static GetResponseInterface getApi()
    {
        return getResponse.getResponseInterface;
    }


}
