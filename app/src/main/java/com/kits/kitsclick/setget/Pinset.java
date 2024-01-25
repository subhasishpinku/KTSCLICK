package com.kits.kitsclick.setget;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Pinset {
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
