package com.kits.kitsclick.setget;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Mobilerecharedata {
    @SerializedName("error")
    @Expose
    private String error;

    @SerializedName("message")
    @Expose
    private String message;

    @SerializedName("recharge_detail")
    @Expose
    private Rechargedetail rechargedetail;

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Rechargedetail getRechargedetail() {
        return rechargedetail;
    }

    public void setRechargedetail(Rechargedetail rechargedetail) {
        this.rechargedetail = rechargedetail;
    }
}
