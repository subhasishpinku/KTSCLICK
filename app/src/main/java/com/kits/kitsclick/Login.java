package com.kits.kitsclick;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.provider.Settings;
import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.google.gson.Gson;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.kits.kitsclick.retrofit.APIClient;
import com.kits.kitsclick.setget.LoginResult;
import com.kits.kitsclick.setget.LoginValue;
import com.kits.kitsclick.setget.Logindata;
import com.kits.kitsclick.setget.Pinsetdata;
import com.kits.kitsclick.util.Utility;
import com.kits.kitsclick.utility.HideSoftKeyboard;
import com.kits.kitsclick.utility.ShowSoftKeyboard;

import retrofit2.Call;
import retrofit2.Callback;

import static android.Manifest.permission.WRITE_EXTERNAL_STORAGE;
import static android.os.Build.VERSION.SDK_INT;

public class Login extends AppCompatActivity {
   TextView sgnUp,fpass;
   Button loginmain;
    private static final int PERMISSION_REQUEST_CODE = 1000;
    private static final int STORAGE_PERMISSION_CODE = 123;
    private static final int REQUEST_PERMISSIONS = 100;
    boolean boolean_permission;
//    EditText logId;
    TextInputEditText textPassword;
    String flag;
    HideSoftKeyboard hsk;
    ShowSoftKeyboard ssk;
    TextInputEditText logId;
    private Boolean saveLogin;
    String pinset = "";
    String loginpin= "";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        sgnUp = (TextView)findViewById(R.id.sgnUp);
        fpass = (TextView)findViewById(R.id.fpass);
        loginmain = (Button)findViewById(R.id.loginmain);
        logId = (TextInputEditText)findViewById(R.id.logId);
        textPassword = (TextInputEditText)findViewById(R.id.textPassword);
        pinset = SharedPrefManagerpin.getInstance(getApplicationContext()).getKeyPin();
        if (pinset != null && !pinset.isEmpty()){
            Intent intent = new Intent(getApplicationContext(),ConfrompinActivity.class);
            startActivity(intent);
            Log.e("Empty","0"+pinset);
        }
        else {
            Log.e("Empty","1"+pinset);
        }

        sgnUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkServiceStatus();
//            Intent intent = new Intent(getApplicationContext(),SignUp_Activity.class);
//            startActivity(intent);
                Intent intent = new Intent(getApplicationContext(),RegistrationActivity.class);
                startActivity(intent);
               // logindata();
            }
        });
        loginmain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               logindata();
                checkServiceStatus();

            }
        });
        fpass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkServiceStatus();
                Intent intent = new Intent(getApplicationContext(),Forgotpassword_Activity.class);
                startActivity(intent);
            }
        });
        textPassword.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if(actionId== EditorInfo.IME_ACTION_DONE){
                    //do something
                    closeKeyboard();
                }
                return false;
            }
        });

        requestPermission();
    }
    private void closeKeyboard()
    {
        View view = this.getCurrentFocus();
        if (view != null) {
            InputMethodManager manager
                    = (InputMethodManager)
                    getSystemService(
                            Context.INPUT_METHOD_SERVICE);
            manager.hideSoftInputFromWindow(
                            view.getWindowToken(), 0);
        }
    }
    private void requestPermission() {
        if (SDK_INT >= Build.VERSION_CODES.R) {
            try {
                Intent intent = new Intent(Settings.ACTION_MANAGE_APP_ALL_FILES_ACCESS_PERMISSION);
                intent.addCategory("android.intent.category.DEFAULT");
                intent.setData(Uri.parse(String.format("package:%s",getApplicationContext().getPackageName())));
                startActivityForResult(intent, 2296);
                Log.e("Permission","0");
            } catch (Exception e) {
                Intent intent = new Intent();
                intent.setAction(Settings.ACTION_MANAGE_ALL_FILES_ACCESS_PERMISSION);
                startActivityForResult(intent, 2296);
                Log.e("Permission","1");
            }
        } else {
            //below android 11
            ActivityCompat.requestPermissions(Login.this, new String[]{WRITE_EXTERNAL_STORAGE}, PERMISSION_REQUEST_CODE);
        }
    }
    @SuppressLint("NewApi")
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        //Checking the request code of our request
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == STORAGE_PERMISSION_CODE) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(getApplicationContext(), "Permission granted now you can read the storage", Toast.LENGTH_LONG).show();
            } else {
                Toast.makeText(getApplicationContext(), "Oops you just denied the permission", Toast.LENGTH_LONG).show();
            }
        }

        switch (requestCode) {
            case REQUEST_PERMISSIONS: {
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    boolean_permission = true;

                } else {
                    Toast.makeText(getApplicationContext(), "Please enable services to get gps", Toast.LENGTH_LONG).show();
                }
            }

        }
    }
    private void checkServiceStatus() {

        if ((ContextCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED)) {

            if ((ActivityCompat.shouldShowRequestPermissionRationale(Login.this, Manifest.permission.ACCESS_FINE_LOCATION))) {

            } else {
                ActivityCompat.requestPermissions(Login.this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION

                        },
                        REQUEST_PERMISSIONS);

            }
        } else {
            boolean_permission = true;

        }
    }

    public void logindata(){
        final String log = logId .getText().toString();
        final String password = textPassword.getText().toString();
        if (TextUtils.isEmpty(log)) {
            logId.setError("Enter your Login Id");
            logId.requestFocus();
            return;
        }
        if (TextUtils.isEmpty(password)) {
            textPassword.setError("Enter your password");
            textPassword.requestFocus();
            return;
        }
        if (Utility.checkConnectivity(getApplicationContext())) {
            LoginValue loginValue = new LoginValue();
            loginValue.setLogin_id(log);
            loginValue.setPassword(password);
            Log.d("UpgradeSchool", "onClick: "+new Gson().toJson(loginValue));
            Call<LoginResult> call = APIClient.getInstance().loginResultCall(
                    loginValue
            );
            call.enqueue(new Callback<LoginResult>() {
                @Override
                public void onResponse(Call<LoginResult> call, retrofit2.Response<LoginResult> response) {
//                Log.e("DATA", " "+response.body().getStatusCode());
                    if (response.isSuccessful()){
                        String message = response.body().getError();
                        if (response.body().getError().equals("false")){
                            String message1 = response.body().getError();
                            String aadharnumber = response.body().getData().getUserdata().getAadharnumber();
                            String accountno= response.body().getData().getUserdata().getAccountno();
                            String addedBy= response.body().getData().getUserdata().getAddedBy();
                            String addedOn= response.body().getData().getUserdata().getAddedOn();
                            String address = response.body().getData().getUserdata().getAddress();
                            String address1 = response.body().getData().getUserdata().getAddress1();
                            String addressproof = response.body().getData().getUserdata().getAddressproof();
                            String adharNo= response.body().getData().getUserdata().getAdharNo();
                            String aepspackID = response.body().getData().getUserdata().getAepspackID();
                            String agencyID = response.body().getData().getUserdata().getAgencyID();
                            String amazonpackID = response.body().getData().getUserdata().getAadharnumber();
                            String apitoken = response.body().getData().getUserdata().getApitoken();
                            String balance = response.body().getData().getUserdata().getBalance();
                            String bankname = response.body().getData().getUserdata().getBankname();
                            String bbpspackID = response.body().getData().getUserdata().getBbpspackID();
                            String branchname = response.body().getData().getUserdata().getBranchname();
                            String businessyear = response.body().getData().getUserdata().getBusinessyear();
                            String bussiness = response.body().getData().getUserdata().getBussiness();
                            String buspackID = response.body().getData().getUserdata().getBuspackID();
                            String city = response.body().getData().getUserdata().getCity();
                            String contactPerson = response.body().getData().getUserdata().getContactPerson();
                            String country = response.body().getData().getUserdata().getCountry();
                            String countryCode = response.body().getData().getUserdata().getCountryCode();
                            String coordinator = response.body().getData().getUserdata().getCoordinator();
                            String createdat = response.body().getData().getUserdata().getCreatedat();
                            String creditbalance = response.body().getData().getUserdata().getCreditbalance();
                            String currency = response.body().getData().getUserdata().getCurrency();
                            String cname = response.body().getData().getUserdata().getCname();
                            String district = response.body().getData().getUserdata().getDistrict();
                            String dob = response.body().getData().getUserdata().getDob();
                            String drivinglicience = response.body().getData().getUserdata().getDrivinglicience();
                            String dthpackID = response.body().getData().getUserdata().getDthpackID();
                            String ekyccom = response.body().getData().getUserdata().getEkyccom();
                            String email = response.body().getData().getUserdata().getEmail();
                            String emailID = response.body().getData().getUserdata().getEmailID();
                            String fax = response.body().getData().getUserdata().getFax();
                            String flightpackID = response.body().getData().getUserdata().getFlightpackID();
                            String frim = response.body().getData().getUserdata().getFrim();
                            String gender = response.body().getData().getUserdata().getGender();
                            String groupID = response.body().getData().getUserdata().getGender();
                            String hotlepackID = response.body().getData().getUserdata().getHotlepackID();
                            String iata = response.body().getData().getUserdata().getIata();
                            String ifsccode = response.body().getData().getUserdata().getIfsccode();
                            String latutude = response.body().getData().getUserdata().getLatutude();
                            String locationType = response.body().getData().getUserdata().getLocationType();
                            String logo = response.body().getData().getUserdata().getLogo();
                            String longitude = response.body().getData().getUserdata().getLongitude();
                            String minbalance = response.body().getData().getUserdata().getMinbalance();
                            String mobile  = response.body().getData().getUserdata().getMobile();
                            String mobilepackID = response.body().getData().getUserdata().getMobilepackID();
                            String moneypackID = response.body().getData().getUserdata().getMoneypackID();
                            String name = response.body().getData().getUserdata().getName();
                            String newagency = response.body().getData().getUserdata().getNewagency();
                            String officesapce = response.body().getData().getUserdata().getOfficesapce();
                            String packID = response.body().getData().getUserdata().getPackID();
                            String pannumber = response.body().getData().getUserdata().getPannumber();
                            String panpackID = response.body().getData().getUserdata().getPanpackID();
                            String passport = response.body().getData().getUserdata().getPassport();
                            String permission = response.body().getData().getUserdata().getPermission();
                            String phoneno = response.body().getData().getUserdata().getPhoneno();
                            String photo = response.body().getData().getUserdata().getPhoto();
                            String pincode = response.body().getData().getUserdata().getPincode();
                            String population = response.body().getData().getUserdata().getPopulation();
                            String profilecomplete = response.body().getData().getUserdata().getProfilecomplete();
                            String qualification = response.body().getData().getUserdata().getQualification();
                            String railpackID = response.body().getData().getUserdata().getRailpackID();
                            String request = response.body().getData().getUserdata().getRequest();
                            String salt = response.body().getData().getUserdata().getSalt();
                            String securitization = response.body().getData().getUserdata().getSecuritization();
                            String shopname = response.body().getData().getUserdata().getShopname();
                            String shopType = response.body().getData().getUserdata().getShopType();
                            String shop_photo = response.body().getData().getUserdata().getPhoto();
                            String state = response.body().getData().getUserdata().getState();
                            String status = response.body().getData().getUserdata().getStatus();
                            String stepone = response.body().getData().getUserdata().getStepone();
                            String stepthree = response.body().getData().getUserdata().getStepthree();
                            String steptwo = response.body().getData().getUserdata().getSteptwo();
                            String turnover = response.body().getData().getUserdata().getTurnover();
                            String updatedat = response.body().getData().getUserdata().getUpdatedat();
                            String userID = response.body().getData().getUserdata().getUserID();
                            String userlimit = response.body().getData().getUserdata().getUserlimit();
                            String validitydate = response.body().getData().getUserdata().getValiditydate();
                            String voterid = response.body().getData().getUserdata().getVoterid();
                            String website = response.body().getData().getUserdata().getWebsite();
                            loginpin = response.body().getData().getUserdata().getLoginpin();
                            Pinsetdata loginpindata = new Pinsetdata(
                                    loginpin);
                            SharedPrefManagerpin.getInstance(getApplicationContext()).userping(loginpindata);
                           // Log.e("Pindata",loginpin);
                            String accessToken = response.body().getData().getToken();
                            Log.e("Pindata",accessToken);
                            Logindata logindata = new Logindata(
                                    accessToken,
                                    message1,
                                    aadharnumber,accountno,addedBy,addedOn,address,address1,addressproof,adharNo,aepspackID,agencyID,amazonpackID,apitoken,balance,bankname,bbpspackID,branchname,businessyear,bussiness,buspackID,city,contactPerson,country,countryCode,coordinator,createdat,creditbalance,currency,cname,district,dob,drivinglicience,dthpackID,ekyccom,email,emailID,fax,flightpackID,frim,gender,groupID,hotlepackID,iata,ifsccode,latutude,locationType,logo,longitude,minbalance,mobile,mobilepackID,moneypackID,name,newagency,officesapce,packID,pannumber,panpackID,passport,permission,phoneno,photo,pincode,population,profilecomplete,qualification,railpackID,request,salt,securitization,shopname,shopType,shop_photo,state,status,stepone,stepthree,steptwo,turnover,updatedat,userID,userlimit,validitydate,voterid,website,loginpin);
                            SharedPrefManagerLogin.getInstance(getApplicationContext()).userdata(logindata);
//                            String accessToken = response.body().getData().getToken();
                            LoginToken loginToken = new LoginToken(
                                    response.body().getData().getToken());
                            SharedPrefManager.getInstance(getApplicationContext()).userToken(loginToken);
//                            Log.e("accessToken",accessToken);
//                            Log.e("flag",flag);
                            flag = response.body().getData().getUserdata().getProfilecomplete();
                            if (flag.equals("0")){
                                Intent ii=new Intent(getApplicationContext(), TabActivity.class);
                                startActivity(ii);
                                Toast.makeText(Login.this,"You are Not Register Account",Toast.LENGTH_SHORT).show();
                            }
                            if (flag.equals("1")){
//                                Intent iii=new Intent(getApplicationContext(), Pinactivity.class);
//                                startActivity(iii);
//                                if (loginpin != null && !loginpin.isEmpty()){
//                                    Intent intent = new Intent(getApplicationContext(),ConfrompinActivity.class);
//                                    startActivity(intent);
//                                    Log.e("Empty","0"+loginpin);
//                                }
//                                else {
//                                    Log.e("Empty","1"+loginpin);
//                                }
                                pinset = SharedPrefManagerpin.getInstance(getApplicationContext()).getKeyPin();
                                if (pinset != null && !pinset.isEmpty()){
                                    Intent intent = new Intent(getApplicationContext(),ConfrompinActivity.class);
                                    startActivity(intent);
                                    Log.e("Empty","0"+pinset);
                                }
                                else {
                                    Intent intent = new Intent(getApplicationContext(),Pinactivity.class);
                                    startActivity(intent);
                                    Log.e("Empty","1"+pinset);
                                }
                                Toast.makeText(Login.this,"Login Successful",Toast.LENGTH_SHORT).show();
                            }
                        }
                        else if (response.body().getError().equals("true")){
                             Toast.makeText(getApplicationContext(),"Your username and password is worng",Toast.LENGTH_SHORT).show();
                                textPassword.setError(getString(R.string.error_incorrect_password));
                                textPassword.requestFocus();
                        }
                        else if (response.body().getError().equals("")){
                            //  Log.e("DATA", response.body().getMessage());
                            // Toast.makeText(getApplicationContext(),message,Toast.LENGTH_SHORT).show();
                        }
                    }
                    else {
                        Log.e("DATA", " "+response.message());
                    }

                }

                @Override
                public void onFailure(Call<LoginResult> call, Throwable t) {

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
    @Override
    protected void onStart() {
        super.onStart();
    }
    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    public void onResume(){
        super.onResume();
        System.out.println("Inside onResume");
    }

    @Override
    public void onRestart(){
        super.onRestart();
        System.out.println("Inside onReStart");
    }

}