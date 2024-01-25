package com.kits.kitsclick;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatAutoCompleteTextView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.google.gson.Gson;
import com.kits.kitsclick.setget.Bill_payment;
import com.kits.kitsclick.setget.BillerDetails;
import com.kits.kitsclick.setget.Billercategory;
import com.kits.kitsclick.setget.Bpspaysetget;
import com.kits.kitsclick.setget.Bpspaysetgetvalue;
import com.kits.kitsclick.setget.Catagoryditialis;
import com.kits.kitsclick.setget.Catagoryditialissecond;
import com.kits.kitsclick.setget.Distric;
import com.kits.kitsclick.setget.ListBillerDetailsparams;
import com.kits.kitsclick.setget.ListBillerDetailsstaticparams;
import com.kits.kitsclick.setget.ListBillercategory;
import com.kits.kitsclick.setget.Listdatadistric;
import com.kits.kitsclick.setget.Logindata;
import com.kits.kitsclick.setget.Otpcheck;
import com.kits.kitsclick.setget.Otpvalue;
import com.kits.kitsclick.setget.Payparamslist;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
public class Catagoryactivity extends AppCompatActivity {
    String Itemname,imag;
    TextView tv_nmaebrand,balance;
    ImageView Img,Img2;
    AppCompatAutoCompleteTextView board_Id;
    ArrayList<String> buildrname;
    private String BuilderId = "";
  //private List<EditText> editTextList = new ArrayList<EditText>();
    List<Catagoryditialis> catagoryditialis;
    List<Catagoryditialissecond> catagoryditialissecondList;

    ArrayList<Catagoryditialis> arrlist = new ArrayList<Catagoryditialis>();
    ArrayList<Catagoryditialissecond> arrlist1 = new ArrayList<Catagoryditialissecond>();

