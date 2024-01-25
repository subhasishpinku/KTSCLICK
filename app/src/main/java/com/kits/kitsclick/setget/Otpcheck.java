package com.kits.kitsclick.setget;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Otpcheck {
    @SerializedName("error")
    @Expose
    private String error;

    @SerializedName("message")
    @Expose
    private String message;

//    @SerializedName("data")
//    @Expose
//    private Dataviewotp data;

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

//    public Dataviewotp getData() {
//        return data;
//    }
//
//    public void setData(Dataviewotp data) {
//        this.data = data;
//    }
}
