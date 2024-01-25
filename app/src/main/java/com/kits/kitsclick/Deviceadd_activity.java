package com.kits.kitsclick;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatAutoCompleteTextView;

import com.google.android.material.textfield.TextInputEditText;
import com.google.gson.Gson;
import com.kits.kitsclick.setget.Addscannerdevice;
import com.kits.kitsclick.setget.Addscannerdeviceresult;
import com.kits.kitsclick.setget.Dthoerator;
import com.kits.kitsclick.setget.Listdatadevice;
import com.kits.kitsclick.setget.Listdevice;
import com.kits.kitsclick.setget.Logindata;
import com.kits.kitsclick.setget.Mobiledatarechagevalue;
import com.kits.kitsclick.setget.Mobilerecharedata;
import com.kits.kitsclick.setget.Mobleoperatorlist;
import com.kits.kitsclick.setget.Scannerdatalist;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;

public class Deviceadd_activity extends AppCompatActivity {
    AppCompatAutoCompleteTextView type_Id;
    TextInputEditText compalyNameID,mobilenumberId,serialnumberId;
    ArrayList<String> devicetype;
    String devicelavel,devicename;
    Button btn_next;
    TextView balance;
    ImageView Img2,Img;
    @Override
    protected void onCreate(@Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.device_add_activityadherwedal);
        type_Id = (AppCompatAutoCompleteTextView)findViewById(R.id.type_Id);
        compalyNameID = (TextInputEditText)findViewById(R.id.compalyNameID);
        mobilenumberId = (TextInputEditText)findViewById(R.id.mobilenumberId);
        serialnumberId = (TextInputEditText)findViewById(R.id.serialnumberId);
        btn_next = (Button)findViewById(R.id.btn_next);
        balance = (TextView)findViewById(R.id.balance);
        Img = (ImageView)findViewById(R.id.Img);
        Img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),NavigationDrawerActivity.class);
                startActivity(intent);
            }
        });
        Img2 = (ImageView)findViewById(R.id.Img2);
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
        devicetype = new ArrayList<>();
        btn_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Mobileoerator();
            }
        });
        mobileoperator();
    }

    public void mobileoperator(){
        Call<Scannerdatalist> call = ApiClientToken.getInstance().scannertype(
        );
        call.enqueue(new Callback<Scannerdatalist>() {
            @Override
            public void onResponse(Call<Scannerdatalist> call, retrofit2.Response<Scannerdatalist> response) {
                if (response.isSuccessful()){
                    String error = response.body().getError();
                    Log.e("response",""+response.body());
                    if (error.equals("false")){
                        for (int i = 0; i <response.body().getData().getDevice().size(); i++) {
                            Listdevice listdata =  response.body().getData().getDevice().get(i);
                            String name = listdata.getName();
                            String lavel = listdata.getLabel();
                            System.out.println("laveldata"+name+" "+lavel);
                            devicetype.add(name);
                            ArrayAdapter busnesstypee= new ArrayAdapter(getApplicationContext(),android.R.layout.simple_spinner_item,devicetype);
                            busnesstypee.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                            type_Id.setAdapter(busnesstypee);
                        }
                        type_Id.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                            @Override
                            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                                devicelavel = response.body().getData().getDevice().get(position).getLabel();
                                devicename = response.body().getData().getDevice().get(position).getName();
                                Log.e("operatortype",devicelavel);
                            }
                        });
                    }


                }
                else {
                    Log.e("DATA", " "+response.message());
                }

            }

            @Override
            public void onFailure(Call<Scannerdatalist> call, Throwable t) {

            }
        });
    }

    public void Mobileoerator(){
        final String companyname = compalyNameID.getText().toString();
        final String modelnumber = mobilenumberId.getText().toString();
        final String serialnumber = serialnumberId.getText().toString();

        if (TextUtils.isEmpty(companyname)) {
            compalyNameID.setError("Enter your Company Name");
            compalyNameID.requestFocus();
            return;
        }
        if (TextUtils.isEmpty(modelnumber)) {
            mobilenumberId.setError("Enter your Model Number");
            mobilenumberId.requestFocus();
            return;
        }
        if (TextUtils.isEmpty(serialnumber)) {
            serialnumberId.setError("Enter your Serial Number");
            serialnumberId.requestFocus();
            return;
        }

        Addscannerdevice addscannerdevice = new Addscannerdevice();
        addscannerdevice.setCompany(companyname);
        addscannerdevice.setModel(modelnumber);
        addscannerdevice.setSerial(serialnumber);
        addscannerdevice.setDivtype(devicelavel);
        Log.d("UpgradeSchool", "onClick: "+new Gson().toJson(addscannerdevice));
        Call<Addscannerdeviceresult> call = ApiClientToken.getInstance().addscannerdevicedatah(
                addscannerdevice
        );
        call.enqueue(new Callback<Addscannerdeviceresult>() {
            @Override
            public void onResponse(Call<Addscannerdeviceresult> call, retrofit2.Response<Addscannerdeviceresult> response) {
//                Log.e("DATA", " "+response.body().getStatusCode());
                if (response.isSuccessful()){
                    String message = response.body().getError();
                    if (response.body().getError().equals("false")){
                        String Errorcode = response.body().getMessage();
                        Toast.makeText(getApplicationContext(),Errorcode,Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(getApplicationContext(),NavigationDrawerActivity.class);
                        startActivity(intent);
                    }

                }
                else {
                    Log.e("DATA", " "+response.message());
                }
            }
            @Override
            public void onFailure(Call<Addscannerdeviceresult> call, Throwable t) {

            }
        });

    }

}
