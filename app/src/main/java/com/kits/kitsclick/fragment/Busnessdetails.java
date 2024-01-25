package com.kits.kitsclick.fragment;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.gson.Gson;
import com.kits.kitsclick.APIClientt;
import com.kits.kitsclick.ApiClientToken;
import com.kits.kitsclick.LoginToken;
import com.kits.kitsclick.NavigationDrawerActivity;
import com.kits.kitsclick.OtpverifyActivity;
import com.kits.kitsclick.R;
import com.kits.kitsclick.SharedPrefManager;
import com.kits.kitsclick.SharedPrefManagerotp;
import com.kits.kitsclick.TabActivity;
import com.kits.kitsclick.retrofit.APIClient;
import com.kits.kitsclick.setget.Busnessdataview;
import com.kits.kitsclick.setget.Busnesstype;
import com.kits.kitsclick.setget.Country;
import com.kits.kitsclick.setget.Listdatabusness;
import com.kits.kitsclick.setget.Listdatacountry;
import com.kits.kitsclick.setget.Listdatasecuritirization;
import com.kits.kitsclick.setget.LoginResult;
import com.kits.kitsclick.setget.LoginValue;
import com.kits.kitsclick.setget.Otpcheck;
import com.kits.kitsclick.setget.Otpvalue;
import com.kits.kitsclick.setget.Packagelist;
import com.kits.kitsclick.setget.Pagecheck;
import com.kits.kitsclick.setget.PersonalDetails;
import com.kits.kitsclick.setget.Resultpersonaldetails;
import com.kits.kitsclick.setget.Securitirizationdata;
import com.kits.kitsclick.setget.Securitirizationresultdetails;
import com.kits.kitsclick.setget.Securitirizationsavedata;
import com.kits.kitsclick.util.Utility;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;

