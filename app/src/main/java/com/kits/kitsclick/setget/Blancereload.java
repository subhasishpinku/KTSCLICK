package com.kits.kitsclick.setget;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Blancereload {
    @SerializedName("error")
    @Expose
    private String error;

    @SerializedName("data")
    @Expose
    private Balancedata data;

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public Balancedata getData() {
        return data;
    }

    public void setData(Balancedata data) {
        this.data = data;
    }
}
