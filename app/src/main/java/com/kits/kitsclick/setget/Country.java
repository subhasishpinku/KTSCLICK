package com.kits.kitsclick.setget;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Country {
    @SerializedName("error")
    @Expose
    private String error;

    @SerializedName("data")
    @Expose
    private Datadataviewconuntry data;

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public Datadataviewconuntry getData() {
        return data;
    }

    public void setData(Datadataviewconuntry data) {
        this.data = data;
    }
}
