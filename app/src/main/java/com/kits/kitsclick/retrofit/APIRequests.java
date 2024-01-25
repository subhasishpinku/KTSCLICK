package com.kits.kitsclick.retrofit;
import com.kits.kitsclick.paytem.Paytemdat;
import com.kits.kitsclick.paytem.Paytemsetget;
import com.kits.kitsclick.setget.Addscannerdevice;
import com.kits.kitsclick.setget.Addscannerdeviceresult;
import com.kits.kitsclick.setget.Banklist;
import com.kits.kitsclick.setget.Banksetget;
import com.kits.kitsclick.setget.Bannerdata;
import com.kits.kitsclick.setget.BillerDetails;
import com.kits.kitsclick.setget.Billercategory;
import com.kits.kitsclick.setget.Billpaymentservice;
import com.kits.kitsclick.setget.Blancereload;
import com.kits.kitsclick.setget.Bpspaysetget;
import com.kits.kitsclick.setget.Bpspaysetgetvalue;
import com.kits.kitsclick.setget.Busnesstype;
import com.kits.kitsclick.setget.Country;
import com.kits.kitsclick.setget.Days;
import com.kits.kitsclick.setget.Deviceadddata;
import com.kits.kitsclick.setget.Distric;
import com.kits.kitsclick.setget.Dthoerator;
import com.kits.kitsclick.setget.MethodList;
import com.kits.kitsclick.setget.Mobiledatarechagevalue;
import com.kits.kitsclick.setget.Mobileoperator;
import com.kits.kitsclick.setget.Mobilerecharedata;
import com.kits.kitsclick.setget.Month;
import com.kits.kitsclick.setget.Notificationservice;
import com.kits.kitsclick.setget.Otpcheck;
import com.kits.kitsclick.setget.Otpvalue;
import com.kits.kitsclick.setget.Pagecheck;
import com.kits.kitsclick.setget.PersonalDetails;
import com.kits.kitsclick.setget.Pinresult;
import com.kits.kitsclick.setget.Pinset;
import com.kits.kitsclick.setget.Reportvalue;
import com.kits.kitsclick.setget.Reportview;
import com.kits.kitsclick.setget.Resultpersonaldetails;
import com.kits.kitsclick.setget.Scannerdatalist;
import com.kits.kitsclick.setget.Securitirizationdata;
import com.kits.kitsclick.setget.Securitirizationresultdetails;
import com.kits.kitsclick.setget.Securitirizationsavedata;
import com.kits.kitsclick.setget.State;
import com.kits.kitsclick.setget.Verfychecksum;
import com.kits.kitsclick.setget.Verfydata;
import com.kits.kitsclick.setget.Yearsetget;
import com.kits.kitsclick.setget.LoginResult;
import com.kits.kitsclick.setget.LoginValue;
import com.kits.kitsclick.setget.RegistrationValue;
import com.kits.kitsclick.setget.Registrationresult;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;
public interface APIRequests {
    @POST("login")
    Call<LoginResult> loginResultCall(@Body LoginValue loginResultCall);

    @POST("registration")
    Call<Registrationresult> registrationResultCall(@Body RegistrationValue registrationValue);

    @GET("year-list")
    Call<Yearsetget> year();

    @GET("month-list")
    Call<Month> month();

    @GET("day-list")
    Call<Days> day();

    @GET("country")
    Call<Country> countryname();

    @GET("state")
    Call<State> statename();

//    @GET("city?st_id=")
//    Call<Distric> districname();
    @GET("city")
    Call<Distric> districname(@Query("st_id") String st_id);

    @GET("bbps-biller-list")
    Call<Billercategory> buldercatagory(@Query("biller_category") String biller_category);

    @GET("bbps-biller-details")
    Call<BillerDetails> BillerDetailsvalue(@Query("biller_id") String biller_id);

    @POST("complete-personal-details")
    Call<Resultpersonaldetails> personalDetailscall(@Body PersonalDetails personalDetails);

    @GET("business-type")
    Call<Busnesstype> busnessname();

    @GET("reports-name-list")
    Call<Reportvalue> reportvalues();


    @GET("reports-name-list")
    Call<Reportvalue> transation(@Query("report_type") String report_type);

    @GET("reports-by-name")
    Call<Reportview> reportview(@Query("name") String name);

    @GET("loginpage-slider")
    Call<Bannerdata> getbanner();

    @GET("securitization-type")
    Call<Securitirizationdata> securitization();

    @GET("get-step")
    Call<Pagecheck> pagecheck();

    @GET("send-kyc-otp")
    Call<Otpcheck> otpcheckvalue();

    @POST("verify-kyc-otp")
    Call<Otpcheck> otpchecverify(@Body Otpvalue otpvalue);

    @POST("bbps-biller-pay")
    Call<Bpspaysetgetvalue> bbpsbillerpay(@Body Bpspaysetget bpspaysetget);

    @POST("complete-business-details")
    Call<Securitirizationresultdetails> securitirizationsavedataCall(@Body Securitirizationsavedata personalDetails);

    @GET("mobile-recharge-operator")
    Call<Mobileoperator> operatorname();

    @GET("dth-recharge-operator")
    Call<Dthoerator> Dthopertaorname();

    @POST("paytm-checksum")
    Call<Paytemsetget> paytemdatCall(@Body Paytemdat paytemdat);

    @POST("validate-checksum")
    Call<Verfydata> veryfy(@Body Verfychecksum verfychecksum);

    @GET("get-scanner-type")
    Call<Scannerdatalist> scannertype();

    @GET("get-aeps-bank")
    Call<Banksetget> getaepsbank();

    @GET("bank-list")
    Call<Banklist> banklist();

    @GET("method-list")
    Call<MethodList> methodlist();

    @POST("mobile-recharge")
    Call<Mobilerecharedata> mobiledatarecharge(@Body Mobiledatarechagevalue mobiledatarechagevalue);

    @POST("dth-recharge")
    Call<Mobilerecharedata> mobiledatarechdth(@Body Mobiledatarechagevalue mobiledatarechagevalue);

    @POST("add-scanner")
    Call<Addscannerdeviceresult> addscannerdevicedatah(@Body Addscannerdevice mobiledatarechagevalue);


    @POST("generate-login-pin")
    Call<Pinresult> pindata(@Body Pinset pinset);

    @GET("notice")
    Call<Notificationservice> getnotification();

    @GET("bbps-category")
    Call<Billpaymentservice> billpaymentlist();

    @GET("refresh-wallet-balance")
    Call<Blancereload> blancereload();

    @GET("aeps-scanner-list")
    Call<Deviceadddata> deviceadd();
}
