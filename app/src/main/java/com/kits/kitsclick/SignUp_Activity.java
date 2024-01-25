package com.kits.kitsclick;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class SignUp_Activity extends AppCompatActivity {
    Spinner sp,sp1,sp2;
    String[] days = { "0", "1", "2", "3", "4" ,"5", "6", "7", "8", "9" ,"10", "11", "12", "13", "14" ,"15", "16", "17", "18" ,"19", "20" ,"21", "22", "23", "24", "25", "26", "27", "28" ,"29", "30"};
    String[]  month ={"January" ,"February" ,"March" ,"April", "May" ,"June" ,"July" ,"August" ,"September" ,"October" ,"November" ,"December" };
    String[]  year = {"2100" ,"2099" ,"2098" ,"2097" ,"2096" ,"2095" ,"2094" ,"2093" ,"2092" ,"2091"};
    Button nextId_Agen;
    Toolbar toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup_new);
        nextId_Agen = (Button)findViewById(R.id.nextId_Agen);
        sp = (Spinner)findViewById(R.id.sp);
        sp1 = (Spinner)findViewById(R.id.sp1);
        sp2 = (Spinner)findViewById(R.id.sp2);
        sp = (Spinner) findViewById(R.id.sp);
        initToolBar();
        nextId_Agen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),Agency_detailsActivity.class);
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
        ArrayAdapter aa = new ArrayAdapter(this,android.R.layout.simple_spinner_item,days);
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
        ArrayAdapter aaa = new ArrayAdapter(this,android.R.layout.simple_spinner_item,month);
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
        ArrayAdapter aaaa = new ArrayAdapter(this,android.R.layout.simple_spinner_item,year);
        aaaa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sp2.setAdapter(aaaa);

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
}
