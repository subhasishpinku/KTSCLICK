package com.kits.kitsclick;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputEditText;
import com.kits.kitsclick.setget.Logindata;

public class NotsupportedActivity extends AppCompatActivity {
    TextView tv_bill_amountID,tv_bill_dateId,tv_bill_numberID,
            tv_bill_dueID,tv_bill_periodID,tv_payment_lateID,tv_ConvenienceFeeID,
            tv_Customer_NameID,tv_Fastag_BalanceID,tv_Maximum_RechargeID,balance;
    ImageView Img2;
    TextInputEditText mobileno;
    @Override
    protected void onCreate(@Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notsupported);
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

        mobileno = (TextInputEditText)findViewById(R.id.mobileno);
        Img2 = (ImageView)findViewById(R.id.Img2);
        tv_bill_amountID = (TextView)findViewById(R.id.tv_bill_amountID);
        tv_bill_dateId = (TextView)findViewById(R.id.tv_bill_dateId);
        tv_bill_numberID = (TextView)findViewById(R.id.tv_bill_numberID);
        tv_bill_dueID = (TextView)findViewById(R.id.tv_bill_dueID);
        tv_bill_periodID = (TextView)findViewById(R.id.tv_bill_periodID);
        tv_payment_lateID = (TextView)findViewById(R.id.tv_payment_lateID);
        tv_ConvenienceFeeID = (TextView)findViewById(R.id.tv_ConvenienceFeeID);
        tv_Customer_NameID = (TextView)findViewById(R.id.tv_Customer_NameID);
        tv_Fastag_BalanceID = (TextView)findViewById(R.id.tv_Fastag_BalanceID);
        tv_Maximum_RechargeID = (TextView)findViewById(R.id.tv_Maximum_RechargeID);
        tv_bill_amountID.setText(amount);
        mobileno.setText(amount);
//        tv_bill_dateId.setText("NA");
//        tv_bill_numberID.setText("NA");
//        tv_bill_dueID.setText("NA");
//        tv_bill_periodID.setText("NA");
      //  tv_payment_lateID.setText("NA");
//        tv_ConvenienceFeeID.setText("NA");
        tv_Customer_NameID.setText(customername);
   //     tv_Fastag_BalanceID.setText("NA");
   //     tv_Maximum_RechargeID.setText("NA");
        if(conveniencefees.equals(null)||conveniencefees.isEmpty()|| conveniencefees.equals("")){

        }
        else {
            tv_ConvenienceFeeID.setText(conveniencefees);

        }
        if(billdate.equals(null)||billdate.isEmpty()|| billdate.equals("")){

        }
        else {
            tv_bill_dateId.setText(billdate);

        }
        if(billperiod.equals(null)||billperiod.isEmpty()|| billperiod.equals("")){

        }
        else {
            tv_bill_periodID.setText(billperiod);

        }
        if(billnumber.equals(null)||billnumber.isEmpty()|| billnumber.equals("")){

        }
        else {
            tv_bill_numberID.setText(billnumber);

        }
        if(billduedate.equals(null)||billduedate.isEmpty()|| billduedate.equals("")){

        }
        else {
            tv_bill_dueID.setText(billduedate);

        }
        balance = (TextView)findViewById(R.id.balance);
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




    }
}
