package com.kits.kitsclick.setget;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Busnesstype {
    @SerializedName("error")
    @Expose
    private String error;

    @SerializedName("data")
    @Expose
    private Busnessdataview data;

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public Busnessdataview getData() {
        return data;
    }

    public void setData(Busnessdataview data) {
        this.data = data;
    }
}
