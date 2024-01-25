package com.kits.kitsclick;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.google.gson.Gson;
import com.kits.kitsclick.retrofit.APIClient;
import com.kits.kitsclick.setget.LoginResult;
import com.kits.kitsclick.setget.LoginValue;
import com.kits.kitsclick.setget.RegistrationValue;
import com.kits.kitsclick.setget.Registrationresult;
import com.kits.kitsclick.util.Utility;

import retrofit2.Call;
import retrofit2.Callback;

public class RegistrationActivity extends AppCompatActivity {
    Toolbar toolbar;
    EditText agName,nameId,lastnameId,loginName,emailId,phoneId,addresId,panId,passId,conPass;
    Button nextId_Agen;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        agName = (EditText)findViewById(R.id.agName);
        nameId = (EditText)findViewById(R.id.nameId);
        lastnameId = (EditText)findViewById(R.id.lastnameId);
        loginName = (EditText)findViewById(R.id.loginName);
        emailId = (EditText)findViewById(R.id.emailId);
        phoneId = (EditText)findViewById(R.id.phoneId);
        addresId = (EditText)findViewById(R.id.addresId);
        panId = (EditText)findViewById(R.id.panId);
        passId = (EditText)findViewById(R.id.passId);
        conPass = (EditText)findViewById(R.id.conPass);
        nextId_Agen = (Button)findViewById(R.id.nextId_Agen);
        nextId_Agen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                logindata();
            }
        });
        initToolBar();
    }
    public void initToolBar() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("Create account          ");
        setSupportActionBar(toolbar);
        toolbar.setNavigationIcon(R.drawable.ic_arrow_back_black_24dp);
        toolbar.setNavigationOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        finish();
                    }
                }
        );
    }

    public void logindata(){
        boolean temp=true;
        final String agname = agName.getText().toString();
        final String nameid = nameId.getText().toString();
        final String lastnameid = lastnameId.getText().toString();
        final String loginname = loginName.getText().toString();
        final String emailid = emailId.getText().toString();
        final String phoneid = phoneId.getText().toString();
        final String addresid = addresId.getText().toString();
        final String panid = panId.getText().toString();
        final String passid = passId.getText().toString();
        final String conpass = conPass.getText().toString();

        if (TextUtils.isEmpty(agname)) {
            agName.setError("Enter your Agent Name");
            agName.requestFocus();
            return;
        }
        if (TextUtils.isEmpty(nameid)) {
            nameId.setError("Enter your First name");
            nameId.requestFocus();
            return;
        }
        if (TextUtils.isEmpty(lastnameid)) {
            lastnameId.setError("Enter your Last name");
            lastnameId.requestFocus();
            return;
        }
        if (TextUtils.isEmpty(loginname)) {
            loginName.setError("Enter your Login name");
            loginName.requestFocus();
            return;
        }
        if (TextUtils.isEmpty(emailid)) {
            emailId.setError("Enter your email");
            emailId.requestFocus();
            return;
        }
        if (!android.util.Patterns.EMAIL_ADDRESS.matcher(emailid).matches()) {
            emailId.setError("Enter a valid email");
            emailId.requestFocus();
            return;
        }
        if (TextUtils.isEmpty(phoneid)) {
            phoneId.setError("Enter your phone");
            phoneId.requestFocus();
            return;
        }
        if (TextUtils.isEmpty(addresid)) {
            addresId.setError("Enter your Address");
            addresId.requestFocus();
            return;
        }
        if (TextUtils.isEmpty(panid)) {
            panId.setError("Enter your Pan");
            panId.requestFocus();
            return;
        }
        if (TextUtils.isEmpty(passid)) {
            passId.setError("Enter your Password");
            passId.requestFocus();
            return;
        }
        if (TextUtils.isEmpty(conpass)) {
            conPass.setError("Enter your Comfirom Password");
            conPass.requestFocus();
            return;
        }
        if(!passid.equals(conpass)){
            Toast.makeText(RegistrationActivity.this,"Password Not matching",Toast.LENGTH_SHORT).show();
            temp=false;
            return;
        }

        if (Utility.checkConnectivity(getApplicationContext())) {
            RegistrationValue registrationValue = new RegistrationValue();
            registrationValue.setAgencyname(agname);
            registrationValue.setFirstname(nameid);
            registrationValue.setLastname(lastnameid);
            registrationValue.setLoginname(loginname);
            registrationValue.setEmail(emailid);
            registrationValue.setMobile(phoneid);
            registrationValue.setAddress(addresid);
            registrationValue.setPannumber(panid);
            registrationValue.setPassword(passid);
            registrationValue.setPasswordconfirmation(conpass);
            Log.d("UpgradeSchool", "onClick: "+new Gson().toJson(registrationValue));
            Call<Registrationresult> call = APIClient.getInstance().registrationResultCall(
                    registrationValue
            );
            call.enqueue(new Callback<Registrationresult>() {
                @Override
                public void onResponse(Call<Registrationresult> call, retrofit2.Response<Registrationresult> response) {
//                Log.e("DATA", " "+response.body().getStatusCode());
                    if (response.isSuccessful()){
                        String message = response.body().getMessage();
                        Log.e("message",message);
                        if (response.body().getError().equals("false")){
                            Toast.makeText(getApplicationContext(),message,Toast.LENGTH_SHORT).show();
                            Intent i=new Intent(getApplicationContext(), Login.class);
                            startActivity(i);
                        }
                        else if (response.body().getError().equals("true")){
                              Toast.makeText(getApplicationContext(),message,Toast.LENGTH_SHORT).show();
                        }
                        else if (response.body().getError().equals("true")){
                            //  Log.e("DATA", response.body().getMessage());
                             Toast.makeText(getApplicationContext(),message,Toast.LENGTH_SHORT).show();
                        }
                    }
                    else {
                        Log.e("DATA", " "+response.message());
                    }

                }

                @Override
                public void onFailure(Call<Registrationresult> call, Throwable t) {

                }
            });

        }
        else {
//            PopupClass.showPopUpWithTitleMessageOneButton(LoginActivity.this, "Ok", "", "Sorry, No Internet connection found, Please check your network connection", "", new PopupCallBackOneButton() {
//                @Override
//                public void onFirstButtonClick() {
//                    finish();
//                }
//            });
        }
    }
}
