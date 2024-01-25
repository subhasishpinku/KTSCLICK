package com.kits.kitsclick.paytem;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.kits.kitsclick.setget.Paytemdata;

public class Paytemsetget {
    @SerializedName("error")
    @Expose
    private String error;

    @SerializedName("data")
    @Expose
    private Paytemdata data;

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public Paytemdata getData() {
        return data;
    }

    public void setData(Paytemdata data) {
        this.data = data;
    }
}
