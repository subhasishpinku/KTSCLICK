package com.kits.kitsclick;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;

import com.kits.kitsclick.setget.Logindata;

public class SharedPrefManagerLogin {
    private static final String SHARED_PREF_NAME = "ktslogin";
    private static final String KEY_TOKEN = "keytoken";
    private static final String KEY_MSSAGE = "message1";
    private static final String KEY_AADHARNUMBER = "aadharnumber";
    private static final String KEY_ACCOUNTNO = "accountno";
    private static final String KEY_ADDEDBY = "addedBy";
    private static final String KEY_ADDEDON = "addedOn";
    private static final String KEY_ADDRESS = "address";
    private static final String KEY_ADDRESS1 = "address1";
    private static final String KEY_ADDRESSPROOF = "addressproof";
    private static final String KEY_ADHARNO = "adharNo";
    private static final String KEY_AEPSOACKID = "aepspackID";
    private static final String KEY_AGENCYID = "agencyID";
    private static final String KEY_AMAZONPACKID = "amazonpackID";
    private static final String KEY_APITOKEN = "apitoken";
    private static final String KEY_BALANCE = "balance";
    private static final String KEY_BANKNAME = "bankname";
    private static final String KEY_BBPPACKID = "bbpspackID";
    private static final String KEY_BRANCHNAME = "branchname";
    private static final String KEY_BUSINESSYEAR = "businessyear";
    private static final String KEY_BUSSINESS = "bussiness";
    private static final String KEY_BUSPACKID = "buspackID";
    private static final String KEY_CITY = "city";

    private static final String KEY_CONTACTPERSON = "contactPerson";
    private static final String KEY_COUNTRY = "country";
    private static final String KEY_COUNTRYCODE = "countryCode";
    private static final String KEY_COORDINATOR = "coordinator";
    private static final String KEY_CREATEDAT = "createdat";
    private static final String KEY_CREDITBALANCE = "creditbalance";
    private static final String KEY_CURRENCY = "currency";
    private static final String KEY_CNAME = "cname";
    private static final String KEY_DISTRICT = "district";
    private static final String KEY_DOB = "dob";
    private static final String KEY_DRIVINGLICIENCE = "drivinglicience";
    private static final String KEY_DTHPACKID = "dthpackID";
    private static final String KEY_EKYCCOM = "ekyccom";
    private static final String KEY_EMAIL = "email";
    private static final String KEY_EMAILID = "emailID";
    private static final String KEY_FAX = "fax";
    private static final String KEY_FLIGHTPACKID = "flightpackID";
    private static final String KEY_FRIM = "frim";
    private static final String KEY_GENDER = "gender";
    private static final String KEY_GROUPID = "groupID";

    private static final String KEY_HOTLEPACKID = "hotlepackID";
    private static final String KEY_IATA = "iata";
    private static final String KEY_IFSCODE = "ifsccode";
    private static final String KEY_LATUTUDE = "latutude";
    private static final String KEY_LOCATIONTYPE = "locationType";
    private static final String KEY_LOGO = "logo";
    private static final String KEY_LONGITUDE = "longitude";
    private static final String KEY_MINBLANCE = "minbalance";
    private static final String KEY_MOBILE = "mobile";
    private static final String KEY_MOBILEPACKID = "mobilepackID";
    private static final String KEY_MONEYPACKID = "moneypackID";
    private static final String KEY_NAME = "name";


    private static final String KEY_NEWAGENCY = "newagency";
    private static final String KEY_OFFICESACE = "officesapce";
    private static final String KEY_PACKID = "packID";
    private static final String KEY_PANNNUMBER = "pannumber";
    private static final String KEY_PANPACKID = "panpackID";
    private static final String KEY_PASSPORT = "passport";
    private static final String KEY_PERMISSION = "permission";
    private static final String KEY_PHONENO = "phoneno";
    private static final String KEY_PHOTO = "photo";
    private static final String KEY_PINCODE = "pincode";
    private static final String KEY_POPULATION = "population";
    private static final String KEY_PROFILECOMLETE = "profilecomplete";
    private static final String KEY_QUALIFICATION = "qualification";
    private static final String KEY_RAILPACKID = "railpackID";
    private static final String KEY_REQUEST = "request";
    private static final String KEY_SALT = "salt";
    private static final String KEY_SECURITIZATION = "securitization";


