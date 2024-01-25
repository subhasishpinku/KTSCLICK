package com.kits.kitsclick.setget;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Billpaymentservice {
    @SerializedName("error")
    @Expose
    private String error;
    @SerializedName("data")
    @Expose
    private Billpaymentdata data;

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public Billpaymentdata getData() {
        return data;
    }

    public void setData(Billpaymentdata data) {
        this.data = data;
    }
}