public class Busnessdetails extends Fragment {
    EditText codinatorId,turnoverId,securitId;
    Spinner sp1,sp2;
    RadioGroup ownerrentId,iataId;
    RadioButton ownerId,rentId,yesId,noId;
    ArrayList<String> busnesstypearray;
    ArrayList<String> securitirizationtypearray;
    String busnessname,securitirizationname;
    String ownerid="";
    String iata = "";
    Button nextId_agent;
    String completephotokyc= "";
    String completebusinessdetails= "";
    String completepersonaldetails ="";
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
        View rootView = inflater.inflate(R.layout.activity_busness_details, container, false);
        codinatorId = (EditText)rootView.findViewById(R.id.codinatorId);
        turnoverId  = (EditText)rootView.findViewById(R.id.turnoverId);
        ownerrentId = (RadioGroup)rootView.findViewById(R.id.ownerrentId);
        iataId = (RadioGroup)rootView.findViewById(R.id.iataId);
        ownerId = (RadioButton)rootView.findViewById(R.id.ownerId);
        rentId = (RadioButton)rootView.findViewById(R.id.rentId);
        yesId  = (RadioButton)rootView.findViewById(R.id.yesId);
        noId  = (RadioButton)rootView.findViewById(R.id.noId);
        sp1 = (Spinner)rootView.findViewById(R.id.sp1);
        sp2  = (Spinner)rootView.findViewById(R.id.sp2);
        nextId_agent = (Button)rootView.findViewById(R.id.nextId_agent);
        nextId_agent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                securitirizationsave();
            }
        });
        sp1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                busnessname = sp1.getSelectedItem().toString();
                //Toast.makeText(parent.getContext(),yearname, Toast.LENGTH_LONG).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }

        });
        sp2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                securitirizationname = sp2.getSelectedItem().toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }

        });
        ownerrentId.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
               if(checkedId == R.id.ownerId) {
                   ownerid = "owner";
                }
                else if(checkedId == R.id.rentId) {
                   ownerid = "rented";
                }
                else {
                    Toast.makeText(getContext(), "No Selected",
                            Toast.LENGTH_SHORT).show();
                }
            }
        });
        iataId.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if(checkedId == R.id.yesId) {
                    iata = "Yes";
                } else if(checkedId == R.id.noId) {
                    iata = "No";
                }
                else {
                    Toast.makeText(getContext(), "No Selected",
                            Toast.LENGTH_SHORT).show();
                }
            }
        });
        busnesstypearray = new ArrayList<>();
        securitirizationtypearray = new ArrayList<>();
        busnesstype();
        securitirization();
        pagecheck();
        return rootView;
    }
    public void busnesstype(){
        Call<Busnesstype> call = APIClientt.getInstance().busnessname(
        );
        call.enqueue(new Callback<Busnesstype>() {
            @Override
            public void onResponse(Call<Busnesstype> call, retrofit2.Response<Busnesstype> response) {
                if (response.isSuccessful()){
                    String error = response.body().getError();
                    Log.e("response",""+response.body());
                    if (error.equals("false")){
                        for (int i = 0; i <response.body().getData().getList().size(); i++) {
                            Listdatabusness listdata =  response.body().getData().getList().get(i);
                            String busnesstype = listdata.getType();
                            System.out.println("year"+busnesstype);
                            busnesstypearray.add(busnesstype);
                            if (getActivity()!=null){
                                ArrayAdapter busnesstypee= new ArrayAdapter(getActivity(),android.R.layout.simple_spinner_item,busnesstypearray);
                                busnesstypee.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                                sp1.setAdapter(busnesstypee);
                            }

                        }
//                        sp1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
//                            @Override
//                            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
//                                countryId = response.body().getData().getList().get(position).getId();
//                                Log.e("countryId",countryId);
//                            }
//
//                            @Override
//                            public void onNothingSelected(AdapterView<?> parent) {
//
//                            }
//                        });
                    }
                }
                else {
                    Log.e("DATA", " "+response.message());
                }

            }

            @Override
            public void onFailure(Call<Busnesstype> call, Throwable t) {

            }
        });
    }

    public void securitirization(){
        Call<Securitirizationdata> call = APIClientt.getInstance().securitization(
        );
        call.enqueue(new Callback<Securitirizationdata>() {
            @Override
            public void onResponse(Call<Securitirizationdata> call, retrofit2.Response<Securitirizationdata> response) {
                if (response.isSuccessful()){
                    String error = response.body().getError();
                    Log.e("response",""+response.body());
                    if (error.equals("false")){
                        for (int i = 0; i <response.body().getData().getList().size(); i++) {
                            Listdatasecuritirization listdata =  response.body().getData().getList().get(i);
                            String securitirization = listdata.getType();
                            System.out.println("year"+securitirization);
                            securitirizationtypearray.add(securitirization);
                            if (getActivity()!=null){
                                ArrayAdapter secutilization = new ArrayAdapter(getActivity(),android.R.layout.simple_spinner_item,securitirizationtypearray);
                                secutilization.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                                sp2.setAdapter(secutilization);
                            }

                        }
//                        sp1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
//                            @Override
//                            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
//                                countryId = response.body().getData().getList().get(position).getId();
//                                Log.e("countryId",countryId);
//                            }
//
//                            @Override
//                            public void onNothingSelected(AdapterView<?> parent) {
//
//                            }
//                        });
                    }
                }
                else {
                    Log.e("DATA", " "+response.message());
                }

            }

            @Override
            public void onFailure(Call<Securitirizationdata> call, Throwable t) {

            }
        });
    }

    public void securitirizationsave(){
        final String codinatorname = codinatorId.getText().toString().trim();
        final String turnover = turnoverId.getText().toString().trim();
        if (TextUtils.isEmpty(codinatorname)) {
            codinatorId.setError("Please enter Codinatorname");
            codinatorId.requestFocus();
            return;
        }
        if (TextUtils.isEmpty(turnover)) {
            turnoverId.setError("Please enter turnover");
            turnoverId.requestFocus();
            return;
        }

        if (Utility.checkConnectivity(getActivity())) {
            Securitirizationsavedata  securitirizationsavedata = new Securitirizationsavedata();
            securitirizationsavedata.setCoordinator(codinatorname);
            securitirizationsavedata.setOfficesapce(ownerid);
            securitirizationsavedata.setBussiness(sp1.getSelectedItem().toString());
            securitirizationsavedata.setIata(iata);
            securitirizationsavedata.setBusiness_year("2021");
            securitirizationsavedata.setTurnover(turnover);
            securitirizationsavedata.setSecuritization(sp2.getSelectedItem().toString());
            Log.d("UpgradeSchool", "onClick: "+new Gson().toJson(securitirizationsavedata));
            Call<Securitirizationresultdetails> call = ApiClientToken.getInstance().securitirizationsavedataCall(
                    securitirizationsavedata
            );
            call.enqueue(new Callback<Securitirizationresultdetails>() {
                @Override
                public void onResponse(Call<Securitirizationresultdetails> call, retrofit2.Response<Securitirizationresultdetails> response) {
                    //   Log.e("DATA", " "+response.body());
                    if (response.isSuccessful()){
                        String message = response.body().getMessage();
                        Log.e("message",message);
                        if (response.body().getError().equals("false")){
                            Toast.makeText(getActivity(),message,Toast.LENGTH_SHORT).show();

                        }
                        else if (response.body().getError().equals("")){
                            //  Toast.makeText(getApplicationContext(),message,Toast.LENGTH_SHORT).show();
                        }
                        else if (response.body().getError().equals("")){
                            //  Log.e("DATA", response.body().getMessage());
                            // Toast.makeText(getApplicationContext(),message,Toast.LENGTH_SHORT).show();
                        }
                    }
                    else {
                        Log.e("DATA", " "+response.message());
                    }

                }

                @Override
                public void onFailure(Call<Securitirizationresultdetails> call, Throwable t) {

                }
            });

        }
        else {
//            PopupClass.showPopUpWithTitleMessageOneButton(LoginActivity.this, "Ok", "", "Sorry, No Internet connection found, Please check your network connection", "", new PopupCallBackOneButton() {
//                @Override
//                public void onFirstButtonClick() {
//                    finish();
//                }
//            });
        }

    }
    public void pagecheck(){

        Call<Pagecheck> call = ApiClientToken.getInstance().pagecheck(
        );
        call.enqueue(new Callback<Pagecheck>() {
            @Override
            public void onResponse(Call<Pagecheck> call, retrofit2.Response<Pagecheck> response) {
                if (response.isSuccessful()){
                    String error = response.body().getError();
                    Log.e("packagelist",""+error);
                    if (error.equals("false")){
                        for (int i = 0; i <response.body().getData().getList().size(); i++) {
                            Packagelist listdata =  response.body().getData().getList().get(i);
                            String name = listdata.getName();
                            String userID = listdata.getUserID();
                            String email = listdata.getEmail();
                            String agencyID = listdata.getAgencyID();
                            String emailID = listdata.getEmailID();
                            String mobile = listdata.getMobile();
                            completephotokyc = listdata.getCompletephotokyc();
                            completebusinessdetails = listdata.getCompletebusinessdetails();
                            completepersonaldetails = listdata.getCompletepersonaldetails();
                            System.out.println("packagelist"+name+" "
                                    +userID+" "+email+" "+agencyID+" "
                                    +emailID+" "+mobile+" "+completephotokyc+" "
                                     +completebusinessdetails+" "+completepersonaldetails);
                            if(completebusinessdetails.equals("1")){
                                codinatorId.setEnabled(false);
                                turnoverId.setEnabled(false);
                                nextId_agent.setEnabled(false);
                                otpcheck();
//                                Log.e("verifyotp",""+SharedPrefManagerotp.getInstance(getActivity()).getKeyPhonenumber());
                            }

                        }
//
                    }
                }
                else {
                    Log.e("DATA", " "+response.message());
                }

            }

            @Override
            public void onFailure(Call<Pagecheck> call, Throwable t) {

            }
        });

    }
    public void otpcheck(){
//        Otpvalue  otpvalue = new Otpvalue();
//        otpvalue.setOtp(SharedPrefManagerotp.getInstance(getActivity()).getKeyPhonenumber());
//        Log.d("UpgradeSchool", "onClick: "+new Gson().toJson(otpvalue));
//        Call<Otpcheck> call = APIClient.getInstance().otpcheckvalue(
//                otpvalue
//        );
        Call<Otpcheck> call = ApiClientToken.getInstance().otpcheckvalue(
        );
        call.enqueue(new Callback<Otpcheck>() {
            @Override
            public void onResponse(Call<Otpcheck> call, retrofit2.Response<Otpcheck> response) {
//                Log.e("DATA", " "+response.body().getStatusCode());
                if (response.isSuccessful()){
                    String message = response.body().getError();
                    if (response.body().getError().equals("false")){
                        String message1 = response.body().getMessage();
                        Toast.makeText(getContext(),message1,Toast.LENGTH_SHORT).show();
                        Intent i=new Intent(getActivity(), OtpverifyActivity.class);
                        startActivity(i);

                    }
                }
                else {
                    Log.e("DATA", " "+response.message());
                }

            }

            @Override
            public void onFailure(Call<Otpcheck> call, Throwable t) {

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
