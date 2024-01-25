package com.kits.kitsclick.setget;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Notificationservice {
    @SerializedName("error")
    @Expose
    private String error;

    @SerializedName("data")
    @Expose
    private Datadatanotification data;

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public Datadatanotification getData() {
        return data;
    }

    public void setData(Datadatanotification data) {
        this.data = data;
    }
}
