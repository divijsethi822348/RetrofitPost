package com.example.retrofitpost.Activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.retrofitpost.SahredPrefrences.Common;
import com.example.retrofitpost.Interfaces.Api;
import com.example.retrofitpost.Models.SetModel;
import com.example.retrofitpost.R;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {
    EditText email,password,device_type,reg_id;
    String Email="",Password="",Device_Type="",Reg_Id="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        email=findViewById(R.id.email2);
        password=findViewById(R.id.password2);
        device_type=findViewById(R.id.device_type2);
        reg_id=findViewById(R.id.reg_id2);



    }

    public void login(View view) {
        Email=email.getText().toString().trim();
        Password=password.getText().toString().trim();
        Device_Type=device_type.getText().toString().trim();
        Reg_Id=reg_id.getText().toString().trim();

        getData(Email,Password,Device_Type,Reg_Id);
    }

    private void getData(String email, String password, String device_type, String reg_id) {

        Api api=ApiClass.api().create(Api.class);
        api.getData(email,password,device_type,reg_id).enqueue(new Callback<SetModel>() {
            @Override
            public void onResponse(Call<SetModel> call, Response<SetModel> response) {

                if(response.body().getSuccess().equalsIgnoreCase("1")){
                    Intent intent=new Intent(LoginActivity.this,MainActivity.class);
                    startActivity(intent);
                }
                else if (response.body().getSuccess().equalsIgnoreCase("2")){
                    Common.SaveToken(LoginActivity.this,"ID",response.body().getDetails().getId());
                    int otp=response.body().getDetails().getOtp();
                    Toast.makeText(LoginActivity.this, ""+otp, Toast.LENGTH_LONG).show();

                    Intent intent=new Intent(LoginActivity.this,OtpVerification.class);
                    startActivity(intent);
                }

            }

            @Override
            public void onFailure(Call<SetModel> call, Throwable t) {
                Toast.makeText(LoginActivity.this, ""+t, Toast.LENGTH_SHORT).show();

            }
        });
    }
}
