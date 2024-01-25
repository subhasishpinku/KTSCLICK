package com.kits.kitsclick.setget;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class BillerDetailsdata {
    @SerializedName("details")
    @Expose
    private BillerDetailsdetails details;

    public BillerDetailsdetails getDetails() {
        return details;
    }

    public void setDetails(BillerDetailsdetails details) {
        this.details = details;
    }
}
