package com.kits.kitsclick;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatEditText;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.loader.app.LoaderManager;

import com.google.gson.Gson;
import com.kits.kitsclick.paytem.Api;
import com.kits.kitsclick.paytem.Checksum;
import com.kits.kitsclick.paytem.Constants;
import com.kits.kitsclick.paytem.JSONParser;
import com.kits.kitsclick.paytem.Paytemdat;
import com.kits.kitsclick.paytem.Paytemsetget;
import com.kits.kitsclick.paytem.Paytm;
import com.kits.kitsclick.setget.Dthoerator;
import com.kits.kitsclick.setget.Logindata;
import com.kits.kitsclick.setget.Mobiledatarechagevalue;
import com.kits.kitsclick.setget.Mobilerecharedata;
import com.kits.kitsclick.setget.Mobleoperatorlist;
import com.kits.kitsclick.setget.Verfychecksum;
import com.kits.kitsclick.setget.Verfydata;
import com.paytm.pgsdk.PaytmOrder;
import com.paytm.pgsdk.PaytmPaymentTransactionCallback;
import com.paytm.pgsdk.TransactionManager;


import io.github.parthav46.httprequest.HttpRequest;
import io.github.parthav46.httprequest.HttpResponseCallback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;



import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
//public class Creditcarddebitcard_Activity extends AppCompatActivity implements PaytmPaymentTransactionCallback {
public class Creditcarddebitcard_Activity extends AppCompatActivity {

