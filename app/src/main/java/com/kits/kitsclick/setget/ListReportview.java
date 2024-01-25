package com.kits.kitsclick.setget;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ListReportview {
    @SerializedName("name")
    @Expose
    private String name;

    @SerializedName("agency_ID")
    @Expose
    private String agencyID;

    @SerializedName("merchantTransactionId")
    @Expose
    private String merchantTransactionId;

    @SerializedName("fpTransactionId")
    @Expose
    private String fpTransactionId;

    @SerializedName("service")
    @Expose
    private String service;

    @SerializedName("bankRRN")
    @Expose
    private String bankRRN;

    @SerializedName("balanceAmount")
    @Expose
    private String balanceAmount;

    @SerializedName("requestTransactionTime")
    @Expose
    private String requestTransactionTime;

    @SerializedName("transactionAmount")
    @Expose
    private String transactionAmount;

    @SerializedName("transactionStatus")
    @Expose
    private String transactionStatus;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAgencyID() {
        return agencyID;
    }

    public void setAgencyID(String agencyID) {
        this.agencyID = agencyID;
    }

    public String getMerchantTransactionId() {
        return merchantTransactionId;
    }

    public void setMerchantTransactionId(String merchantTransactionId) {
        this.merchantTransactionId = merchantTransactionId;
    }

    public String getFpTransactionId() {
        return fpTransactionId;
    }

    public void setFpTransactionId(String fpTransactionId) {
        this.fpTransactionId = fpTransactionId;
    }

    public String getService() {
        return service;
    }

    public void setService(String service) {
        this.service = service;
    }

    public String getBankRRN() {
        return bankRRN;
    }

    public void setBankRRN(String bankRRN) {
        this.bankRRN = bankRRN;
    }

    public String getBalanceAmount() {
        return balanceAmount;
    }

    public void setBalanceAmount(String balanceAmount) {
        this.balanceAmount = balanceAmount;
    }

    public String getRequestTransactionTime() {
        return requestTransactionTime;
    }

    public void setRequestTransactionTime(String requestTransactionTime) {
        this.requestTransactionTime = requestTransactionTime;
    }

    public String getTransactionAmount() {
        return transactionAmount;
    }

    public void setTransactionAmount(String transactionAmount) {
        this.transactionAmount = transactionAmount;
    }

    public String getTransactionStatus() {
        return transactionStatus;
    }

    public void setTransactionStatus(String transactionStatus) {
        this.transactionStatus = transactionStatus;
    }
}
