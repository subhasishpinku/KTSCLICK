package com.kits.kitsclick.setget;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Distric {
    @SerializedName("error")
    @Expose
    private String error;

    @SerializedName("data")
    @Expose
    private Datadataviewdistric data;

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public Datadataviewdistric getData() {
        return data;
    }

    public void setData(Datadataviewdistric data) {
        this.data = data;
    }
}
