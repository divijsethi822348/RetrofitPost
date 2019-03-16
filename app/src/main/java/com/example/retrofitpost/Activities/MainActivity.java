package com.example.retrofitpost.Activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.retrofitpost.SahredPrefrences.Common;
import com.example.retrofitpost.Interfaces.Api;
import com.example.retrofitpost.Models.SetModel;
import com.example.retrofitpost.R;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    EditText name,email,password,phone,device_type,reg_id;
    Button submit,login;
    String Name="",Email="",Phone="",Password="",Device_type="",Reg_id="";
    String id="";
    int otpGenerated;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        name=findViewById(R.id.name);
        email=findViewById(R.id.email);
        phone=findViewById(R.id.phone);
        password=findViewById(R.id.password);
        device_type=findViewById(R.id.device_type);
        reg_id=findViewById(R.id.reg_id);
        submit=findViewById(R.id.submit);

        login=findViewById(R.id.for_login);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this,LoginActivity.class);
                startActivity(intent);
            }
        });



    }



    public void submit(View view) {

        Name=name.getText().toString().trim();
        Email=email.getText().toString().trim();
        Phone=phone.getText().toString().trim();
        Password=password.getText().toString().trim();
        Device_type=device_type.getText().toString().trim();
        Reg_id=reg_id.getText().toString().trim();


        setData(Name,Email,Phone,Password,Device_type,Reg_id);



    }



    private void setData(final String name, final String email, final String phone, final String password, final String device_type, final String reg_id) {
        Api api=ApiClass.api().create(Api.class);
        api.setData(name,email,phone,password,device_type,reg_id).enqueue(new Callback<SetModel>() {
            @Override
            public void onResponse(Call<SetModel> call, Response<SetModel> response) {
                Toast.makeText(MainActivity.this, "Success", Toast.LENGTH_SHORT).show();
                if (response.body().getSuccess().equalsIgnoreCase("1")) {
//                    id=response.body().getDetails().getId();
                    Common.SaveToken(MainActivity.this,"ID",response.body().getDetails().getId());
                    otpGenerated=response.body().getDetails().getOtp();

                    Toast.makeText(MainActivity.this, "Your otp is: "+otpGenerated, Toast.LENGTH_LONG).show();

                    Intent intent=new Intent(MainActivity.this,OtpVerification.class);
                    startActivity(intent);

                }


            }

            @Override
            public void onFailure(Call<SetModel> call, Throwable t) {
                Toast.makeText(MainActivity.this, "Failed"+t, Toast.LENGTH_SHORT).show();

            }
        });


    }



}