    private static final String KEY_SHOPNAME = "shopname";
    private static final String KEY_SHOPTYPE = "shopType";
    private static final String KEY_SHOPPHOTO = "shop_photo";
    private static final String KEY_STATE = "state";
    private static final String KEY_STATUS = "status";
    private static final String KEY_STEPONE = "stepone";
    private static final String KEY_STEPTHREE = "stepthree";
    private static final String KEY_STEPTO = "steptwo";
    private static final String KEY_TURNOVER = "turnover";
    private static final String KEY_UPDATEDAT = "updatedat";
    private static final String KEY_USERID = "userID";
    private static final String KEY_USERLIMIT = "userlimit";
    private static final String KEY_VALIDITYDATE = "validitydate";
    private static final String KEY_VOTERID = "voterid";
    private static final String KEY_WEBSITE = "website";
    private static final String KEY_LOGINPIN = "loginpin";



    private static SharedPrefManagerLogin mInstance;
    private static Context mCtx;

    private SharedPrefManagerLogin(Context context) {
        mCtx = context;
    }

    public static synchronized SharedPrefManagerLogin getInstance(Context context) {
        if (mInstance == null) {
            mInstance = new SharedPrefManagerLogin(context);
        }
        return mInstance;
    }

    public void userdata(Logindata loginToken) {
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(KEY_TOKEN, loginToken.getTokenkey());
        editor.putString(KEY_MSSAGE, loginToken.getMessage1());
        editor.putString(KEY_AADHARNUMBER, loginToken.getAadharnumber());
        editor.putString(KEY_ACCOUNTNO, loginToken.getAccountno());
        editor.putString(KEY_ADDEDBY, loginToken.getAddedBy());
        editor.putString(KEY_ADDEDON, loginToken.getAddedOn());
        editor.putString(KEY_ADDRESS, loginToken.getAddress());
        editor.putString(KEY_ADDRESS1, loginToken.getAddress1());
        editor.putString(KEY_ADDRESSPROOF, loginToken.getAddressproof());
        editor.putString(KEY_ADHARNO, loginToken.getAdharNo());
        editor.putString(KEY_AEPSOACKID, loginToken.getAepspackID());
        editor.putString(KEY_AGENCYID, loginToken.getAgencyID());
        editor.putString(KEY_AMAZONPACKID, loginToken.getAmazonpackID());
        editor.putString(KEY_APITOKEN, loginToken.getApitoken());
        editor.putString(KEY_BALANCE, loginToken.getBalance());
        editor.putString(KEY_BANKNAME, loginToken.getBankname());
        editor.putString(KEY_BBPPACKID, loginToken.getBbpspackID());
        editor.putString(KEY_BRANCHNAME, loginToken.getBranchname());
        editor.putString(KEY_BUSINESSYEAR, loginToken.getBusinessyear());
        editor.putString(KEY_BUSSINESS, loginToken.getBussiness());
        editor.putString(KEY_BUSPACKID, loginToken.getBuspackID());
        editor.putString(KEY_CITY, loginToken.getCity());
        editor.putString(KEY_CONTACTPERSON, loginToken.getContactPerson());
        editor.putString(KEY_COUNTRY, loginToken.getCountry());
        editor.putString(KEY_COUNTRYCODE, loginToken.getCountryCode());
        editor.putString(KEY_COORDINATOR, loginToken.getCoordinator());
        editor.putString(KEY_CREATEDAT, loginToken.getCreatedat());
        editor.putString(KEY_CREDITBALANCE, loginToken.getCreditbalance());
        editor.putString(KEY_CURRENCY, loginToken.getCurrency());

        editor.putString(KEY_CNAME, loginToken.getCname());
        editor.putString(KEY_DISTRICT, loginToken.getDistrict());
        editor.putString(KEY_DOB, loginToken.getDob());
        editor.putString(KEY_DRIVINGLICIENCE, loginToken.getDrivinglicience());
        editor.putString(KEY_DTHPACKID, loginToken.getDthpackID());
        editor.putString(KEY_EKYCCOM, loginToken.getEkyccom());
        editor.putString(KEY_EMAIL, loginToken.getEmail());
        editor.putString(KEY_EMAILID, loginToken.getEmailID());
        editor.putString(KEY_FAX, loginToken.getFax());
        editor.putString(KEY_FLIGHTPACKID, loginToken.getFlightpackID());
        editor.putString(KEY_FRIM, loginToken.getFrim());
        editor.putString(KEY_GENDER, loginToken.getGender());
        editor.putString(KEY_GROUPID, loginToken.getGroupID());
        editor.putString(KEY_HOTLEPACKID, loginToken.getHotlepackID());
        editor.putString(KEY_IATA, loginToken.getIata());
        editor.putString(KEY_IFSCODE, loginToken.getIfsccode());
        editor.putString(KEY_LATUTUDE, loginToken.getLatutude());
        editor.putString(KEY_LOCATIONTYPE, loginToken.getLocationType());
        editor.putString(KEY_LOGO, loginToken.getLogo());
        editor.putString(KEY_LONGITUDE, loginToken.getLongitude());
        editor.putString(KEY_MINBLANCE, loginToken.getMinbalance());
        editor.putString(KEY_MOBILE, loginToken.getMobile());
        editor.putString(KEY_MOBILEPACKID, loginToken.getMobilepackID());
        editor.putString(KEY_MONEYPACKID, loginToken.getMoneypackID());
        editor.putString(KEY_NAME, loginToken.getName());
        editor.putString(KEY_NEWAGENCY, loginToken.getNewagency());


        editor.putString(KEY_OFFICESACE, loginToken.getOfficesapce());
        editor.putString(KEY_PACKID, loginToken.getPackID());
        editor.putString(KEY_PANNNUMBER, loginToken.getPannumber());
        editor.putString(KEY_PANPACKID, loginToken.getPanpackID());
        editor.putString(KEY_PASSPORT, loginToken.getPassport());
        editor.putString(KEY_PERMISSION, loginToken.getPermission());
        editor.putString(KEY_PHONENO, loginToken.getPhoneno());
        editor.putString(KEY_PHOTO, loginToken.getPhoto());
        editor.putString(KEY_PINCODE, loginToken.getPincode());
        editor.putString(KEY_POPULATION, loginToken.getPopulation());
        editor.putString(KEY_PROFILECOMLETE, loginToken.getProfilecomplete());
        editor.putString(KEY_QUALIFICATION, loginToken.getQualification());
        editor.putString(KEY_RAILPACKID, loginToken.getRailpackID());
        editor.putString(KEY_REQUEST, loginToken.getRequest());
        editor.putString(KEY_SALT, loginToken.getSalt());
        editor.putString(KEY_SECURITIZATION, loginToken.getSecuritization());
        editor.putString(KEY_SHOPNAME, loginToken.getShopname());
        editor.putString(KEY_SHOPTYPE, loginToken.getShopType());
        editor.putString(KEY_SHOPPHOTO, loginToken.getShop_photo());
        editor.putString(KEY_STATE, loginToken.getState());
        editor.putString(KEY_STATUS, loginToken.getStatus());
        editor.putString(KEY_STEPONE, loginToken.getStepone());
        editor.putString(KEY_STEPTHREE, loginToken.getStepthree());
        editor.putString(KEY_STEPTO, loginToken.getSteptwo());
        editor.putString(KEY_TURNOVER, loginToken.getTurnover());
        editor.putString(KEY_UPDATEDAT, loginToken.getUpdatedat());
        editor.putString(KEY_USERID, loginToken.getUserID());
        editor.putString(KEY_USERLIMIT, loginToken.getUserlimit());
        editor.putString(KEY_VALIDITYDATE, loginToken.getValiditydate());
        editor.putString(KEY_VOTERID, loginToken.getVoterid());
        editor.putString(KEY_WEBSITE, loginToken.getWebsite());
        editor.apply();
    }
    public Logindata getLogindata() {
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        return new Logindata(
                sharedPreferences.getString(KEY_TOKEN, null),
                sharedPreferences.getString(KEY_MSSAGE, null),
                sharedPreferences.getString(KEY_AADHARNUMBER, null),
                sharedPreferences.getString(KEY_ACCOUNTNO, null),
                sharedPreferences.getString(KEY_ADDEDBY, null),
                sharedPreferences.getString(KEY_ADDEDON, null),

                sharedPreferences.getString(KEY_ADDRESS, null),
                sharedPreferences.getString(KEY_ADDRESS1, null),
                sharedPreferences.getString(KEY_ADDRESSPROOF, null),
                sharedPreferences.getString(KEY_ADHARNO, null),
                sharedPreferences.getString(KEY_AEPSOACKID, null),
                sharedPreferences.getString(KEY_AGENCYID, null),


                sharedPreferences.getString(KEY_AMAZONPACKID, null),
                sharedPreferences.getString(KEY_APITOKEN, null),
                sharedPreferences.getString(KEY_BALANCE, null),
                sharedPreferences.getString(KEY_BANKNAME, null),
                sharedPreferences.getString(KEY_BBPPACKID, null),
                sharedPreferences.getString(KEY_BRANCHNAME, null),
                sharedPreferences.getString(KEY_BUSINESSYEAR, null),
                sharedPreferences.getString(KEY_BUSSINESS, null),
                sharedPreferences.getString(KEY_BUSPACKID, null),
                sharedPreferences.getString(KEY_CITY, null),
                sharedPreferences.getString(KEY_CONTACTPERSON, null),
                sharedPreferences.getString(KEY_COUNTRY, null),
                sharedPreferences.getString(KEY_COUNTRYCODE, null),
                sharedPreferences.getString(KEY_COORDINATOR, null),
                sharedPreferences.getString(KEY_CREATEDAT, null),
                sharedPreferences.getString(KEY_CREDITBALANCE, null),
                sharedPreferences.getString(KEY_CURRENCY, null),
                sharedPreferences.getString(KEY_CNAME, null),
                sharedPreferences.getString(KEY_DISTRICT, null),
                sharedPreferences.getString(KEY_DOB, null),
                sharedPreferences.getString(KEY_DRIVINGLICIENCE, null),
                sharedPreferences.getString(KEY_DTHPACKID, null),
                sharedPreferences.getString(KEY_EKYCCOM, null),
                sharedPreferences.getString(KEY_EMAIL, null),
                sharedPreferences.getString(KEY_EMAILID, null),
                sharedPreferences.getString(KEY_FAX, null),
                sharedPreferences.getString(KEY_FLIGHTPACKID, null),
                sharedPreferences.getString(KEY_FRIM, null),
                sharedPreferences.getString(KEY_GENDER, null),
                sharedPreferences.getString(KEY_GROUPID, null),
                sharedPreferences.getString(KEY_HOTLEPACKID, null),
                sharedPreferences.getString(KEY_IATA, null),
                sharedPreferences.getString(KEY_IFSCODE, null),
                sharedPreferences.getString(KEY_LATUTUDE, null),
                sharedPreferences.getString(KEY_LOCATIONTYPE, null),
                sharedPreferences.getString(KEY_LOGO, null),
                sharedPreferences.getString(KEY_LONGITUDE, null),
                sharedPreferences.getString(KEY_MINBLANCE, null),
                sharedPreferences.getString(KEY_MOBILE, null),

                sharedPreferences.getString(KEY_MOBILEPACKID, null),
                sharedPreferences.getString(KEY_MONEYPACKID, null),
                sharedPreferences.getString(KEY_NAME, null),
                sharedPreferences.getString(KEY_NEWAGENCY, null),
                sharedPreferences.getString(KEY_OFFICESACE, null),
                sharedPreferences.getString(KEY_PACKID, null),
                sharedPreferences.getString(KEY_PANNNUMBER, null),
                sharedPreferences.getString(KEY_PANPACKID, null),

                sharedPreferences.getString(KEY_PASSPORT, null),
                sharedPreferences.getString(KEY_PERMISSION, null),
                sharedPreferences.getString(KEY_PHONENO, null),
                sharedPreferences.getString(KEY_PHOTO, null),
                sharedPreferences.getString(KEY_PINCODE, null),
                sharedPreferences.getString(KEY_POPULATION, null),
                sharedPreferences.getString(KEY_PROFILECOMLETE, null),
                sharedPreferences.getString(KEY_QUALIFICATION, null),

                sharedPreferences.getString(KEY_RAILPACKID, null),
                sharedPreferences.getString(KEY_REQUEST, null),
                sharedPreferences.getString(KEY_SALT, null),
                sharedPreferences.getString(KEY_SECURITIZATION, null),

                sharedPreferences.getString(KEY_SHOPNAME, null),
                sharedPreferences.getString(KEY_SHOPTYPE, null),
                sharedPreferences.getString(KEY_SHOPPHOTO, null),
                sharedPreferences.getString(KEY_STATE, null),
                sharedPreferences.getString(KEY_STATUS, null),
                sharedPreferences.getString(KEY_STEPONE, null),
                sharedPreferences.getString(KEY_STEPTHREE, null),
                sharedPreferences.getString(KEY_STEPTO, null),
                sharedPreferences.getString(KEY_TURNOVER, null),
                sharedPreferences.getString(KEY_UPDATEDAT, null),
                sharedPreferences.getString(KEY_USERID, null),
                sharedPreferences.getString(KEY_USERLIMIT, null),
                sharedPreferences.getString(KEY_VALIDITYDATE, null),
                sharedPreferences.getString(KEY_VOTERID, null),
                sharedPreferences.getString(KEY_WEBSITE, null),
                sharedPreferences.getString(KEY_LOGINPIN, null)

                );
    }
    public boolean isLoggedIn() {
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        return sharedPreferences.getString(KEY_TOKEN, null) != null;
    }


    public String getKeyToken(){
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        return sharedPreferences.getString(KEY_TOKEN, null);
    }
    public void clear(){
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.clear();
        editor.commit();
    }

//    public void logout() {
//        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
//        SharedPreferences.Editor editor = sharedPreferences.edit();
//        editor.clear();
//        editor.apply();
//        mCtx.startActivity(new Intent(mCtx, Login.class));
//    }

    public void logout() {
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.clear();
        editor.apply();
        Intent intent = new Intent(mCtx, Login.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        mCtx.startActivity(intent);
        // mCtx.startActivity(new Intent(mCtx, LoginActivity.class));
    }
}
