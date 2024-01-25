package com.kits.kitsclick;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.kits.kitsclick.fragment.DasboardFragment;
import com.kits.kitsclick.fragment.Transaction;
import com.kits.kitsclick.setget.Collect_Payment;
import com.kits.kitsclick.setget.ListReportview;
import com.kits.kitsclick.setget.Report;
import com.kits.kitsclick.setget.Reportdataview;
import com.kits.kitsclick.setget.Reportlist;
import com.kits.kitsclick.setget.Reportshowlist;
import com.kits.kitsclick.setget.Reportvalue;
import com.kits.kitsclick.setget.Reportview;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
public class Reportactivity extends AppCompatActivity {
    RecyclerView rcv,rcvv;
    List<Reportlist> reportlistList;
    ListViewAdapter listViewAdapter;
    List<Reportshowlist> reportshowlist;
    ReportViewAdapter listReportViewAdapter;
    String name;
    ArrayList<Reportshowlist> arrlist = new ArrayList<Reportshowlist>();
    ImageView Img;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.report_activity);
        Intent intent = getIntent();
        name = intent.getStringExtra("name");
        Log.e("name",""+name);
        reportdata(name);
        rcv = (RecyclerView)findViewById(R.id.rcv);
        rcvv = (RecyclerView)findViewById(R.id.rcvv);
        Img = (ImageView)findViewById(R.id.Img);
        Img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        reportlistList = new ArrayList<>();
        reportshowlist = new ArrayList<>();
        rcvv.setHasFixedSize(true);
        rcvv.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        reportlistList.add(
                new Reportlist(
                        "1",
                        "All"));
        reportlistList.add(
                new Reportlist(
                        "1",
                        "Successful"));
        reportlistList.add(
                new Reportlist(
                        "1",
                        "Refunded"));
        reportlistList.add(
                new Reportlist(
                        "1",
                        "Pending"));
//        reportshowlist.add(
//                new Reportshowlist(
//                        "123456",
//                        "24474747",
//                        "12345",
//                        "Subhasish",
//                        "9804044940",
//                        "22/10/2021",
//                        "Jio",
//                        "ATM",
//                        "Ok"));
//        reportshowlist.add(
//                new Reportshowlist(
//                        "123456",
//                        "24474747",
//                        "12345",
//                        "Subhasish",
//                        "9804044940",
//                        "22/10/2021",
//                        "Jio",
//                        "ATM",
//                        "Ok"));
        rcv.setLayoutManager(new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.HORIZONTAL, false));
        listViewAdapter = new ListViewAdapter(getApplicationContext(), reportlistList);
        rcv.setAdapter(listViewAdapter);
