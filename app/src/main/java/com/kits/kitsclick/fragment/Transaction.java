package com.kits.kitsclick.fragment;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.kits.kitsclick.APIClientt;
import com.kits.kitsclick.ApiClientToken;
import com.kits.kitsclick.Catagoryactivity;
import com.kits.kitsclick.R;
import com.kits.kitsclick.Reportactivity;
import com.kits.kitsclick.setget.Distric;
import com.kits.kitsclick.setget.Report;
import com.kits.kitsclick.setget.Reportdataview;
import com.kits.kitsclick.setget.Reportvalue;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;

public class Transaction extends Fragment {
    RecyclerView rv;
    List<Report> mReportList;
    ReportAdapter reportAdapter;
    ArrayList<Report> arrlist = new ArrayList<Report>();
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }
    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);

    }
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.activity_transaction, container, false);
        rv = (RecyclerView)rootView.findViewById(R.id.rv);
        mReportList = new ArrayList<>();
        rv.setHasFixedSize(true);
        rv.setLayoutManager(new LinearLayoutManager(getContext()));
        Reportdata();
        return rootView;
    }

    public void Reportdata(){
//        Call<Reportvalue> call = ApiClientToken.getInstance().transation(
//        );
        Call<Reportvalue> call = ApiClientToken.getInstance().transation("transaction");
        call.enqueue(new Callback<Reportvalue>() {
            @Override
            public void onResponse(Call<Reportvalue> call, retrofit2.Response<Reportvalue> response) {
                if (response.isSuccessful()){
                    String error = response.body().getError();
                    Log.e("responsetransaction",""+response.body().getError());
                    if (error.equals("true")){
                        for (int i = 0; i <response.body().getData().size(); i++) {
                            Reportdataview listdata =  response.body().getData().get(i);
                            String name = listdata.getName();
                            String dec = listdata.getDec();
                            String images= listdata.getImage();
                            Log.e("responsetransaction",name+" "+dec);
                            mReportList.add(new Report(
                                    name,
                                    dec,
                                    images
                            ));
                            arrlist.addAll(mReportList);
                        }
                        reportAdapter = new ReportAdapter(getContext(), mReportList);
                        rv.setAdapter(reportAdapter);
                    }
                }
                else {
                    Log.e("DATA", " "+response.message());
                }

            }

            @Override
            public void onFailure(Call<Reportvalue> call, Throwable t) {

            }
        });
    }

    public class ReportAdapter extends RecyclerView.Adapter<ReportAdapter.ViewHolder> {
        private Context mCtx;
        private List<Report> mReportList;
        int listview;
        public ReportAdapter(Context mCtx, List<Report> mReportList) {
            this.mCtx = mCtx;
            this.mReportList = mReportList;
        }
        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_view_report_adapter  , parent, false);
            return new ViewHolder(view);
        }
        @Override
        public void onBindViewHolder(final ViewHolder holder, int position) {
            Report report = mReportList.get(position);
            listview = position;
            holder.tv_name.setText(report.getName());
            holder.tv_statement.setText(report.getStatement());
            Glide.with(mCtx)
                    .load(report.getImage())
                    .into(holder.Img);
            holder.cv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String name = report.getName();
                    Log.e("Data",name);
                    Intent intent = new Intent(mCtx, Reportactivity.class);
                    Bundle bundle_edit  =   new Bundle();
                    bundle_edit.putString("name",name);
                    intent.putExtras(bundle_edit);
                    startActivity(intent);

                }
            });
        }
        @Override
        public int getItemCount() {
            return mReportList.size();
        }
        public class ViewHolder extends RecyclerView.ViewHolder {
            private ImageView Img;
            private TextView tv_name,tv_statement;
            private CardView cv;
            public ViewHolder(View view) {
                super(view);
                Img = (ImageView) view.findViewById(R.id.Img);
                tv_name = (TextView) view.findViewById(R.id.tv_name);
                tv_statement = (TextView) view.findViewById(R.id.tv_statement);
                cv = (CardView) view.findViewById(R.id.cv);

            }
        }
    }
    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
    }

    @Override
    public void onStop() {
        super.onStop();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

//    @Override
//    public void onDetach() {
//        super.onDetach();
//
//    }



}

