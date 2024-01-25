package com.kits.kitsclick.setget;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Securitirizationdata {
    @SerializedName("error")
    @Expose
    private String error;

    @SerializedName("data")
    @Expose
    private Datadataviewsecuritirization data;

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public Datadataviewsecuritirization getData() {
        return data;
    }

    public void setData(Datadataviewsecuritirization data) {
        this.data = data;
    }
}
