package com.example.retrofitpost.Interfaces;

import com.example.retrofitpost.Models.ResendModel;
import com.example.retrofitpost.Models.SetModel;
import com.example.retrofitpost.Models.VerificationModel;

import retrofit2.Call;
import retrofit2.CallAdapter;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface Api {

    @FormUrlEncoded
    @POST("userRegister")
    Call<SetModel> setData(@Field("name") String name,
                           @Field("email") String email,
                           @Field("phone") String phone,
                           @Field("password") String password,
                           @Field("device_type") String deice_type,
                           @Field("reg_id") String reg_id);

    @FormUrlEncoded
    @POST("userLogin")
    Call<SetModel> getData(@Field("email") String email,
                           @Field("password") String password,
                           @Field("device_type") String deice_type,
                           @Field("reg_id") String reg_id);


    @FormUrlEncoded
    @POST("matchVerificationToken")
    Call<VerificationModel> verify(@Field("id") String ID,
                                   @Field("token") String Token);

    @FormUrlEncoded
    @POST("resendVerificationToken")
    Call<ResendModel> resend(@Field("id") String ID);
}
