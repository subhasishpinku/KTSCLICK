package com.kits.kitsclick.setget;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class BillerDetails {
    @SerializedName("error")
    @Expose
    private String error;

    @SerializedName("data")
    @Expose
    private BillerDetailsdata data;

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public BillerDetailsdata getData() {
        return data;
    }

    public void setData(BillerDetailsdata data) {
        this.data = data;
    }
}
