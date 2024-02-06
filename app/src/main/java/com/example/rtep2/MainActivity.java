package com.example.rtep2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;

import com.example.rtep2.Employees.*;
import com.example.rtep2.*;
import com.example.rtep2.Sign_in_up.login;
import com.example.rtep2.Sign_in_up.register;
import com.example.rtep2.Sign_in_up.register_department;
import com.example.rtep2.Sign_in_up.register_vehicle;
import com.example.rtep2.Vehicles.*;

import retrofit2.Retrofit;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(new Intent(MainActivity.this, login.class));
                finish();
            }
        }, 3000);
    }
}