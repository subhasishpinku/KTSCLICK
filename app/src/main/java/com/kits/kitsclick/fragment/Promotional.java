package com.kits.kitsclick.fragment;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.kits.kitsclick.APIClientt;
import com.kits.kitsclick.ApiClientToken;
import com.kits.kitsclick.R;
import com.kits.kitsclick.setget.Listdatastate;
import com.kits.kitsclick.setget.Notificationservice;
import com.kits.kitsclick.setget.State;

import retrofit2.Call;
import retrofit2.Callback;

public class Promotional extends Fragment {
    TextView noti;
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
        View rootView = inflater.inflate(R.layout.activity_promotional, container, false);
        noti= (TextView)rootView.findViewById(R.id.noti);
        noti();
        return rootView;
    }
    public void noti(){
        Call<Notificationservice> call = ApiClientToken.getInstance().getnotification(
        );
        call.enqueue(new Callback<Notificationservice>() {
            @Override
            public void onResponse(Call<Notificationservice> call, retrofit2.Response<Notificationservice> response) {
                if (response.isSuccessful()){
                    String error = response.body().getError();
                    Log.e("response",""+response.body());
                    if (error.equals("false")){
                      String notification = response.body().getData().getNotice();
                        noti.setText(notification);
                     }
                }
                else {
                    Log.e("DATA", " "+response.message());
                }

            }

            @Override
            public void onFailure(Call<Notificationservice> call, Throwable t) {

            }
        });
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
