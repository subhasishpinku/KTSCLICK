package com.kits.kitsclick.setget;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class Reportvalue {
    @SerializedName("error")
    @Expose
    private String error;

    @SerializedName("data")
    @Expose
    private ArrayList<Reportdataview> data;

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public ArrayList<Reportdataview> getData() {
        return data;
    }

    public void setData(ArrayList<Reportdataview> data) {
        this.data = data;
    }
}
