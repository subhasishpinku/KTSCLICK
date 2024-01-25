package com.kits.kitsclick;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputEditText;
import com.google.gson.Gson;
import com.kits.kitsclick.setget.Logindata;
import com.kits.kitsclick.setget.Mobiledatarechagevalue;
import com.kits.kitsclick.setget.Mobilerecharedata;
import com.kits.kitsclick.setget.Mobleoperatorlist;
import com.kits.kitsclick.setget.Pinlist;
import com.kits.kitsclick.setget.Pinresult;
import com.kits.kitsclick.setget.Pinset;
import com.kits.kitsclick.setget.Pinsetdata;

import retrofit2.Call;
import retrofit2.Callback;

public class Pinactivity extends AppCompatActivity {
    String name;
    TextView nameId;
    TextInputEditText pinID,comfrompinId;
    Button loginpin;
    @Override
    protected void onCreate(@Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pin_activity);
        nameId = (TextView)findViewById(R.id.nameId);
        loginpin = (Button)findViewById(R.id.loginpin);
        pinID = (TextInputEditText)findViewById(R.id.pinID);
        comfrompinId = (TextInputEditText)findViewById(R.id.comfrompinId);
        Logindata logindata = SharedPrefManagerLogin.getInstance(getApplicationContext()).getLogindata();
        name = String.valueOf(logindata.getName());
        nameId.setText(name);
        loginpin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                logindata();

            }
        });

    }

    public void logindata(){
        boolean temp=true;
        final String passid = pinID.getText().toString();
        final String conpass = comfrompinId.getText().toString();
        if (TextUtils.isEmpty(passid)) {
            pinID.setError("Enter your Pin");
            pinID.requestFocus();
            return;
        }
        if (TextUtils.isEmpty(conpass)) {
            comfrompinId.setError("Enter your Comfirom Pin");
            comfrompinId.requestFocus();
            return;
        }
        if(!passid.equals(conpass)){
            Toast.makeText(Pinactivity.this,"Pin Not matching",Toast.LENGTH_SHORT).show();
            temp=false;
            return;
        }

        Pinset  pinset = new Pinset();
        pinset.setLoginpin(conpass);
        Log.d("UpgradeSchool", "onClick: "+new Gson().toJson(pinset));
        Call<Pinresult> call = ApiClientToken.getInstance().pindata(
                pinset
        );
        call.enqueue(new Callback<Pinresult>() {
            @Override
            public void onResponse(Call<Pinresult> call, retrofit2.Response<Pinresult> response) {
//                Log.e("DATA", " "+response.body().getStatusCode());
                if (response.isSuccessful()){
                    String error = response.body().getError();
                    String msg = response.body().getMessage();
                    Log.e("loginpin",error+" "+loginpin);
                    if (response.body().getError().equals("false")){
                        Toast.makeText(Pinactivity.this,msg,Toast.LENGTH_SHORT).show();
                        String loginid = response.body().getData().getLoginpin();
                        Log.e("loginid",loginid);
                        Pinsetdata loginToken = new Pinsetdata(
                                loginid);
                        SharedPrefManagerpin.getInstance(getApplicationContext()).userping(loginToken);
                        Intent intent = new Intent(getApplicationContext(),ConfrompinActivity.class);
                        startActivity(intent);
                    }

                }
                else {
                    Log.e("DATA", " "+response.message());
                }
            }
            @Override
            public void onFailure(Call<Pinresult> call, Throwable t) {

            }
        });

    }
}
