package com.kits.kitsclick;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class Agency_detailsActivity extends AppCompatActivity {
    Toolbar toolbar;
    Spinner sp,sp1,sp2;
    String[] Country = { "Select Country" };
    String[]  State ={"Select State" };
    String[]  District = {"Select District"};
    Button nextId_agent;
    EditText phnum,otp,conformOtp;
    LinearLayout otpId,conformId;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.agency_details);
        sp = (Spinner)findViewById(R.id.sp);
        sp1 = (Spinner)findViewById(R.id.sp1);
        sp2 = (Spinner)findViewById(R.id.sp2);
        nextId_agent = (Button)findViewById(R.id.nextId_agent);
        phnum = (EditText)findViewById(R.id.phnum);
        otp = (EditText)findViewById(R.id.otp);
        conformOtp = (EditText)findViewById(R.id.conformOtp);
        otpId = (LinearLayout)findViewById(R.id.otpId);
        conformId = (LinearLayout)findViewById(R.id.conformId);
        otpId.setVisibility(View.GONE);
        conformId.setVisibility(View.GONE);
        phnum.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
             //   Log.e("count",""+String.valueOf(s.length()));
                int counts = Integer.parseInt(String.valueOf(s.length()));
                if (counts == 10) {
//                    phnum.requestFocus();
                 //   Log.e("count",""+String.valueOf(s.length()));
                    otpId.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        otp.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                //   Log.e("count",""+String.valueOf(s.length()));
                int counts = Integer.parseInt(String.valueOf(s.length()));
                if (counts == 4) {
//                    phnum.requestFocus();
                      Log.e("count",""+String.valueOf(s.length()));
                    conformId.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        nextId_agent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),AgentNext_Activity.class);
                startActivity(intent);
            }
        });
        sp.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        ArrayAdapter aa = new ArrayAdapter(this,android.R.layout.simple_spinner_item,Country);
        aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sp.setAdapter(aa);
        ///////////////////////////////////////////////////
        sp1 = (Spinner) findViewById(R.id.sp1);
        sp1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        ArrayAdapter aaa = new ArrayAdapter(this,android.R.layout.simple_spinner_item,State);
        aaa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sp1.setAdapter(aaa);

        ///////////////////////////////////////////////////
        sp2 = (Spinner) findViewById(R.id.sp2);
        sp2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
        ArrayAdapter aaaa = new ArrayAdapter(this,android.R.layout.simple_spinner_item,District);
        aaaa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sp2.setAdapter(aaaa);
        initToolBar();
    }
    public void initToolBar() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("Agency Details");
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
}
