package com.kits.kitsclick;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class SuccessfulActivity extends AppCompatActivity {
    TextView tv_bal,tv_phone,tv_refer,tv_date;
    @Override
    protected void onCreate(@Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.successful_activity);
        Intent intent = getIntent();
        String paymentmodes = intent.getStringExtra("paymentmodes");
        String reqid = intent.getStringExtra("reqid");
        String billerId = intent.getStringExtra("billerId");
        String billername = intent.getStringExtra("billername");
        String payeenumber = intent.getStringExtra("payeenumber");
        String customernumber = intent.getStringExtra("customernumber");
        String amount = intent.getStringExtra("amount");
        String paytypee = intent.getStringExtra("paytypee");
        String customeremail = intent.getStringExtra("customeremail");
        String customername = intent.getStringExtra("customername");
        String paramname = intent.getStringExtra("paramname");
        String paramlavel = intent.getStringExtra("paramlavel");
        String conveniencefees = intent.getStringExtra("conveniencefees");
        String billdate = intent.getStringExtra("billdate");
        String billperiod = intent.getStringExtra("billperiod");
        String billnumber = intent.getStringExtra("billnumber");
        String billduedate = intent.getStringExtra("billduedate");
        tv_bal = (TextView)findViewById(R.id.tv_bal);
        tv_phone = (TextView)findViewById(R.id.tv_phone);
        tv_refer = (TextView)findViewById(R.id.tv_refer);
        tv_date = (TextView)findViewById(R.id.tv_date);
        tv_bal.setText(amount);
        tv_phone.setText("NA");
        tv_refer.setText("Ref ID: NA");
        tv_date.setText("NA");

    }
}