    RecyclerView bill_catagory;
    BillpaymentItemViewAdapter4 billpaymentItemViewAdapter4;
    TextInputLayout board;
    Button btn_getbil;
    ProgressBar progressBar_cyclic;
    String lelel,buildename,max,min,staticlelel,staticbuildename;
    String value,value1;
    String name,builderid,builderreq,paytype;
    String mobile = "";
    String mobilevalue = "";
    String paramname,paramlavel;
    String message;
    @Override
    protected void onPostCreate(@Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        setContentView(R.layout.bill_payment_activity);
        tv_nmaebrand = (TextView)findViewById(R.id.tv_nmaebrand);
        balance = (TextView)findViewById(R.id.balance);
        Img = (ImageView)findViewById(R.id.Img);
        Img2 = (ImageView)findViewById(R.id.Img2);
        bill_catagory = (RecyclerView)findViewById(R.id.bill_catagory);
        board_Id = (AppCompatAutoCompleteTextView) findViewById(R.id.board_Id);
        board = (TextInputLayout)findViewById(R.id.board);
        btn_getbil = (Button)findViewById(R.id.btn_getbil);
        progressBar_cyclic = (ProgressBar)findViewById(R.id.progressBar_cyclic);
        progressBar_cyclic.setVisibility(View.GONE);
        Intent intent = getIntent();
        Itemname = intent.getStringExtra("Itemname");
        imag = intent.getStringExtra("imag");
        Log.e("Itemname",""+Itemname+imag);
        buildrname = new ArrayList<String>();
        bulsecatagory(Itemname);
        Picasso.with(this).load("https://www.b2bktsclickworld.com/assets/icon/1637922956_Associati.png").placeholder(R.drawable.dth).into(Img);
        tv_nmaebrand.setText(Itemname);
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
        btn_getbil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    for (int i = 0; i < catagoryditialis.size(); i++) {
                        int maxlength = Integer.parseInt(catagoryditialis.get(i).getMinlength());
                        int minlength = Integer.parseInt(catagoryditialis.get(i).getMaxlength());
                        Log.e("Printlength", " " + maxlength + " " + minlength);
                        if ((catagoryditialis.get(i).getValue() != null && catagoryditialis.get(i).getValue().equals("")) ||
                                catagoryditialis.get(i).getValue() == null
                        ) {
                            Toast.makeText(getApplicationContext(), " Invaild " + catagoryditialis.get(i).getParamlevel(), Toast.LENGTH_SHORT).show();
//                     if (catagoryditialis.get(i).getParamlevel().equalsIgnoreCase("Amount")){
//
//                     }
//                     if (catagoryditialis.get(i).getParamlevel().equalsIgnoreCase("Name")){
//
//                     }
//                     if (catagoryditialis.get(i).getParamlevel().equalsIgnoreCase("Email Address")){
//
//                     }
//                     if (catagoryditialis.get(i).getParamlevel().equalsIgnoreCase("Landline Number with STD code")){
//
//                     }
                            return;
                        } else if ((catagoryditialis.get(i).getValue().length() < minlength) ||
                                (catagoryditialis.get(i).getValue().length() > maxlength)) {
                            Toast.makeText(getApplicationContext(), " Length " + " " + minlength + "Between" + "" + maxlength, Toast.LENGTH_SHORT).show();
                            Log.e("mobilevalue",""+catagoryditialis.get(i).getValue().length()+1);
                            return;
                        }
                    }
                    List<Catagoryditialis> tempCatDetailsList =new ArrayList();
                    for (int j = 0; j < catagoryditialis.size(); j++) {
                        Catagoryditialis catDetails = new Catagoryditialis(catagoryditialis.get(j).getParamlevel(),
                                catagoryditialis.get(j).getParamname(),null,null);
                        catDetails.setValue(catagoryditialis.get(j).getValue());
                      tempCatDetailsList.add(catDetails);
                    }
                    value = new Gson().toJson(tempCatDetailsList);
                    Log.e("Fromjson", value);
                    for (int j = 0; j < catagoryditialissecondList.size(); j++) {
//                       if (catagoryditialissecondList.get(j).getParamlevel().equalsIgnoreCase("mobile")){
//                           Toast.makeText(getApplicationContext(), "Enter number", Toast.LENGTH_SHORT).show();
//                       }
                        mobile = catagoryditialissecondList.get(j).getValue();
                        mobilevalue = catagoryditialissecondList.get(j).getParamlevel();
                    }
                    value1 = new Gson().toJson(catagoryditialissecondList);
                    Log.e("Fromjson", value1);
                    Toast.makeText(getApplicationContext(), " Successfull ", Toast.LENGTH_SHORT).show();
//                    Intent myIntent = new Intent(Catagoryactivity.this, Blanceenquiry.class);
//                    startActivity(myIntent);
                    if("Payee Mobile Number".equals(mobilevalue)) {
                        if (mobile.equals("0")) {
                            Toast.makeText(getApplicationContext(), " Enter Mobile Number ", Toast.LENGTH_SHORT).show();
                        } else if (mobile.length() == 10) {
                            progressBar_cyclic.setVisibility(View.VISIBLE);
                            Log.e("Bpspaysetgetvalue",builderid+" "+name+" "+builderreq+" "+value+" "+value1);
                            closeKeyboard();
                        //    Getbill();
                        } else {
                            Toast.makeText(getApplicationContext(), "Enter 10 digit Mobile Number ", Toast.LENGTH_SHORT).show();

                        }
                    }
                    else {
//                        if (mobile.equals("0")) {
//                            Toast.makeText(getApplicationContext(), " Enter Mobile Number ", Toast.LENGTH_SHORT).show();
//                        }
//                        else {
//
//                        }
                        progressBar_cyclic.setVisibility(View.VISIBLE);
                        closeKeyboard();
                        Getbill();
                    }
                    Log.e("Bpspaysetgetvalue",builderid+" "+name+" "+builderreq+" "+value+" "+value1);
                }catch (Exception e) {
                    Log.e("error",""+e.getMessage());

                }
            }
        });

//        LinearLayout linearLayout = new LinearLayout(this);
//        ViewGroup.LayoutParams params = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.FILL_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
//        linearLayout.setLayoutParams(params);
//        linearLayout.setOrientation(LinearLayout.HORIZONTAL);
//
//        int count = 10;
   //     linearLayout.addView(tableLayout(count));
