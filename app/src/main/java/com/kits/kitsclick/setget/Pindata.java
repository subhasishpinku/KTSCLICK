package com.kits.kitsclick.setget;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class Pindata {
    @SerializedName("loginpin")
    @Expose
    private String loginpin;

    public String getLoginpin() {
        return loginpin;
    }

    public void setLoginpin(String loginpin) {
        this.loginpin = loginpin;
    }
}
