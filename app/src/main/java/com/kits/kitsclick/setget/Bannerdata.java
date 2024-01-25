package com.kits.kitsclick.setget;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class Bannerdata {
    @SerializedName("error")
    @Expose
    private String error;

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public Bannerdatavalue getData() {
        return data;
    }

    public void setData(Bannerdatavalue data) {
        this.data = data;
    }

    @SerializedName("data")
    @Expose
    private Bannerdatavalue data;

//    private ArrayList<Reportdataview> data;
}