//        linearLayout.addView(submitButton());
//        setContentView(linearLayout);
        bill_catagory.setHasFixedSize(true);
        bill_catagory.setLayoutManager(new LinearLayoutManager(this));
        catagoryditialis = new ArrayList<>();
        catagoryditialissecondList = new ArrayList<>();

    }

    public void bulsecatagory(String Itemname){
        Call<Billercategory> call = ApiClientToken.getInstance().buldercatagory(Itemname);
        call.enqueue(new Callback<Billercategory>() {
            @Override
            public void onResponse(Call<Billercategory> call, retrofit2.Response<Billercategory> response) {
                if (response.isSuccessful()){
                    String error = response.body().getError();
                    Log.e("buolderresponse",""+error);
                    if (error.equals("false")){
                        for (int i = 0; i <response.body().getData().getList().size(); i++) {
                            ListBillercategory listdata =  response.body().getData().getList().get(i);
                            String Buildername = listdata.getName();
                            String Builderid = listdata.getBillerid();
                            System.out.println("buolder"+Buildername);
                            buildrname.add(Buildername);
                            ArrayAdapter aa = new ArrayAdapter(getApplicationContext(),android.R.layout.simple_spinner_item,buildrname);
                            aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                            board_Id.setAdapter(aa);

                        }
                        board_Id.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                            @Override
                            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                                BuilderId = response.body().getData().getList().get(position).getBillerid();
                                Log.e("BuilderId",BuilderId);
                                progressBar_cyclic.setVisibility(View.VISIBLE);
                                catagoryditialis.clear();
                                BillerDetails(BuilderId);
                            }
                        });
//                        board_Id.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
//                            @Override
//                            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
//                                board_Id.setVisibility(View.GONE);
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
            public void onFailure(Call<Billercategory> call, Throwable t) {
                t.getMessage();
            }
        });
    }
    public void BillerDetails(String BuilderId){
        board.setVisibility(View.GONE);
        Call<BillerDetails> call = ApiClientToken.getInstance().BillerDetailsvalue(BuilderId);
        call.enqueue(new Callback<BillerDetails>() {
            @Override
            public void onResponse(Call<BillerDetails> call, retrofit2.Response<BillerDetails> response) {
                if (response.isSuccessful()){
                    progressBar_cyclic.setVisibility(View.GONE);
                    String error = response.body().getError();
                    Log.e("buolderresponse",""+error);
                    if (error.equals("false")){
                         name = response.body().getData().getDetails().getName();
                         builderid = response.body().getData().getDetails().getBiller_id();
                        builderreq = response.body().getData().getDetails().getReq_id();
                        paytype = response.body().getData().getDetails().getPay_type();
                        Log.e("buolderresponse"," "+name+" "+builderid+" "+builderreq+" "+paytype);
                        for (int i = 0; i <response.body().getData().getDetails().getParams().size(); i++) {
                            ListBillerDetailsparams listdata =  response.body().getData().getDetails().getParams().get(i);
//                            ListBillerDetailsstaticparams listdata1 =  response.body().getData().getDetails().getParams().get(i);
                            lelel = listdata.getParamlevel();
                            buildename = listdata.getParamname();
                            max = listdata.getMaxlength();
                            min = listdata.getMinlength();
                            System.out.println("buildename"+lelel+" "+buildename+" "+max+" "+min);
                            catagoryditialis.add(new Catagoryditialis(
                                    lelel,
                                    buildename,max,min
                            ));
                            arrlist.addAll(catagoryditialis);
                        }
                        for (int i = 0; i <response.body().getData().getDetails().getStaticparams().size(); i++) {
                        ListBillerDetailsstaticparams listdata1 =  response.body().getData().getDetails().getStaticparams().get(i);
                            staticlelel = listdata1.getParamlevel();
                            staticbuildename = listdata1.getParamname();
                            System.out.println("buildename"+staticlelel+" "+staticbuildename);
                            catagoryditialissecondList.add(new Catagoryditialissecond(
                                    staticlelel,
                                    staticbuildename,"0"
                            ));
                            arrlist1.addAll(catagoryditialissecondList);
                        }

                        billpaymentItemViewAdapter4 = new BillpaymentItemViewAdapter4(getApplication(), catagoryditialis,catagoryditialissecondList);
                        bill_catagory.setAdapter(billpaymentItemViewAdapter4);

                    }
                }
                else {
                    Log.e("DATA", " "+response.message());
                }

            }

            @Override
            public void onFailure(Call<BillerDetails> call, Throwable t) {
                t.getMessage();
            }
        });

    }
    private void closeKeyboard()
    {
        View view = this.getCurrentFocus();
        if (view != null) {
            InputMethodManager manager
                    = (InputMethodManager)
                    getSystemService(
                            Context.INPUT_METHOD_SERVICE);
            manager.hideSoftInputFromWindow(
                    view.getWindowToken(), 0);
        }
    }
    public void Getbill(){
//        if (TextUtils.isEmpty(value1)) {
//            Toast.makeText(getApplicationContext(), " Enter Mobile Number ", Toast.LENGTH_SHORT).show();
//
//            return;
//        }

        Bpspaysetget bpspaysetget = new Bpspaysetget();
        bpspaysetget.setBillerid(builderid);
        bpspaysetget.setBillerName(name);
        bpspaysetget.setReqid(builderreq);
        bpspaysetget.setBbpsparams(value);
        bpspaysetget.setBbpsstaticparams(value1);
        Log.e("Bpspaysetgetvalue", "onClick: "+new Gson().toJson(bpspaysetget));
        Log.e("Bpspaysetgetvalue",builderid+" "+name+" "+builderreq+" "+value+" "+value1);

        Call<Bpspaysetgetvalue> call = ApiClientToken.getInstance().bbpsbillerpay(
                bpspaysetget
        );
        call.enqueue(new Callback<Bpspaysetgetvalue>() {
            @Override
            public void onResponse(Call<Bpspaysetgetvalue> call, retrofit2.Response<Bpspaysetgetvalue> response) {
               Log.e("Bpspaysetgetvalue1", " "+response.body().getError());
                if (response.isSuccessful()){
                    message = response.body().getMessage();
                    String error = response.body().getError();
                    Log.e("Bpspaysetgetvalue",message+" "+error);
                    Toast.makeText(getApplicationContext(),message,Toast.LENGTH_SHORT).show();
                    if (response.body().getError().equals("false")){
                        progressBar_cyclic.setVisibility(View.GONE);
                        String message1 = response.body().getMessage();
                        String paymentmodes = response.body().getData().getPaymentmodes();
                        String reqid = response.body().getData().getReqid();
                        String billerId = response.body().getData().getBillerId();
                        String billername = response.body().getData().getBillername();
                        String payeenumber = response.body().getData().getPayeenumber();
                        String customernumber = response.body().getData().getCustomernumber();
                        String amount = response.body().getData().getAmount();
                        String paytypee = response.body().getData().getPay_type();
                        String customeremail = response.body().getData().getCustomeremail();
                        String customername = response.body().getData().getCustomername();
                        String conveniencefees = response.body().getData().getConveniencefees();
                        String billdate = response.body().getData().getBilldate();
                        String billperiod = response.body().getData().getBillperiod();
                        String billnumber = response.body().getData().getBillnumber();
                        String billduedate = response.body().getData().getBillduedate();
                        for (int i = 0; i <response.body().getData().getPayparams().size(); i++) {
                            Payparamslist listdata1 =  response.body().getData().getPayparams().get(i);
                             paramname = listdata1.getParamname();
                             paramlavel = listdata1.getParamlevel();
                            Log.e("Bpspaysetgetvalue"," "+paymentmodes+" "
                                    +reqid+" "+billerId+" "+billername+" "+payeenumber+" "
                                    +customernumber+" "+amount+" "+paytypee+" "+customeremail+" "+customername
                                    +" "+paramname+" "+paramlavel+conveniencefees+" "
                                    +billdate+" "+billperiod+" "+billnumber+" "+billduedate);
                        }

                        Toast.makeText(getApplicationContext(),message1,Toast.LENGTH_SHORT).show();
                        if (paytype.equals("MANDATORY")) {
                            Log.e("Bpspaysetgetvalue","MANDATORY");
                            Intent myIntent = new Intent(Catagoryactivity.this, NotsupportedActivity.class);
                            myIntent.putExtra("paymentmodes", paymentmodes);
                            myIntent.putExtra("reqid", reqid);
                            myIntent.putExtra("billerId", billerId);
                            myIntent.putExtra("billername", billername);
                            myIntent.putExtra("payeenumber", payeenumber);
                            myIntent.putExtra("customernumber", customernumber);
                            myIntent.putExtra("amount", amount);
                            myIntent.putExtra("paytypee", paytypee);
                            myIntent.putExtra("customeremail", customeremail);
                            myIntent.putExtra("customername", customername);
                            myIntent.putExtra("paramname", paramname);
                            myIntent.putExtra("paramlavel", paramlavel);

                            myIntent.putExtra("conveniencefees", conveniencefees);
                            myIntent.putExtra("billdate", billdate);
                            myIntent.putExtra("billperiod", billperiod);
                            myIntent.putExtra("billnumber", billnumber);
                            myIntent.putExtra("billduedate", billduedate);
                            Catagoryactivity.this.startActivity(myIntent);
                        }
                        if (paytype.equals("NOT_SUPPORTED")) {
                            Log.e("Bpspaysetgetvalue","NOT_SUPPORTED");

                            Intent myIntent = new Intent(Catagoryactivity.this, SuccessfulActivity.class);
                            myIntent.putExtra("paymentmodes", paymentmodes);
                            myIntent.putExtra("reqid", reqid);
                            myIntent.putExtra("billerId", billerId);
                            myIntent.putExtra("billername", billername);
                            myIntent.putExtra("payeenumber", payeenumber);
                            myIntent.putExtra("customernumber", customernumber);
                            myIntent.putExtra("amount", amount);
                            myIntent.putExtra("paytypee", paytypee);
                            myIntent.putExtra("customeremail", customeremail);
                            myIntent.putExtra("customername", customername);
                            myIntent.putExtra("paramname", paramname);
                            myIntent.putExtra("paramlavel", paramlavel);

                            myIntent.putExtra("conveniencefees", conveniencefees);
                            myIntent.putExtra("billdate", billdate);
                            myIntent.putExtra("billperiod", billperiod);
                            myIntent.putExtra("billnumber", billnumber);
                            myIntent.putExtra("billduedate", billduedate);
                            Catagoryactivity.this.startActivity(myIntent);

                        }
                    }
                    if(response.body().getError().equals("true")){
                        Toast.makeText(getApplicationContext(),message,Toast.LENGTH_SHORT).show();
                    }
                }
                else {
                    Log.e("DATA", " "+response.message());
                    Toast.makeText(getApplicationContext(),message,Toast.LENGTH_SHORT).show();

                }

            }

            @Override
            public void onFailure(Call<Bpspaysetgetvalue> call, Throwable t) {

            }
        });
    }

    public class BillpaymentItemViewAdapter4 extends RecyclerView.Adapter<BillpaymentItemViewAdapter4.ViewHolder> {
        final int VIEW_TYPE_LIST = 0;
        final int VIEW_TYPE_LISTSECONED = 1;
        private Context mCtx;
        private List<Catagoryditialis> catagoryditialis;
        private List<Catagoryditialissecond> catagoryditialissecondList;
        int listview;

        public BillpaymentItemViewAdapter4(Context mCtx, List<Catagoryditialis> catagoryditialis, List<Catagoryditialissecond> catagoryditialissecondList) {
            this.mCtx = mCtx;
            this.catagoryditialis = catagoryditialis;
            this.catagoryditialissecondList=catagoryditialissecondList;
        }
        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.catagory_cardview_list, parent, false);
            return new ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(final ViewHolder holder, int position) {
            listview = position;
//            holder.paramlevel_Id.setText(bill_payment.getMinlength());
            //holder.name_ID.setText(bill_payment.getParamname());
            holder.minlength_ID.setText(catagoryditialis.get(position).getMinlength());
            holder.maxlength_ID.setText(catagoryditialis.get(position).getMaxlength());
//            System.out.println("Length"+catagoryditialis.get(position).getMinlength()+"  "+catagoryditialis.get(position).getMaxlength());
            holder.paramlevel_Id.setHint(catagoryditialis.get(position).getParamname());
            holder.txinut_name.setHint(catagoryditialis.get(position).getParamlevel());
            holder.textinpe_phone_id.setHint(catagoryditialissecondList.get(position).getParamlevel());
            holder.txinut_name.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {
                    catagoryditialis.get(position).setValue(s.toString().trim());
                }

                @Override
                public void afterTextChanged(Editable s) {

                }
            });
            holder.textinpe_phone_id.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {
                    catagoryditialissecondList.get(position).setValue(s.toString().trim());
                }

                @Override
                public void afterTextChanged(Editable s) {

                }
            });
        }
        @Override
        public int getItemCount() {
          //  return catagoryditialis.size() + catagoryditialissecondList.size();
            // return catagoryditialis.size();
            if(catagoryditialis.size()>catagoryditialissecondList.size()){
                return  Math.max(catagoryditialis.size(), catagoryditialissecondList.size());
            }
            else {
                return  Math.min(catagoryditialissecondList.size(), catagoryditialis.size());
            }
          //  return  Math.min(catagoryditialissecondList.size(), catagoryditialis.size());
        }
        public class ViewHolder extends RecyclerView.ViewHolder {
            private TextInputEditText paramlevel_Id,txinut_name,minlength_ID,maxlength_ID,textinpe_phone_id;
            public ViewHolder(View view) {
                super(view);
                paramlevel_Id = (TextInputEditText) view.findViewById(R.id.paramlevel_Id);
                txinut_name = (TextInputEditText) view.findViewById(R.id.txinut_name);
                minlength_ID = (TextInputEditText) view.findViewById(R.id.minlength_ID);
                maxlength_ID = (TextInputEditText) view.findViewById(R.id.maxlength_ID);
                textinpe_phone_id = (TextInputEditText) view.findViewById(R.id.textinpe_phone_id);
                // catid = (TextView)view.findViewById(R.id.catid);
                //lv = (LinearLayout) view.findViewById(R.id.lv);
            }
        }
    }