    String amount;
    AppCompatEditText cardnumber,experID,ccv;
    TextView tv_emi,tv_deliveryID,tv_amountId,tv_p,tv_pri;
    Button contatue;
    String name;
    String custid="", orderId="", mid="";
    String varifyurl = "" ;
    float value;
    final int requestCode = 2;
    LoaderManager loaderManager;
    String bodyData = "";
    TextView balance;
    ImageView Img2,Img;
    @Override
    protected void onCreate(@Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.creditcarddebitcard_activity);
        cardnumber = (AppCompatEditText)findViewById(R.id.cardnumber);
        experID = (AppCompatEditText)findViewById(R.id.experID);
        ccv = (AppCompatEditText)findViewById(R.id.ccv);
        tv_emi =(TextView)findViewById(R.id.tv_emi);
        tv_deliveryID =(TextView)findViewById(R.id.tv_deliveryID);
        tv_amountId =(TextView)findViewById(R.id.tv_amountId);
        tv_p =(TextView)findViewById(R.id.tv_p);
        tv_pri =(TextView)findViewById(R.id.tv_pri);
        contatue = (Button) findViewById(R.id.contatue);
        contatue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                generateCheckSum();
            }
        });
        Intent intent = getIntent();
        amount = intent.getStringExtra("amount");
        Log.e("Amount",amount);
        Logindata logindata = SharedPrefManagerLogin.getInstance(getApplicationContext()).getLogindata();
        name = String.valueOf(logindata.getName());
        if (ContextCompat.checkSelfPermission(Creditcarddebitcard_Activity.this, Manifest.permission.READ_SMS) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(Creditcarddebitcard_Activity.this, new String[]{Manifest.permission.READ_SMS, Manifest.permission.RECEIVE_SMS}, 101);
        }
        balance = (TextView)findViewById(R.id.balance);
        Img2 = (ImageView)findViewById(R.id.Img2);
        Img = (ImageView)findViewById(R.id.Img);
        Img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),NavigationDrawerActivity.class);
                startActivity(intent);
            }
        });
        Img2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }


    private void generateCheckSum() {
//        Retrofit retrofit = new Retrofit.Builder()
//                .baseUrl(Api.BASE_URL)
//                .addConverterFactory(GsonConverterFactory.create())
//                .build();
//        Api apiService = retrofit.create(Api.class);
//        final Paytm paytm = new Paytm(
//                Constants.M_ID,
//                Constants.CHANNEL_ID,
//                amount,
//                Constants.WEBSITE,
//                Constants.CALLBACK_URL,
//                Constants.INDUSTRY_TYPE_ID
//        );
//
//        //creating a call object from the apiService
//        Call<Checksum> callvalue = apiService.getChecksum(
//                paytm.getmId(),
//                paytm.getOrderId(),
//                paytm.getCustId(),
//                paytm.getChannelId(),
//                paytm.getTxnAmount(),
//                paytm.getWebsite(),
//                paytm.getCallBackUrl(),
//                paytm.getIndustryTypeId()
//        );
//
//        //making the call to generate checksum
//        callvalue.enqueue(new Callback<Checksum>() {
//            @Override
//            public void onResponse(Call<Checksum> call, Response<Checksum> response) {
//
//
//            }
//
//            @Override
//            public void onFailure(Call<Checksum> call, Throwable t) {
//
//            }
//        });



        Paytemdat paytemdat = new Paytemdat();
        paytemdat.setAmount(amount);
        Log.d("UpgradeSchool", "onClick: "+new Gson().toJson(paytemdat));
        Call<Paytemsetget> call = ApiClientToken.getInstance().paytemdatCall(
                paytemdat
        );
        call.enqueue(new Callback<Paytemsetget>() {
            @Override
            public void onResponse(Call<Paytemsetget> call, retrofit2.Response<Paytemsetget> response) {
//                Log.e("DATA", " "+response.body().getStatusCode());
                if (response.isSuccessful()){
                    String error = response.body().getError();
                    Log.e("response",""+error);
                    if (error.equals("false")){
                        String cheksum = response.body().getData().getChecksum();
                        String orderId = response.body().getData().getOrderid();
                        String paystatus = response.body().getData().getPaytstatus();
                        String cusId = response.body().getData().getCUSTID();
                        String merchentkey = response.body().getData().getMerchentkey();
                        String mid = response.body().getData().getMid();
                        String channelid = response.body().getData().getChannelid();
                        String induty = response.body().getData().getIndustrytype();
                        String website = response.body().getData().getWebsite();
                        Log.e("paytemresponse",""+cheksum+" "+orderId+" "+paystatus);
                        Log.e("CHecksum",cheksum);
                        //  initializePaytmPayment(response.body().getData().getChecksum(), amount,orderId);
                        veryfychecksum(response.body().getData().getChecksum(),
                                amount,orderId,cusId,paystatus,merchentkey,mid,channelid,induty,website);
//                        mid = "Ktscli96530948369699"; /// your marchant key
//                        varifyurl = "https://securegw-stage.paytm.in/theia/paytmCallback?ORDER_ID="+orderId;
//                        sendUserDetailTOServerdd dl = new sendUserDetailTOServerdd(cheksum,orderId,amount,mid,varifyurl);
//                        dl.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
                    }
                }
                else {
                    Log.e("DATA", " "+response.message());
                }
            }
            @Override
            public void onFailure(Call<Paytemsetget> call, Throwable t) {

            }
        });
    }
    public  void veryfychecksum(String checksumHash,String amount,String orderId,
                                String cusId,String paystatus,String merchentkey,String mid,String channelid,String induty,String website){
        Verfychecksum verfychecksum = new Verfychecksum();
        verfychecksum.setChecksumHash(checksumHash);
        verfychecksum.setOrderId(orderId);
        verfychecksum.setAmount(amount);
        verfychecksum.setStatus(paystatus);
        Log.d("UpgradeSchool", "onClick: "+new Gson().toJson(verfychecksum));
        Call<Verfydata> call = ApiClientToken.getInstance().veryfy(
                verfychecksum
        );
        call.enqueue(new Callback<Verfydata>() {
            @Override
            public void onResponse(Call<Verfydata> call, retrofit2.Response<Verfydata> response) {
//                Log.e("DATA", " "+response.body().getStatusCode());
                if (response.isSuccessful()){
                    String error = response.body().getError();
                    Log.e("response",""+error);
                    if (error.equals("false")){
                        String message = response.body().getMessage();
                        String cheksum = response.body().getChecksum();
                        Toast.makeText(getApplicationContext(),cheksum,Toast.LENGTH_SHORT).show();
                        Log.e("CHecksum1",cheksum);
                        initializePaytmPayment(cheksum, amount,orderId,cusId,merchentkey,mid,channelid,induty,website);


                    }
                }
                else {
                    Log.e("DATA", " "+response.message());
                }
            }
            @Override
            public void onFailure(Call<Verfydata> call, Throwable t) {

            }
        });
    }
    private void initializePaytmPayment(String checksumHash,String amount,String orderId,
                                        String cusId,String merchentkey,String
            mid,String channelid,String induty,String website) {
        startPayment(checksumHash,amount,orderId,
                cusId,merchentkey,
                mid,channelid,induty,website);
//        JSONObject paytmParams = new JSONObject();
//        JSONObject body = new JSONObject();
//        try {
//            body.put("requestType", "Payment");
//            body.put("mid", mid);
//            body.put("websiteName", website);
//            body.put("orderId", orderId);
//            body.put("callbackUrl", "https://<callback URL to be used by merchant>");
//            JSONObject txnAmount = new JSONObject();
//            txnAmount.put("value", amount);
//            txnAmount.put("currency", "INR");
//            JSONObject userInfo = new JSONObject();
//            userInfo.put("custId", cusId);
//            body.put("txnAmount",amount);
//            body.put("userInfo", userInfo);
//
//            String checksum = PaytmChecksum.generateSignature(body.toString(),merchentkey);
//            JSONObject head = new JSONObject();
//            head.put("signature", checksum);
//
//            paytmParams.put("body", body);
//            paytmParams.put("head", head);
//        } catch (JSONException e) {
//            e.printStackTrace();
//        }


////        PaytmPGService Service = PaytmPGService.getStagingService("");
//        PaytmPGService Service = PaytmPGService.getProductionService();
//        Map<String, String> paramMap = new HashMap<>();
//        paramMap.put("MID", Constants.M_ID);
//        paramMap.put("ORDER_ID", orderId);
////      paramMap.put("CUST_ID", paytm.getCustId());
//        paramMap.put("CUST_ID", cusId);
//        paramMap.put("CHANNEL_ID",  Constants.CHANNEL_ID);
//        paramMap.put("TXN_AMOUNT", amount);
//        paramMap.put("WEBSITE",  Constants.WEBSITE);
//        paramMap.put("CALLBACK_URL",Constants.CALLBACK_URL+orderId);
////        paramMap.put("EMAIL" ,"mailtoanil8@gmail.com");
////        paramMap.put("MOBILE_NO" , "7603056655");
//        paramMap.put("CHECKSUMHASH", checksumHash);
//        paramMap.put("INDUSTRY_TYPE_ID", Constants.INDUSTRY_TYPE_ID);
//        PaytmOrder order = new PaytmOrder((HashMap<String, String>) paramMap);
////      PaytmClientCertificate Certificate = new PaytmClientCertificate(String inPassword, String inFileName);
//        Log.e("Erroemessage",order.toString()+" "+paramMap.toString());
//        Service.initialize(order, null);
//        Service.startPaymentTransaction(Creditcarddebitcard_Activity.this, true, true,
//                Creditcarddebitcard_Activity.this  );
////        Service.startPaymentTransaction(this, true, true, new PaytmPaymentTransactionCallback() {
////            /*Call Backs*/
////            public void someUIErrorOccurred(String inErrorMessage) {}
////            public void onTransactionResponse(Bundle inResponse) {}
////            public void networkNotAvailable() {}
////            public void clientAuthenticationFailed(String inErrorMessage) {}
////            public void onErrorLoadingWebPage(int iniErrorCode, String inErrorMessage, String inFailingUrl) {}
////            public void onBackPressedCancelTransaction() {}
////            public void onTransactionCancel(String inErrorMessage, Bundle inResponse) {}
////        });
    }