//        rcvv.setLayoutManager(new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false));
//        listReportViewAdapter = new ReportViewAdapter(getApplicationContext(), reportshowlist);
//        rcvv.setAdapter(listReportViewAdapter);
    }
        public void reportdata(String name){
            Call<Reportview> call = ApiClientToken.getInstance().reportview(name);
            call.enqueue(new Callback<Reportview>() {
                @Override
                public void onResponse(Call<Reportview> call, retrofit2.Response<Reportview> response) {
                    if (response.isSuccessful()){
                        String error = response.body().getError();
                        Log.e("responsetransactionview",""+response.body().getError());
                        if (error.equals("false")){
                            String page = response.body().getData().getPage();
                            String totalpage = response.body().getData().getTotalpage();
                            String totalrecord = response .body().getData().getTotalrecord();
                            for (int i = 0; i <response.body().getData().getList().size(); i++) {
                                ListReportview listdata =  response.body().getData().getList().get(i);
                                String name = listdata.getName();
                                String agentid = listdata.getAgencyID();
                                String merchentid= listdata.getMerchantTransactionId();
                                String fqtransation = listdata.getFpTransactionId();
                                String service = listdata.getService();
                                String bankrnn = listdata.getBankRRN();
                                String balanceamount = listdata.getBalanceAmount();
                                String rqtransactiontime = listdata.getRequestTransactionTime();
                                String transamount = listdata.getTransactionAmount();
                                String transcationstatus = listdata.getTransactionStatus();
                                Log.e("responsetransactionview",name+" "
                                        +agentid+" "+merchentid+" "
                                        +fqtransation+" "+service+" "+bankrnn+" "
                                        +balanceamount+" "+rqtransactiontime+" "+transamount+" "+transcationstatus);
                                reportshowlist.add(new Reportshowlist(
                                        page,
                                        totalpage,
                                        totalrecord,name,agentid,
                                        merchentid,fqtransation,service,bankrnn,balanceamount,rqtransactiontime,transamount,transcationstatus
                                ));
                                arrlist.addAll(reportshowlist);
                            }
                            listReportViewAdapter = new ReportViewAdapter(getApplicationContext(), reportshowlist);
                            rcvv.setAdapter(listReportViewAdapter);
                        }
                    }
                    else {
                        Log.e("DATA", " "+response.message());
                    }

                }

                @Override
                public void onFailure(Call<Reportview> call, Throwable t) {

                }
            });
        }

    public class ListViewAdapter extends RecyclerView.Adapter<ListViewAdapter.ViewHolder> {
        private Context mCtx;
        private List<Reportlist> reportlistList;
        int listview;
        public ListViewAdapter(Context mCtx, List<Reportlist> reportlistList) {
            this.mCtx = mCtx;
            this.reportlistList = reportlistList;
        }
        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_view_item  , parent, false);
            return new ViewHolder(view);
        }
        @Override
        public void onBindViewHolder(final ViewHolder holder, int position) {
            Reportlist reportlist = reportlistList.get(position);
            listview = position;
            holder.list_tv.setText(reportlist.getTotalpage());

        }
        @Override
        public int getItemCount() {
            return reportlistList.size();
        }
        public class ViewHolder extends RecyclerView.ViewHolder {
            private TextView list_tv;
            public ViewHolder(View view) {
                super(view);
                list_tv = (TextView)view.findViewById(R.id.list_tv);

            }
        }
    }
    public class ReportViewAdapter extends RecyclerView.Adapter<ReportViewAdapter.ViewHolder> {
        private Context mCtx;
        private List<Reportshowlist> reportshowlist;
        int listview;
        public ReportViewAdapter(Context mCtx, List<Reportshowlist> reportshowlist) {
            this.mCtx = mCtx;
            this.reportshowlist = reportshowlist;
        }
        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_view_report  , parent, false);
            return new ViewHolder(view);
        }
        @Override
        public void onBindViewHolder(final ViewHolder holder, int position) {
            Reportshowlist reportlist = reportshowlist.get(position);
            listview = position;
            holder.trangaction_ID.setText(reportlist.getName());
            holder.referenceid_ID.setText(reportlist.getAgentid());
            holder.agencycode_ID.setText(reportlist.getMerchentid());
            holder.operatorname_ID.setText(reportlist.getFqtransation());
            holder.number_ID.setText(reportlist.getService());
            holder.rechargedate_ID.setText(reportlist.getBankrnn());
            holder.recharge_ID.setText(reportlist.getBalanceamount());
            holder.amt_ID.setText(reportlist.getRqtransactiontime());
            holder.status_ID.setText(reportlist.getTransamount());

        }
        @Override
        public int getItemCount() {
            return reportshowlist.size();
        }
        public class ViewHolder extends RecyclerView.ViewHolder {
            private TextView trangaction_ID,referenceid_ID,
                    agencycode_ID,operatorname_ID,number_ID,rechargedate_ID,recharge_ID,amt_ID,status_ID;
            public ViewHolder(View view) {
                super(view);
                trangaction_ID = (TextView)view.findViewById(R.id.trangaction_ID);
                referenceid_ID = (TextView)view.findViewById(R.id.referenceid_ID);
                agencycode_ID = (TextView)view.findViewById(R.id.agencycode_ID);
                operatorname_ID = (TextView)view.findViewById(R.id.operatorname_ID);
                number_ID = (TextView)view.findViewById(R.id.number_ID);
                rechargedate_ID = (TextView)view.findViewById(R.id.rechargedate_ID);
                recharge_ID = (TextView)view.findViewById(R.id.recharge_ID);
                amt_ID = (TextView)view.findViewById(R.id.amt_ID);
                status_ID = (TextView)view.findViewById(R.id.status_ID);

            }
        }
    }
}
