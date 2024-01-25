package com.kits.kitsclick.setget;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Mobileoperator {
    @SerializedName("error")
    @Expose
    private String error;

    @SerializedName("data")
    @Expose
    private Mobleopertordata data;

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public Mobleopertordata getData() {
        return data;
    }

    public void setData(Mobleopertordata data) {
        this.data = data;
    }
}
