package com.kits.kitsclick.setget;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Mobiledatarechagevalue {
    @SerializedName("operator")
    @Expose
    private String operator;

    @SerializedName("mobile")
    @Expose
    private String mobile;

    @SerializedName("amount")
    @Expose
    private String amount;

    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }
}
