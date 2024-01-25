package com.kits.kitsclick;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputEditText;
import com.kits.kitsclick.setget.Logindata;

public class ConfrompinActivity extends AppCompatActivity {
    TextInputEditText comfrompinId;
    String pinset;
    TextView nameId,logpass;
    String name,token;
    @Override
    protected void onCreate(@Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.comfrom_pin);
        pinset = SharedPrefManagerpin.getInstance(getApplicationContext()).getKeyPin();
        token = SharedPrefManagerLogin.getInstance(getApplicationContext()).getKeyToken();
        LoginToken loginToken = new LoginToken(
                token);
        SharedPrefManager.getInstance(getApplicationContext()).userToken(loginToken);
        comfrompinId = (TextInputEditText)findViewById(R.id.comfrompinId);
        nameId = (TextView)findViewById(R.id.nameId);
        logpass = (TextView)findViewById(R.id.logpass);
        logpass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPrefManagerLogin.getInstance(getApplicationContext()).logout();
                SharedPrefManagerpin.getInstance(getApplicationContext()).logout();
                SharedPrefManagerLogin.getInstance(getApplicationContext()).clear();
                SharedPrefManagerpin.getInstance(getApplicationContext()).clear();
//                Intent intent = new Intent(getApplicationContext(), Login.class);
//                startActivity(intent);
            }
        });
        Logindata logindata = SharedPrefManagerLogin.getInstance(getApplicationContext()).getLogindata();
        name = String.valueOf(logindata.getName());
        nameId.setText(name);
        comfrompinId.addTextChangedListener(new TextWatcher() {

            @Override
            public void afterTextChanged(Editable editable) {
                boolean temp=true;
              String s3 = editable.toString();
                pinset = SharedPrefManagerpin.getInstance(getApplicationContext()).getKeyPin();
              //  Logindata logindata = SharedPrefManagerLogin.getInstance(getApplicationContext()).getLogindata();
              //  pinset = String.valueOf(logindata.getLoginpin());
                Log.e("Pindata",String.valueOf(logindata.getLoginpin()));
                if (editable.toString().length() == 4) {
                    if (!s3.equals(pinset)) {
                        Toast.makeText(ConfrompinActivity.this, "Pin Not matching", Toast.LENGTH_SHORT).show();
                        temp = false;
                        return;
                    } else {
                        Intent intent = new Intent(getApplicationContext(), NavigationDrawerActivity.class);
                        startActivity(intent);
                    }
                }
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) { }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }
        });
    }
}
