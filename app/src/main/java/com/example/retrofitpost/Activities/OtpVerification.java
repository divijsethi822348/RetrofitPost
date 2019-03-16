package com.example.retrofitpost.Activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.retrofitpost.SahredPrefrences.Common;
import com.example.retrofitpost.Interfaces.Api;
import com.example.retrofitpost.Models.ResendModel;
import com.example.retrofitpost.Models.VerificationModel;
import com.example.retrofitpost.R;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class OtpVerification extends AppCompatActivity {
    EditText otp;
    Button Verify;
    TextView resend;
    String otpFilled="";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_otp_verification);
        otp=findViewById(R.id.otp);
        Verify=findViewById(R.id.verify);
        resend=findViewById(R.id.resend);



        Verify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String id= Common.GetToken(OtpVerification.this,"ID");
                otpFilled=otp.getText().toString().trim();

                Api api=ApiClass.api().create(Api.class);
                api.verify(id,otpFilled).enqueue(new Callback<VerificationModel>() {
                    @Override
                    public void onResponse(Call<VerificationModel> call, Response<VerificationModel> response) {
                        if(response.body().getSuccess().equalsIgnoreCase("1")){
                            Toast.makeText(OtpVerification.this, "Verified Successfully", Toast.LENGTH_LONG).show();
                            Intent intent=new Intent(OtpVerification.this,MainActivity.class);
                            startActivity(intent);
                        }
                        else
                        {
                            Toast.makeText(OtpVerification.this, "Otp you entered is wrong", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<VerificationModel> call, Throwable t) {
                        Toast.makeText(OtpVerification.this, "Failed cuz of :"+t, Toast.LENGTH_SHORT).show();

                    }
                });

            }
        });

        resend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String id=Common.GetToken(OtpVerification.this,"ID");
                Api api=ApiClass.api().create(Api.class);
                api.resend(id).enqueue(new Callback<ResendModel>() {
                    @Override
                    public void onResponse(Call<ResendModel> call, Response<ResendModel> response) {
                        if(response.body().getSuccess().equalsIgnoreCase("1")){
                            Toast.makeText(OtpVerification.this, ""+response.body().getDetails().getOtp(), Toast.LENGTH_SHORT).show();
                        }

                    }

                    @Override
                    public void onFailure(Call<ResendModel> call, Throwable t) {
                        Toast.makeText(OtpVerification.this, ""+t, Toast.LENGTH_SHORT).show();

                    }
                });
            }
        });


    }
}
