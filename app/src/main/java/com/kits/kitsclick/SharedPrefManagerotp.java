package com.kits.kitsclick;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;

import com.kits.kitsclick.setget.Otptoken;

public class SharedPrefManagerotp {
    private static final String SHARED_PREF_NAME = "cutselfi";
    private static final String KEY_PHONE = "otp";
    private static SharedPrefManagerotp mInstance;
    private static Context mCtx;

    private SharedPrefManagerotp(Context context) {
        mCtx = context;
    }

    public static synchronized SharedPrefManagerotp getInstance(Context context) {
        if (mInstance == null) {
            mInstance = new SharedPrefManagerotp(context);
        }
        return mInstance;
    }

    public void userphoneno(Otptoken otptoken) {
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(KEY_PHONE, otptoken.getPhonenumberkey());
        editor.apply();
    }

    public boolean isLoggedIn() {
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        return sharedPreferences.getString(KEY_PHONE, null) != null;
    }


    public String getKeyPhonenumber(){
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        return sharedPreferences.getString(KEY_PHONE, null);
    }



}
