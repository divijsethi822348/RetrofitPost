package com.example.retrofitpost.Activities;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiClass {

    public static String BaseAddress="http://apptech.mobi/freshupApplication/new/index.php/api/user/";
    public static Retrofit retrofit=null;
    public static Retrofit api(){
        if (retrofit==null){

            retrofit=new Retrofit.Builder().baseUrl(BaseAddress).addConverterFactory(GsonConverterFactory.create()).build();
        }
        return retrofit;
    }
}
