package com.kits.kitsclick;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.kits.kitsclick.setget.Collect_Payment;
import com.kits.kitsclick.setget.Earnmore;
import com.kits.kitsclick.setget.Logindata;
import com.kits.kitsclick.setget.Payinlist;

import java.util.ArrayList;
import java.util.List;

public class PayinActivity extends AppCompatActivity {
    RecyclerView rcv;
    List<Payinlist> payinlist;
    Paylist paylistadapter;
    RadioGroup rg;
    RadioButton upi,cash,cheqe,imps,gpay,ppay,rtgs,creditcard,debitcard,netbanking,neft,transfer;
    String str="";
    Button contatue;
    EditText ed_amount;
    TextView balance;
    ImageView Img2,Img;
    @Override
    protected void onCreate(@Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.payin_activity);
        rcv = (RecyclerView) findViewById(R.id.rcv);
        upi = (RadioButton)findViewById(R.id.upi);
        cash = (RadioButton)findViewById(R.id.cash);
        cheqe = (RadioButton)findViewById(R.id.cheqe);
        imps = (RadioButton)findViewById(R.id.imps);
        gpay = (RadioButton)findViewById(R.id.gpay);
        ppay = (RadioButton)findViewById(R.id.ppay);
        rtgs = (RadioButton)findViewById(R.id.rtgs);
        creditcard = (RadioButton)findViewById(R.id.creditcard);
        debitcard = (RadioButton)findViewById(R.id.debitcard);
        netbanking = (RadioButton)findViewById(R.id.netbanking);
        neft = (RadioButton)findViewById(R.id.neft);
        transfer = (RadioButton)findViewById(R.id.transfer);
        contatue = (Button)findViewById(R.id.contatue);
        ed_amount = (EditText)findViewById(R.id.ed_amount);
        balance = (TextView) findViewById(R.id.balance);
        Img2 = (ImageView)findViewById(R.id.Img2);
        Img = (ImageView)findViewById(R.id.Img);
        Img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),NavigationDrawerActivity.class);
                startActivity(intent);
            }
        });
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
        contatue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String amount = ed_amount.getText().toString().trim();
                if (TextUtils.isEmpty(amount)) {
                    ed_amount.setError("Please enter Amount");
                    ed_amount.requestFocus();
                    return;
                }

                if (str.equals("UPI Selected")){
                    Intent intent = new Intent(getApplicationContext(),UpiActivity.class);
                    startActivity(intent);
                }
                if (str.equals("Cash Selected")){
                    Intent intent = new Intent(getApplicationContext(),PayinofflineActivity.class);
                    startActivity(intent);

                }
                if (str.equals("Cheqe Selected")){
                    Intent intent = new Intent(getApplicationContext(),PayinofflineActivity.class);
                    startActivity(intent);
                }
                if (str.equals("IMPS Selected")){

                }
                if (str.equals("Google Pay Selected")){

                }
                if (str.equals("Phone Pay Selected")){

                }
                if (str.equals("RTGS Selected")){
                    Intent intent = new Intent(getApplicationContext(),PayinofflineActivity.class);
                    startActivity(intent);
                }
                if (str.equals("Credit Card Selected")){
                    Intent intent = new Intent(getApplicationContext(),Creditcarddebitcard_Activity.class);
                    intent.putExtra("amount", amount);
                    startActivity(intent);
                }
                if (str.equals("Debit Card Selected")){
//                    Intent intent = new Intent(getApplicationContext(),Creditdebit_Activity.class);
                    Intent intent = new Intent(getApplicationContext(),Creditcarddebitcard_Activity.class);
                    intent.putExtra("amount", amount);
                    startActivity(intent);
                }
                if (str.equals("Net Banking")){

                }
                if (str.equals("NEFT Selected")){

                }
                if (str.equals("Transfer Selected")){

                }

            }
        });
        payinlist = new ArrayList<>();
        payinlist.add(
                new Payinlist(
                        "UPI","1"));
        payinlist.add(
                new Payinlist(

                        "Cash","2"));
        payinlist.add(
                new Payinlist(

                        "Cheque","3"));
        payinlist.add(
                new Payinlist(

                        "IMPS","4"));
        payinlist.add(
                new Payinlist(

                        "Google Pay","5"));
        payinlist.add(
                new Payinlist(

                        "Phone Pay","6"));
        payinlist.add(
                new Payinlist(

                        "RTGS","7"));
        payinlist.add(
                new Payinlist(

                        "Credit Card","8"));
        payinlist.add(
                new Payinlist(

                        "Debit Card","9"));
        payinlist.add(
                new Payinlist(

                        "Net Banking","10"));
        payinlist.add(
                new Payinlist(

                        "NEFT","11"));
        payinlist.add(
                new Payinlist(

                        "Transfer","12"));