//    private TableLayout tableLayout(int count) {
//        TableLayout tableLayout = new TableLayout(this);
//        tableLayout.setStretchAllColumns(true);
//        int noOfRows = count / 1;
//        for (int i = 0; i < noOfRows; i++) {
//            int rowId = 1 * i;
//            tableLayout.addView(createOneFullRow(rowId));
//        }
//        int individualCells = count % 1;
//        tableLayout.addView(createLeftOverCells(individualCells, count));
//        return tableLayout;
//    }
//
//    private TableRow createLeftOverCells(int individualCells, int count) {
//        TableRow tableRow = new TableRow(this);
//        tableRow.setPadding(0, 10, 0, 0);
//        int rowId = count - individualCells;
//        for (int i = 1; i <= individualCells; i++) {
//            tableRow.addView(editText(String.valueOf(rowId + i)));
//        }
//        return tableRow;
//    }
//
//    private TableRow createOneFullRow(int rowId) {
//        TableRow tableRow = new TableRow(this);
//        tableRow.setPadding(0, 10, 0, 0);
//        for (int i = 1; i <= 1; i++) {
//            tableRow.addView(editText(String.valueOf(rowId + i)));
//        }
//        return tableRow;
//    }
//
//    private EditText editText(String hint) {
//        EditText editText = new EditText(this);
//        editText.setId(Integer.valueOf(hint));
//        editText.setHint(hint);
//        editTextList.add(editText);
//        return editText;
//    }
//
}