//    public class sendUserDetailTOServerdd extends AsyncTask<ArrayList<String>, Void, String> {
//        private ProgressDialog dialog = new ProgressDialog(Creditcarddebitcard_Activity.this);
//        //private String orderId , mid, custid, amt;
//        String url ="https://www.blueappsoftware.com/payment/payment_paytm/generateChecksum.php";
////        String varifyurl = "https://pguat.paytm.com/paytmchecksum/paytmCallback.jsp";
//        String CHECKSUMHASH ="";
//        String orderId ="";
//        String amount = "";
//        String mid = "";
//        String varifyurl ="";
//        public sendUserDetailTOServerdd(String CHECKSUMHASH, String orderId,String amount,String mid,String varifyurl) {
//            this.CHECKSUMHASH=CHECKSUMHASH;
//            this.orderId=orderId;
//            this.amount=amount;
//            this.mid=mid;
//            this.varifyurl=varifyurl;
//        }
//
//        @Override
//        protected void onPreExecute() {
//            this.dialog.setMessage("Please wait");
//            this.dialog.show();
//        }
//        protected String doInBackground(ArrayList<String>... alldata) {
//            JSONParser jsonParser = new JSONParser(Creditcarddebitcard_Activity.this);
//            String param=
//                    "MID="+mid+
//                            "&ORDER_ID=" + orderId+
//                            "&CUST_ID="+custid+
//                            "&CHANNEL_ID=WAP&TXN_AMOUNT="+amount+"&WEBSITE=WEBSTAGING"+
//                            "&CALLBACK_URL="+ varifyurl+"&INDUSTRY_TYPE_ID=Retail";
////            JSONObject jsonObject = jsonParser.makeHttpRequest(url,"POST",param);
////            // yaha per checksum ke saht order id or status receive hoga..
////            Log.e("CheckSum result >>",jsonObject.toString());
////            if(jsonObject != null){
////                Log.e("CheckSum result >>",jsonObject.toString());
////                try {
////                    CHECKSUMHASH=jsonObject.has("CHECKSUMHASH")?jsonObject.getString("CHECKSUMHASH"):"";
////                    Log.e("CheckSum result >>",CHECKSUMHASH);
////                } catch (JSONException e) {
////                    e.printStackTrace();
////                }
////            }
//            return CHECKSUMHASH;
//        }
//        @Override
//        protected void onPostExecute(String result) {
//            Log.e(" setup acc ","  signup result  " + result);
//            if (dialog.isShowing()) {
//                dialog.dismiss();
//            }
//           // PaytmPGService Service = PaytmPGService.getStagingService("");
//            // when app is ready to publish use production service
//             PaytmPGService  Service = PaytmPGService.getProductionService();
//            // now call paytm service here
//            //below parameter map is required to construct PaytmOrder object, Merchant should replace below map values with his own values
//            HashMap<String, String> paramMap = new HashMap<String, String>();
//            //these are mandatory parameters
//            paramMap.put("MID", mid); //MID provided by paytm
//            paramMap.put("ORDER_ID", orderId);
//            paramMap.put("CUST_ID", custid);
//            paramMap.put("CHANNEL_ID", "WAP");
//            paramMap.put("TXN_AMOUNT", amount);
//            paramMap.put("WEBSITE", "WEBSTAGING");
//            paramMap.put("CALLBACK_URL" ,varifyurl);
//            paramMap.put("EMAIL" ,"mailtoanil8@gmail.com");
//             paramMap.put("MOBILE_NO" , "7603056655");
//            paramMap.put("CHECKSUMHASH" ,CHECKSUMHASH);
//            //paramMap.put("PAYMENT_TYPE_ID" ,"CC");    // no need
//            paramMap.put("INDUSTRY_TYPE_ID", "Retail");
//            PaytmOrder Order = new PaytmOrder(paramMap);
//            Log.e("checksum ", "param "+ paramMap.toString());
//            Service.initialize(Order,null);
//            Service.startPaymentTransaction(Creditcarddebitcard_Activity.this, true, true,
//                    Creditcarddebitcard_Activity.this  );
//        }
//    }




