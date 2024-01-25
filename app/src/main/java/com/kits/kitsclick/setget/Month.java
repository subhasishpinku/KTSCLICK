package com.kits.kitsclick.setget;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Month {
    @SerializedName("error")
    @Expose
    private String error;

    @SerializedName("data")
    @Expose
    private DatadataviewMonth data;

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public DatadataviewMonth getData() {
        return data;
    }

    public void setData(DatadataviewMonth data) {
        this.data = data;
    }
}
