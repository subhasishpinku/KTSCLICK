package com.kits.kitsclick;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.kits.kitsclick.setget.Logindata;

public class BalanceenquryThird extends AppCompatActivity {
    Button btn_prientreceipet,btn_home;
    TextView balance;
    ImageView Img2,Img;
    @Override
    protected void onCreate(@Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.aadher_activity);
        btn_prientreceipet = (Button)findViewById(R.id.btn_prientreceipet);
        btn_home = (Button)findViewById(R.id.btn_home);
        balance = (TextView) findViewById(R.id.balance);
        Img2 = (ImageView)findViewById(R.id.Img2);
        Img = (ImageView)findViewById(R.id.Img);
        Img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),NavigationDrawerActivity.class);
                startActivity(intent);
            }
        });
        Logindata logindata = SharedPrefManagerLogin.getInstance(getApplicationContext()).getLogindata();
        String name = String.valueOf(logindata.getName());
        String blance = String.valueOf(logindata.getBalance());
        balance.setText(blance);
        Img2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        btn_prientreceipet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        btn_home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),Blanceenquiryseconed.class);
                startActivity(intent);

            }
        });
    }
}