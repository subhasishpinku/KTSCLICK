package com.kits.kitsclick;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.kits.kitsclick.setget.Logindata;
import com.kits.kitsclick.setget.Reportshowlist;
import com.kits.kitsclick.setget.Transactiondata;

import java.util.ArrayList;
import java.util.List;

public class MinstatementActivitysecomed extends AppCompatActivity {
    RecyclerView rcvv;
    List<Transactiondata> transactiondata;
    TransactionAdapter transactionAdapter;
    TextView balance;
    ImageView Img2,Img;
    @Override
    protected void onCreate(@Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.minstatement_activitysecomed);
        rcvv = (RecyclerView)findViewById(R.id.rcvv);

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
        transactiondata = new ArrayList<>();
        transactiondata.add(
                new Transactiondata(
                        "12/12",
                        "15/12/2021",
                        "AFT"
                        ,"Creadit"
                       ));
        transactiondata.add(
                new Transactiondata(
                        "12/12",
                        "15/12/2021",
                        "AFT"
                        ,"Creadit"
                ));
        transactiondata.add(
                new Transactiondata(
                        "12/12",
                        "15/12/2021",
                        "AFT"
                        ,"Creadit"
                ));
        transactiondata.add(
                new Transactiondata(
                        "12/12",
                        "15/12/2021",
                        "AFT"
                        ,"Creadit"
                ));
        transactiondata.add(
                new Transactiondata(
                        "12/12",
                        "15/12/2021",
                        "AFT"
                        ,"Creadit"
                ));
        transactiondata.add(
                new Transactiondata(
                        "12/12",
                        "15/12/2021",
                        "AFT"
                        ,"Creadit"
                ));
        transactiondata.add(
                new Transactiondata(
                        "12/12",
                        "15/12/2021",
                        "AFT"
                        ,"Creadit"
                ));
        rcvv.setLayoutManager(new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false));
        transactionAdapter = new TransactionAdapter(getApplicationContext(), transactiondata);
        rcvv.setAdapter(transactionAdapter);
    }

    public class TransactionAdapter extends RecyclerView.Adapter<TransactionAdapter.ViewHolder> {
        private Context mCtx;
        private List<Transactiondata> transactiondata;
        int listview;
        public TransactionAdapter(Context mCtx, List<Transactiondata> transactiondata) {
            this.mCtx = mCtx;
            this.transactiondata = transactiondata;
        }
        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_view_ministatement  , parent, false);
            return new ViewHolder(view);
        }
        @Override
        public void onBindViewHolder(final ViewHolder holder, int position) {
            Transactiondata trans = transactiondata.get(position);
            listview = position;
            holder.faildId.setText(trans.getType());
            holder.tv_transaction.setText(trans.getTransaction());
            holder.tv_mobile_no.setText(trans.getTrandate());
            holder.tv_adher_no.setText(trans.getTraaft());

        }
        @Override
        public int getItemCount() {
            return transactiondata.size();
        }
        public class ViewHolder extends RecyclerView.ViewHolder {
           private TextView faildId,tv_transaction,tv_mobile_no,tv_adher_no;
            public ViewHolder(View view) {
                super(view);
                faildId = (TextView) view.findViewById(R.id.faildId);
                tv_transaction = (TextView) view.findViewById(R.id.tv_transaction);
                tv_mobile_no = (TextView) view.findViewById(R.id.tv_mobile_no);
                tv_adher_no = (TextView) view.findViewById(R.id.tv_adher_no);

            }
        }
    }

}
