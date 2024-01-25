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
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.gson.Gson;
import com.kits.kitsclick.APIClientt;
import com.kits.kitsclick.ApiClientToken;
import com.kits.kitsclick.Login;
import com.kits.kitsclick.LoginToken;
import com.kits.kitsclick.R;
import com.kits.kitsclick.SharedPrefManager;
import com.kits.kitsclick.SharedPrefManagerotp;
import com.kits.kitsclick.retrofit.APIClient;
import com.kits.kitsclick.setget.Country;
import com.kits.kitsclick.setget.Days;
import com.kits.kitsclick.setget.Distric;
import com.kits.kitsclick.setget.ListdataMonth;
import com.kits.kitsclick.setget.Listdatacountry;
import com.kits.kitsclick.setget.Listdataday;
import com.kits.kitsclick.setget.Listdatadistric;
import com.kits.kitsclick.setget.Listdatastate;
import com.kits.kitsclick.setget.Month;
import com.kits.kitsclick.setget.Otptoken;
import com.kits.kitsclick.setget.Packagelist;
import com.kits.kitsclick.setget.Pagecheck;
import com.kits.kitsclick.setget.PersonalDetails;
import com.kits.kitsclick.setget.RegistrationValue;
import com.kits.kitsclick.setget.Registrationresult;
import com.kits.kitsclick.setget.Resultpersonaldetails;
import com.kits.kitsclick.setget.State;
import com.kits.kitsclick.setget.Yearsetget;
import com.kits.kitsclick.setget.Listdata;
import com.kits.kitsclick.util.Utility;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;

