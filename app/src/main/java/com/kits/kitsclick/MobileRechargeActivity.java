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
import com.kits.kitsclick.retrofit.APIClient;
import com.kits.kitsclick.setget.Busnesstype;
import com.kits.kitsclick.setget.Listdatabusness;
import com.kits.kitsclick.setget.LoginResult;
import com.kits.kitsclick.setget.LoginValue;
import com.kits.kitsclick.setget.Logindata;
import com.kits.kitsclick.setget.Mobiledatarechagevalue;
import com.kits.kitsclick.setget.Mobileoperator;
import com.kits.kitsclick.setget.Mobilerecharedata;
import com.kits.kitsclick.setget.Mobleoperatorlist;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;

public class MobileRechargeActivity extends AppCompatActivity {
    AppCompatAutoCompleteTextView OperatorId;
    ArrayList<String> operatorname;
    String operatortype = "";
    String mobopername = "";
    TextInputEditText mobileno,amountId;
    Button moblerecharge;
    TextView blance;
    ImageView Img2;
    @Override
    protected void onCreate(@Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mobile_recharge);
        OperatorId = (AppCompatAutoCompleteTextView) findViewById(R.id.OperatorId);
        mobileno  = (TextInputEditText) findViewById(R.id.mobileno);
        amountId  = (TextInputEditText) findViewById(R.id.amountId);
        moblerecharge = (Button)findViewById(R.id.moblerecharge);
        blance = (TextView)findViewById(R.id.blance);
        Img2 = (ImageView)findViewById(R.id.Img2);
        Img2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        Logindata logindata = SharedPrefManagerLogin.getInstance(getApplicationContext()).getLogindata();
        String name = String.valueOf(logindata.getName());
        String bal = String.valueOf(logindata.getBalance());
        blance.setText(bal);
        operatorname = new ArrayList<String>();
        mobileoperator();

//        OperatorId.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                //  mobopername = OperatorId.getSelectedItem().toString();
//                mobopername = (String) parent.getItemAtPosition(position);
//                Log.e("Oper",mobopername);
//            }
//        });
        moblerecharge.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mobopername.equals("")) {
                    Log.e("NULL","1");
                    Toast.makeText(MobileRechargeActivity.this,"Select current oparator name",Toast.LENGTH_SHORT).show();
                }
                else{
                    Log.e("NULL","0");
                    Mobileoerator();


                }
            }
        });
    }
     public void mobileoperator(){
         Call<Mobileoperator> call = ApiClientToken.getInstance().operatorname(
         );
         call.enqueue(new Callback<Mobileoperator>() {
             @Override
             public void onResponse(Call<Mobileoperator> call, retrofit2.Response<Mobileoperator> response) {
                 if (response.isSuccessful()){
                     String error = response.body().getError();
                     Log.e("response",""+response.body());
                     if (error.equals("false")){
                         for (int i = 0; i <response.body().getData().getList().size(); i++) {
                             Mobleoperatorlist listdata =  response.body().getData().getList().get(i);
                             String name = listdata.getName();
                             System.out.println("year"+name);
                             operatorname.add(name);
                                 ArrayAdapter busnesstypee= new ArrayAdapter(getApplicationContext(),android.R.layout.simple_spinner_item,operatorname);
                                 busnesstypee.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                             OperatorId.setAdapter(busnesstypee);


                         }
                   ;  OperatorId.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                             @Override
                             public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                                 operatortype = response.body().getData().getList().get(position).getType();
                                 mobopername = response.body().getData().getList().get(position).getName();
                                 Log.e("operatortype",operatortype+" "+mobopername);
                             }
                         });
                     }


                 }
                 else {
                     Log.e("DATA", " "+response.message());
                 }

             }

             @Override
             public void onFailure(Call<Mobileoperator> call, Throwable t) {

             }
         });
     }

     public void Mobileoerator(){
         final String mobile = mobileno .getText().toString();
         final String amount = amountId.getText().toString();
         if (TextUtils.isEmpty(mobile)) {
             mobileno.setError("Enter your MobleNo");
             mobileno.requestFocus();
             return;
         }
         if (TextUtils.isEmpty(amount)) {
             amountId.setError("Enter your Amount");
             amountId.requestFocus();
             return;
         }
         Mobiledatarechagevalue mobiledatarechagevalue = new Mobiledatarechagevalue();
         mobiledatarechagevalue.setOperator(operatortype);
         mobiledatarechagevalue.setMobile(mobile);
         mobiledatarechagevalue.setAmount(amount);
         Log.d("UpgradeSchool", "onClick: "+new Gson().toJson(mobiledatarechagevalue));
         Call<Mobilerecharedata> call = ApiClientToken.getInstance().mobiledatarecharge(
                 mobiledatarechagevalue
         );
         call.enqueue(new Callback<Mobilerecharedata>() {
             @Override
             public void onResponse(Call<Mobilerecharedata> call, retrofit2.Response<Mobilerecharedata> response) {
//                Log.e("DATA", " "+response.body().getStatusCode());
                 if (response.isSuccessful()){
                     String message = response.body().getError();
                     if (response.body().getError().equals("false")){
                         Toast.makeText(MobileRechargeActivity.this,message,Toast.LENGTH_SHORT).show();
                         String Errorcode = response.body().getRechargedetail().getErrorcode();
                         String msg = response.body().getRechargedetail().getMessage();
                         String status = response.body().getRechargedetail().getStatus();
                         String amount = response.body().getRechargedetail().getData().getAmount();
                         String operrator = response.body().getRechargedetail().getData().getOperatorrefno();
                         String recharge = response.body().getRechargedetail().getData().getRechargenumber();
                         String softinfo = response.body().getRechargedetail().getData().getSoftrefno();
                         String taxdate = response.body().getRechargedetail().getData().getTxnDate();
//                         Intent intent = new Intent(getApplicationContext(),NavigationDrawerActivity.class);
//                         startActivity(intent);
                         Intent intent = new Intent(getApplicationContext(),Mobilerechargedata.class);
                         startActivity(intent);
                     }

                 }
                 else {
                     Log.e("DATA", " "+response.message());
                 }
             }
             @Override
             public void onFailure(Call<Mobilerecharedata> call, Throwable t) {

             }
         });

     }

}

