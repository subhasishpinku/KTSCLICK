package com.kits.kitsclick.setget;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class State {
    @SerializedName("error")
    @Expose
    private String error;

    @SerializedName("data")
    @Expose
    private Datadataviewstate data;

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public Datadataviewstate getData() {
        return data;
    }

    public void setData(Datadataviewstate data) {
        this.data = data;
    }
}