//    @Override
//    public void onTransactionResponse(Bundle inResponse) {
//        Toast.makeText(getApplicationContext(), "Payment Transaction response " + inResponse.toString(), Toast.LENGTH_LONG).show();
//        Log.e("Erroemessage",inResponse.toString());
//    }
//
//    @Override
//    public void networkNotAvailable() {
//        Toast.makeText(getApplicationContext(), "Network connection error: Check your internet connectivity", Toast.LENGTH_LONG).show();
//
//    }
//
//    @Override
//    public void clientAuthenticationFailed(String inErrorMessage) {
//        Toast.makeText(getApplicationContext(), "Authentication failed: Server error" + inErrorMessage.toString(), Toast.LENGTH_LONG).show();
//        Log.e("Erroemessage",inErrorMessage.toString());
//
//    }
//
//    @Override
//    public void someUIErrorOccurred(String inErrorMessage) {
//        Toast.makeText(getApplicationContext(), "UI Error " + inErrorMessage , Toast.LENGTH_LONG).show();
//        Log.e("Erroemessage",inErrorMessage.toString());
//
//    }
//
//    @Override
//    public void onErrorLoadingWebPage(int iniErrorCode, String inErrorMessage, String inFailingUrl) {
//        Toast.makeText(getApplicationContext(), "Unable to load webpage " + inErrorMessage.toString(), Toast.LENGTH_LONG).show();
//        Log.e("Erroemessage",inErrorMessage.toString());
//
//    }
//
//    @Override
//    public void onBackPressedCancelTransaction() {
//        Toast.makeText(getApplicationContext(), "Transaction cancelled" , Toast.LENGTH_LONG).show();
//
//    }
//
//    @Override
//    public void onTransactionCancel(String inErrorMessage, Bundle inResponse) {
//        Log.e("Erroemessage",inErrorMessage.toString());
//
//    }
    void startPayment(String checksumHash,String amount,String orderId,
                      String cusId,String merchentkey,String
                              mid,String channelid,String induty,String website) {
        Log.e("DATA","0");
    final ProgressDialog progressDialog = new ProgressDialog(Creditcarddebitcard_Activity.this);
    progressDialog.setMessage("Making Payment....");
    progressDialog.show();
    bodyData = getPaytmParams(checksumHash,amount,orderId,
            cusId,merchentkey,
            mid,channelid,induty,website);
    new HttpRequest(Creditcarddebitcard_Activity.this, checksumHash, HttpRequest.Request.POST, bodyData, new HttpResponseCallback() {
        @Override
        public void onResponse(String response) {
            if(response != null) {
                try {
                    JSONObject paytmParams = new JSONObject();
                    JSONObject head = new JSONObject();
                    String checksum = new JSONObject(response).getString("checksum");
                    Log.e("checksum", checksum);
                    head.put("signature", checksum);

                    paytmParams.put("head", head);
                    paytmParams.put("body", new JSONObject(bodyData));

                    String url = "https://securegw-stage.paytm.in/theia/api/v1/initiateTransaction?mid=" + mid + "&orderId=" + orderId;

                    new HttpRequest(Creditcarddebitcard_Activity.this, url, HttpRequest.Request.POST, paytmParams.toString(), new HttpResponseCallback() {
                        @Override
                        public void onResponse(String response) {
                            if (response != null) {
                                try {
                                    processPaytmTransaction(new JSONObject(response),checksumHash,amount,orderId,
                                            cusId,merchentkey,
                                            mid,channelid,induty,website);
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                } finally {
                                    //orderId = "ID" + System.currentTimeMillis();
                                }
                            }
                            if (progressDialog.isShowing()) progressDialog.dismiss();
                        }
                    }).execute();
                } catch (Exception e) {
                    e.printStackTrace();
                    if (progressDialog.isShowing()) progressDialog.dismiss();
                }
            } else {
                if (progressDialog.isShowing()) progressDialog.dismiss();
            }
        }
    }).execute();
}
    String getPaytmParams (String checksumHash,String amount,String orderId,
                           String cusId,String merchentkey,String
                                   mid,String channelid,String induty,String website) {
        JSONObject paytmParams;
        try {
            JSONObject body = new JSONObject();
            body.put("requestType", "Payment");
            body.put("mid", mid);
            body.put("websiteName", website);
            body.put("orderId", orderId);
            body.put("callbackUrl", Constants.CALLBACK_URL+orderId);

            JSONObject txnAmount = new JSONObject();
            try{
                value = Float.parseFloat(amount);
            } catch (Exception e) {
                value = 0f;
            }
            txnAmount.put("value", String.format(Locale.getDefault(), "%.2f", value));
            txnAmount.put("currency", "INR");

            JSONObject userInfo = new JSONObject();
            userInfo.put("custId", cusId);

            body.put("txnAmount", txnAmount);
            body.put("userInfo", userInfo);

            /*
             * Generate checksum by parameters we have in body
             * You can get Checksum JAR from https://developer.paytm.com/docs/checksum/
             * Find your Merchant Key in your Paytm Dashboard at https://dashboard.paytm.com/next/apikeys
             */

            paytmParams = body;

        } catch (Exception e) {
            e.printStackTrace();
            paytmParams = new JSONObject();
        }
        return paytmParams.toString();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == this.requestCode && data != null) {
            String nsdk = data.getStringExtra("nativeSdkForMerchantMessage");
            String response = data.getStringExtra("response");
            Toast.makeText(this, nsdk + response, Toast.LENGTH_SHORT).show();
        }
    }
    void processPaytmTransaction(JSONObject data,String checksumHash,String amount,String orderId,
                                 String cusId,String merchentkey,String
                                         mid,String channelid,String induty,String website) {
        try {
            Log.i("CHECKSUM", data.getJSONObject("body").toString());
            Log.i("CHECKSUM", data.getJSONObject("head").getString("signature"));
            Log.e("TXN_TOKEN", data.getJSONObject("body").getString("txnToken"));



            PaytmOrder paytmOrder = new PaytmOrder(orderId, mid, data.getJSONObject("body").getString("txnToken"),
                    String.format(Locale.getDefault(), "%.2f", value), Constants.CALLBACK_URL+orderId);
            TransactionManager transactionManager = new TransactionManager(paytmOrder, new PaytmPaymentTransactionCallback() {
                @Override
                public void onTransactionResponse(Bundle bundle) {
                    Toast.makeText(getApplicationContext(), "Payment Transaction response " + bundle.toString(), Toast.LENGTH_LONG).show();
                }

                @Override
                public void networkNotAvailable() {
                    Log.e("RESPONSE", "network not available");
                }

                @Override
                public void onErrorProceed(String s) {
                    Log.e("RESPONSE", "error proceed: " + s);

                }

                @Override
                public void clientAuthenticationFailed(String s) {
                    Log.e("RESPONSE", "client auth failed: " + s);

                }

                @Override
                public void someUIErrorOccurred(String s) {
                    Log.e("RESPONSE", "UI error occured: " + s);

                }

                @Override
                public void onErrorLoadingWebPage(int i, String s, String s1) {
                    Log.e("RESPONSE", "error loading webpage: " + s + "--" + s1);

                }

                @Override
                public void onBackPressedCancelTransaction() {
                    Log.e("RESPONSE", "back pressed");

                }

                @Override
                public void onTransactionCancel(String s, Bundle bundle) {
                    Log.e("RESPONSE", "transaction cancel: " + s);

                }
            });
            transactionManager.startTransaction(Creditcarddebitcard_Activity.this, requestCode);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
