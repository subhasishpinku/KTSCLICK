package com.kits.kitsclick.setget;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Days {
    @SerializedName("error")
    @Expose
    private String error;

    @SerializedName("data")
    @Expose
    private Daysdataview data;

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public Daysdataview getData() {
        return data;
    }

    public void setData(Daysdataview data) {
        this.data = data;
    }
}
