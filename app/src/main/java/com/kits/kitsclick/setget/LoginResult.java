package com.kits.kitsclick.setget;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class LoginResult {
    @SerializedName("error")
    @Expose
    private String error;
    @SerializedName("data")
    @Expose
    private Dataview data;

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public Dataview getData() {
        return data;
    }

    public void setData(Dataview data) {
        this.data = data;
    }
}