public class Personaldetails extends Fragment {
    EditText agencyId,addresID,addressIDD,pincodeId,phonenumberId;
    Spinner sp,sp1,sp2,sp3,sp4,sp6;
    private List<String> listdata = new ArrayList<>();
    String yearname,monthname,daysname,countryname,statename,districname;
    ArrayList<String> year;
    ArrayList<String> month;
    ArrayList<String> days;
    ArrayList<String> country;
    ArrayList<String> state;
    ArrayList<String> distric;
    private String countryId="";
    private String stateId="";
    private String DistricId="";
    Button nextId_Agen;
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
        View rootView = inflater.inflate(R.layout.activity_personal, container, false);
        sp = (Spinner)rootView.findViewById(R.id.sp);
        sp1 = (Spinner)rootView.findViewById(R.id.sp1);
        sp2 = (Spinner)rootView.findViewById(R.id.sp2);
        sp3 = (Spinner)rootView.findViewById(R.id.sp3);
        sp4 = (Spinner)rootView.findViewById(R.id.sp4);
        sp6 = (Spinner)rootView.findViewById(R.id.sp6);
       nextId_Agen = (Button)rootView.findViewById(R.id.nextId_Agen);
        addresID = (EditText)rootView.findViewById(R.id.addresID);
        addressIDD = (EditText)rootView.findViewById(R.id.addressIDD);
        pincodeId = (EditText)rootView.findViewById(R.id.pincodeId);
        phonenumberId = (EditText)rootView.findViewById(R.id.phonenumberId);
        nextId_Agen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Otptoken loginToken = new Otptoken(
//                        phonenumberId.getText().toString());
//                SharedPrefManagerotp.getInstance(getActivity()).userphoneno(loginToken);
                Personal();
            }
        });
        sp.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                yearname = sp.getSelectedItem().toString();
                 //Toast.makeText(parent.getContext(),yearname, Toast.LENGTH_LONG).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }

        });

        sp1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                monthname = sp1.getSelectedItem().toString();
                // Toast.makeText(parent.getContext(),"OFF"+disName+" "+" "+distID, Toast.LENGTH_LONG).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }

        });
        sp2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                daysname = sp2.getSelectedItem().toString();
                // Toast.makeText(parent.getContext(),"OFF"+disName+" "+" "+distID, Toast.LENGTH_LONG).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }

        });

        sp3.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                countryname = sp3.getSelectedItem().toString();
               // String cnty_id = (String) parent.getItemAtPosition(position);
                //Toast.makeText(parent.getContext(),countryname, Toast.LENGTH_LONG).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }

        });
        sp4.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                statename = sp4.getSelectedItem().toString();
                // String cnty_id = (String) parent.getItemAtPosition(position);
                //Toast.makeText(parent.getContext(),cnty_id, Toast.LENGTH_LONG).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }

        });
        sp6.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                districname = sp6.getSelectedItem().toString();
                // String cnty_id = (String) parent.getItemAtPosition(position);
                //Toast.makeText(parent.getContext(),cnty_id, Toast.LENGTH_LONG).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }

        });
        year = new ArrayList<>();
        month = new ArrayList<>();
        days = new ArrayList<>();
        country = new ArrayList<>();
        state = new ArrayList<>();
        distric = new ArrayList<>();
        year();
        month();
        day();
        country();
        State();
        pagecheck();
        return rootView;
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

    public void Personal(){
     //   final String agency = agencyId.getText().toString().trim();
        final String address = addresID.getText().toString().trim();
        final String addresss = addressIDD.getText().toString().trim();
        final String pincode = pincodeId.getText().toString().trim();
        final String phnumber = phonenumberId.getText().toString().trim();

//        if (TextUtils.isEmpty(agency)) {
//            agencyId.setError("Please enter Agency Name");
//            agencyId.requestFocus();
//            return;
//        }
        if (TextUtils.isEmpty(address)) {
            addresID.setError("Please enter Address");
            addresID.requestFocus();
            return;
        }
        if (TextUtils.isEmpty(addresss)) {
            addressIDD.setError("Please enter Address");
            addressIDD.requestFocus();
            return;
        }
        if (TextUtils.isEmpty(pincode)) {
            pincodeId.setError("Please enter Pincode");
            pincodeId.requestFocus();
            return;
        }
        if (TextUtils.isEmpty(phnumber)) {
            phonenumberId.setError("Please enter Phonenumber");
            phonenumberId.requestFocus();
            return;
        }
        if (Utility.checkConnectivity(getActivity())) {
            PersonalDetails personalDetails = new PersonalDetails();
            personalDetails.setAddress1(address);
            personalDetails.setAddress2(addresss);
            personalDetails.setPincode(pincode);
            personalDetails.setPhoneno(phnumber);
            personalDetails.setCountry(sp3.getSelectedItem().toString());
            personalDetails.setState(stateId);
            personalDetails.setDistrict(DistricId);
            personalDetails.setCity(DistricId);
            personalDetails.setYear(yearname);
            personalDetails.setMonth(monthname);
            personalDetails.setDay(daysname);
            Log.d("UpgradeSchool", "onClick: "+new Gson().toJson(personalDetails));
            Call<Resultpersonaldetails> call = ApiClientToken.getInstance().personalDetailscall(
                    personalDetails
            );
            call.enqueue(new Callback<Resultpersonaldetails>() {
                @Override
                public void onResponse(Call<Resultpersonaldetails> call, retrofit2.Response<Resultpersonaldetails> response) {
             //   Log.e("DATA", " "+response.body());
                    if (response.isSuccessful()){
                        String message = response.body().getMessage();
                        Log.e("message",message);
                        if (response.body().getError().equals("false")){
                            Otptoken loginToken = new Otptoken(
                                    phnumber);
                            SharedPrefManagerotp.getInstance(getActivity()).userphoneno(loginToken);
                            Intent intent = new Intent(getContext(), Busnessdetails.class);
                            getContext().startActivity(intent);
                            getActivity().finish();
                            Toast.makeText(getActivity(),message,Toast.LENGTH_SHORT).show();

                        }
                        else if (response.body().getError().equals("true")){
                            Toast.makeText(getActivity(),message,Toast.LENGTH_SHORT).show();
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
                public void onFailure(Call<Resultpersonaldetails> call, Throwable t) {

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

    public void year(){
            Call<Yearsetget> call = APIClientt.getInstance().year(
            );
            call.enqueue(new Callback<Yearsetget>() {
                @Override
                public void onResponse(Call<Yearsetget> call, retrofit2.Response<Yearsetget> response) {
                    if (response.isSuccessful()){
                        String error = response.body().getError();
                        Log.e("response",""+response.body());
                        if (error.equals("false")){
                            for (int i = 0; i <response.body().getData().getList().size(); i++) {
                                Listdata  listdata =  response.body().getData().getList().get(i);
                                 String yearname = listdata.getYear();
                                 System.out.println("year"+yearname);
                                 year.add(yearname);
                                ArrayAdapter aa = new ArrayAdapter(getActivity(),android.R.layout.simple_spinner_item,year);
                                aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                                sp.setAdapter(aa);
                            }
                        }

                    }
                    else {
                        Log.e("DATA", " "+response.message());
                    }

                }

                @Override
                public void onFailure(Call<Yearsetget> call, Throwable t) {

                }
            });


        }

    public void month(){
        Call<Month> call = APIClientt.getInstance().month(
        );
        call.enqueue(new Callback<Month>() {
            @Override
            public void onResponse(Call<Month> call, retrofit2.Response<Month> response) {
                if (response.isSuccessful()){
                    String error = response.body().getError();
                    Log.e("response",""+response.body());
                    if (error.equals("false")){
                        for (int i = 0; i <response.body().getData().getList().size(); i++) {
                            ListdataMonth listdata =  response.body().getData().getList().get(i);
                            String monthname = listdata.getMonth();
                            System.out.println("year"+monthname);
                            month.add(monthname);
                            ArrayAdapter aa = new ArrayAdapter(getActivity(),android.R.layout.simple_spinner_item,month);
                            aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                            sp1.setAdapter(aa);
                        }
                    }

                }
                else {
                    Log.e("DATA", " "+response.message());
                }

            }

            @Override
            public void onFailure(Call<Month> call, Throwable t) {

            }
        });
    }

    public void day(){
        Call<Days> call = APIClientt.getInstance().day(
        );
        call.enqueue(new Callback<Days>() {
            @Override
            public void onResponse(Call<Days> call, retrofit2.Response<Days> response) {
                if (response.isSuccessful()){
                    String error = response.body().getError();
                    Log.e("response",""+response.body());
                    if (error.equals("false")){
                        for (int i = 0; i <response.body().getData().getList().size(); i++) {
                            Listdataday listdata =  response.body().getData().getList().get(i);
                            String daysname = listdata.getDay();
                            System.out.println("year"+daysname);
                            days.add(daysname);
                            ArrayAdapter aa = new ArrayAdapter(getActivity(),android.R.layout.simple_spinner_item,days);
                            aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                            sp2.setAdapter(aa);
                        }
                    }

                }
                else {
                    Log.e("DATA", " "+response.message());
                }

            }

            @Override
            public void onFailure(Call<Days> call, Throwable t) {

            }
        });
    }

    public void country(){
        Call<Country> call = APIClientt.getInstance().countryname(
        );
        call.enqueue(new Callback<Country>() {
            @Override
            public void onResponse(Call<Country> call, retrofit2.Response<Country> response) {
                if (response.isSuccessful()){
                    String error = response.body().getError();
                    Log.e("response",""+response.body());
                    if (error.equals("false")){
                        for (int i = 0; i <response.body().getData().getList().size(); i++) {
                            Listdatacountry listdata =  response.body().getData().getList().get(i);
                            String daysname = listdata.getName();
                            System.out.println("year"+daysname);
                            country.add(daysname);
                            ArrayAdapter aa = new ArrayAdapter(getActivity(),android.R.layout.simple_spinner_item,country);
                            aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                            sp3.setAdapter(aa);

                        }

                        sp3.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                            @Override
                            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                                 countryId = response.body().getData().getList().get(position).getId();
                                 Log.e("countryId",countryId);
                            }

                            @Override
                            public void onNothingSelected(AdapterView<?> parent) {

                            }
                        });
                    }
                }
                else {
                    Log.e("DATA", " "+response.message());
                }

            }

            @Override
            public void onFailure(Call<Country> call, Throwable t) {

            }
        });
    }

    public void State(){
        Call<State> call = APIClientt.getInstance().statename(
        );
        call.enqueue(new Callback<State>() {
            @Override
            public void onResponse(Call<State> call, retrofit2.Response<State> response) {
                if (response.isSuccessful()){
                    String error = response.body().getError();
                    Log.e("response",""+response.body());
                    if (error.equals("false")){
                        for (int i = 0; i <response.body().getData().getList().size(); i++) {
                            Listdatastate listdata =  response.body().getData().getList().get(i);
                            String daysname = listdata.getName();
                            System.out.println("year"+daysname);
                            state.add(daysname);
                            ArrayAdapter aa = new ArrayAdapter(getActivity(),android.R.layout.simple_spinner_item,state);
                            aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                            sp4.setAdapter(aa);

                        }

                        sp4.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                            @Override
                            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                                stateId = response.body().getData().getList().get(position).getId();
                                Log.e("stateId",stateId);
                                distric.clear();
                                Distric(stateId);
                            }

                            @Override
                            public void onNothingSelected(AdapterView<?> parent) {

                            }
                        });
                    }
                }
                else {
                    Log.e("DATA", " "+response.message());
                }

            }

            @Override
            public void onFailure(Call<State> call, Throwable t) {

            }
        });
    }
    public void Distric(String stateId){

//        Call<Distric> call = APIClientt.getInstance().districname(stateId)(
//        );
        Call<Distric> call = APIClientt.getInstance().districname(stateId);
        call.enqueue(new Callback<Distric>() {
            @Override
            public void onResponse(Call<Distric> call, retrofit2.Response<Distric> response) {
                if (response.isSuccessful()){
                    String error = response.body().getError();
                    Log.e("response",""+response.body());
                    if (error.equals("false")){
                        for (int i = 0; i <response.body().getData().getList().size(); i++) {
                            Listdatadistric listdata =  response.body().getData().getList().get(i);
                            String daysname = listdata.getName();
                            System.out.println("year"+daysname);
                            distric.add(daysname);
                            ArrayAdapter aa = new ArrayAdapter(getActivity(),android.R.layout.simple_spinner_item,distric);
                            aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                            sp6.setAdapter(aa);

                        }

                        sp6.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                            @Override
                            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                                DistricId = response.body().getData().getList().get(position).getId();
                                Log.e("DistricId",DistricId);
                            }

                            @Override
                            public void onNothingSelected(AdapterView<?> parent) {

                            }
                        });
                    }
                }
                else {
                    Log.e("DATA", " "+response.message());
                }

            }

            @Override
            public void onFailure(Call<Distric> call, Throwable t) {
                t.getMessage();
            }
        });

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
                            if (completepersonaldetails.equals("1")){
                                Log.e("completepersonaldetails",completepersonaldetails);
                                addresID.setEnabled(false);
                                addressIDD.setEnabled(false);
                                pincodeId.setEnabled(false);
                                phonenumberId.setEnabled(false);
                                nextId_Agen.setEnabled(false);
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
    }