//        payinlist.add(
//                new Payinlist(
//
//                        "EDC Machine","10"));
//        payinlist.add(
//                new Payinlist(
//
//                        "Pay Through Indifi","11"));
//        payinlist.add(
//                new Payinlist(
//
//                        "Pay Through UPI","12"));
        rcv.setHasFixedSize(true);
        rcv.setLayoutManager(new LinearLayoutManager(this));
        paylistadapter = new Paylist(getApplication(), payinlist);
        rcv.setAdapter(paylistadapter);
    }

    public void onRadioButtonClicked(View view) {
        boolean checked = ((RadioButton) view).isChecked();
        // Check which radio button was clicked
        switch(view.getId()) {
            case R.id.upi:
                if(checked)
                    str = "UPI Selected";
                break;
            case R.id.cash:
                if(checked)
                    str = "Cash Selected";
                break;
            case R.id.cheqe:
                if(checked)
                    str = "Cheqe Selected";
                break;
            case R.id.imps:
                if(checked)
                    str = "IMPS Selected";
                break;

            case R.id.gpay:
                if(checked)
                    str = "Google Pay Selected";
                break;

            case R.id.ppay:
                if(checked)
                    str = "Phone Pay Selected";
                break;
            case R.id.rtgs:
                if(checked)
                    str = "RTGS Selected";
                break;
            case R.id.creditcard:
                if(checked)
                    str = "Credit Card Selected";
                break;
            case R.id.debitcard:
                if(checked)
                    str = "Debit Card Selected";
                break;

            case R.id.netbanking:
                if(checked)
                    str = "Net Banking Selected";
                break;

            case R.id.neft:
                if(checked)
                    str = "NEFT Selected";
                break;

            case R.id.transfer:
                if(checked)
                    str = "Transfer Selected";
                break;
        }
        Toast.makeText(getApplicationContext(), str, Toast.LENGTH_SHORT).show();
    }
    public class Paylist extends RecyclerView.Adapter<Paylist.ViewHolder> {
        private Context mCtx;
        private List<Payinlist> payinlist;
        int listview;
        public Paylist(Context mCtx, List<Payinlist> payinlist) {
            this.mCtx = mCtx;
            this.payinlist = payinlist;
        }
        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.payin_list, parent, false);
            return new ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(final ViewHolder holder, int position) {
            listview = position;
            Payinlist payinlists = payinlist.get(position);
            holder.tv_name.setText(payinlists.getName());

            holder.ck_ID.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    if (isChecked) {
                        //status.set(position, true);
                        if (payinlists.getName().equals("UPI")){

                        }
                        if (payinlists.getName().equals("Cash")){
                            Intent intent = new Intent(mCtx,PayinofflineActivity.class);
                            startActivity(intent);

                        }
                        if (payinlists.getName().equals("Cheque")){

                        }
                        if (payinlists.getName().equals("IMPS")){

                        }
                        if (payinlists.getName().equals("Google Pay")){

                        }
                        if (payinlists.getName().equals("Phone Pay")){

                        }
                        if (payinlists.getName().equals("RTGS")){
                            Intent intent = new Intent(mCtx,PayinofflineActivity.class);
                            startActivity(intent);
                        }
                        if (payinlists.getName().equals("Credit Card")){

                            Intent intent = new Intent(mCtx,Creditcarddebitcard_Activity.class);
                            startActivity(intent);
                        }
                        if (payinlists.getName().equals("Debit Card")){
                            Intent intent = new Intent(mCtx,Creditdebit_Activity.class);
                            startActivity(intent);
                        }
                        if (payinlists.getName().equals("Net Banking")){

                        }
                        if (payinlists.getName().equals("NEFT")){

                        }
                        if (payinlists.getName().equals("Transfer")){

                        }

                    } else {
//                        status.set(position, false);
//                        if (!buttonView.isSelected()) {
//                            buttonView.setChecked(true);
//                            buttonView.setSelected(true);
//                        } else {
//                            buttonView.setChecked(false);
//                            buttonView.setSelected(false);
//                        }
                    }
                }
            });
        }
        @Override
        public int getItemCount() {
            return payinlist.size();
        }
        public class ViewHolder extends RecyclerView.ViewHolder {
            private TextView tv_name;
            private RadioButton ck_ID;
            public ViewHolder(View view) {
                super(view);
                tv_name = (TextView) view.findViewById(R.id.tv_name);
                ck_ID = (RadioButton) view.findViewById(R.id.ck_ID);
            }
        }
    }
}
