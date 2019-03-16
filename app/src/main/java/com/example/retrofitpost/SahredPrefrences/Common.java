package com.example.retrofitpost.SahredPrefrences;

import android.app.Activity;
import android.content.SharedPreferences;

public class Common {
    public static void SaveToken(Activity activity,String key,String value){
        SharedPreferences sharedPreferences=activity.getSharedPreferences("MyFile",0);
        SharedPreferences.Editor editor=sharedPreferences.edit();
        editor.putString(key,value);
        editor.commit();
    }

    public static String GetToken(Activity activity,String key){
        SharedPreferences sharedPreferences=activity.getSharedPreferences("MyFile",0);
        return sharedPreferences.getString(key,"");
    }

    public static void Logout(Activity activity){
        SharedPreferences sharedPreferences=activity.getSharedPreferences("MyFile",0);
        sharedPreferences.edit().clear().commit();
    }

}
