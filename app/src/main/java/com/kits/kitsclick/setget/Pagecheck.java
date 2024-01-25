package com.kits.kitsclick.setget;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Pagecheck {
    @SerializedName("error")
    @Expose
    private String error;

    @SerializedName("data")
    @Expose
    private Packgedata data;

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public Packgedata getData() {
        return data;
    }

    public void setData(Packgedata data) {
        this.data = data;
    }
}
