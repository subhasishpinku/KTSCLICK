package com.kits.kitsclick;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.gson.Gson;
import com.kits.kitsclick.setget.Otpcheck;
import com.kits.kitsclick.setget.Otpvalue;

import retrofit2.Call;
import retrofit2.Callback;

public class OtpverifyActivity extends AppCompatActivity {
    Button subId;
    String otpp;
    String s ="";
    String s1 ="";
    String s2 ="";
    String s3 ="";
    String s4 ="";
    String s5 ="";
    @Override
    protected void onCreate(@Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.otp_verifytactivity);
        subId = (Button) findViewById(R.id.subId);
        EditText edit_one_mpin = (EditText)findViewById(R.id.edit_one_mpin);
        EditText edit_two_mpin = (EditText)findViewById(R.id.edit_two_mpin);
        EditText  edit_three_mpin = (EditText)findViewById(R.id.edit_three_mpin);
        EditText edit_four_mpin = (EditText)findViewById(R.id.edit_four_mpin);
        EditText  edit_five_mpin = (EditText)findViewById(R.id.edit_five_mpin);
        EditText edit_six_mpin = (EditText)findViewById(R.id.edit_six_mpin);
        edit_one_mpin.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                //  Log.e("sos",""+s);
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                //   Log.e("count",""+String.valueOf(s.length()));
                if (edit_one_mpin.getText().toString().length() == 1) {
                    edit_two_mpin.requestFocus();
                }
            }

            @Override
            public void afterTextChanged(Editable edit) {
                Log.e("sos",""+s);
                s = edit.toString();
            }
        });

        edit_two_mpin.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                //  Log.e("sos",""+s);
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                //   Log.e("count",""+String.valueOf(s.length()));
                if (edit_two_mpin.getText().toString().length() == 1) {
                    edit_three_mpin.requestFocus();
                }
            }

            @Override
            public void afterTextChanged(Editable edit) {
                Log.e("sos",""+s);
                s1 = edit.toString();
            }
        });
        edit_three_mpin.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                //  Log.e("sos",""+s);
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                //   Log.e("count",""+String.valueOf(s.length()));
                if (edit_three_mpin.getText().toString().length() == 1) {
                    edit_four_mpin.requestFocus();
                }
            }

            @Override
            public void afterTextChanged(Editable edit) {
                Log.e("sos",""+s);
                s2 = edit.toString();
            }
        });

        edit_four_mpin.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                //  Log.e("sos",""+s);
            }

            @Override
            public void onTextChanged(CharSequence ss, int start, int before, int count) {
                //   Log.e("count",""+String.valueOf(s.length()));
                if (edit_four_mpin.getText().toString().length() == 1) {
                    edit_five_mpin.requestFocus();
                }
            }

            @Override
            public void afterTextChanged(Editable edit) {
                Log.e("sos",""+s);
                s3 = edit.toString();
            }
        });
        edit_five_mpin.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                //  Log.e("sos",""+s);
            }
            @Override
            public void onTextChanged(CharSequence ss, int start, int before, int count) {
                //   Log.e("count",""+String.valueOf(s.length()));
                if (edit_five_mpin.getText().toString().length() == 1) {
                    edit_six_mpin.requestFocus();
                }
            }

            @Override
            public void afterTextChanged(Editable edit) {
                Log.e("sos",""+s);
                s4 = edit.toString();

            }
        });

        edit_six_mpin.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                //  Log.e("sos",""+s);
            }

            @Override
            public void onTextChanged(CharSequence ss, int start, int before, int count) {
                //   Log.e("count",""+String.valueOf(s.length()));
                if (edit_three_mpin.getText().toString().length() == 1) {
                    // Log.e("model",""+mModel.cc+" "+mModel.phone+" "+mModel.otp+" "+mModel.name);
                    //verifyOtp(phone);
                }
            }

            @Override
            public void afterTextChanged(Editable edit) {
                Log.e("sos",""+s);
                s5 = edit.toString();
                otpp = s+s1+s2+s3+s4+s5;
            }
        });
        subId.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                verifyotp(otpp);
            }
        });
    }
    public void verifyotp(String otpp){
        Otpvalue otpvalue = new Otpvalue();
        otpvalue.setOtp(otpp);
        Log.d("UpgradeSchool", "onClick: "+new Gson().toJson(otpvalue));
        Call<Otpcheck> call = ApiClientToken.getInstance().otpchecverify(
                otpvalue
        );
        call.enqueue(new Callback<Otpcheck>() {
            @Override
            public void onResponse(Call<Otpcheck> call, retrofit2.Response<Otpcheck> response) {
//                Log.e("DATA", " "+response.body().getStatusCode());
                if (response.isSuccessful()){
                    String message = response.body().getError();
                    Log.e("errormessage",message);
                    if (response.body().getError().equals("false")){
                        String message1 = response.body().getMessage();
                        Toast.makeText(getApplicationContext(),message1,Toast.LENGTH_SHORT).show();
                        Intent i=new Intent(getApplicationContext(), Login.class);
                        startActivity(i);

                    }
                }
                else {
                    Log.e("DATA", " "+response.message());
                }

            }

            @Override
            public void onFailure(Call<Otpcheck> call, Throwable t) {

            }
        });
    }
}
