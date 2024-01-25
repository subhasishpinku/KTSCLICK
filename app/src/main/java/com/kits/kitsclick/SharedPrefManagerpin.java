package com.kits.kitsclick;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;

import com.kits.kitsclick.setget.Otptoken;
import com.kits.kitsclick.setget.Pinsetdata;

public class SharedPrefManagerpin {
    private static final String SHARED_PREF_NAME = "kts";
    private static final String KEY_PIN = "pin";
    private static SharedPrefManagerpin mInstance;
    private static Context mCtx;

    private SharedPrefManagerpin(Context context) {
        mCtx = context;
    }

    public static synchronized SharedPrefManagerpin getInstance(Context context) {
        if (mInstance == null) {
            mInstance = new SharedPrefManagerpin(context);
        }
        return mInstance;
    }

    public void userping(Pinsetdata otptoken) {
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(KEY_PIN, otptoken.getPinsetdata());
        editor.apply();
    }

    public boolean isLoggedIn() {
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        return sharedPreferences.getString(KEY_PIN, null) != null;
    }


    public String getKeyPin(){
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        return sharedPreferences.getString(KEY_PIN, null);
    }

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
    public void clear(){
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.clear();
        editor.commit();
    }
}
