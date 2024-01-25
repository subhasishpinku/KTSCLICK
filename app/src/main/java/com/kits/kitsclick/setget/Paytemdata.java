package com.kits.kitsclick.setget;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Paytemdata {
    @SerializedName("checksum")
    @Expose
    private String checksum;

    @SerializedName("ORDER_ID")
    @Expose
    private String orderid;

    @SerializedName("payt_STATUS")
    @Expose
    private String paytstatus;

    @SerializedName("CUST_ID")
    @Expose
    private String CUSTID;

    @SerializedName("MID")
    @Expose
    private String mid;

    @SerializedName("CHANNEL_ID")
    @Expose
    private String channelid;

    @SerializedName("TXN_AMOUNT")
    @Expose
    private String txnamount;

    @SerializedName("INDUSTRY_TYPE_ID")
    @Expose
    private String industrytype;

    @SerializedName("WEBSITE")
    @Expose
    private String website;


    public String getMid() {
        return mid;
    }

    public void setMid(String mid) {
        this.mid = mid;
    }

    public String getChannelid() {
        return channelid;
    }

    public void setChannelid(String channelid) {
        this.channelid = channelid;
    }

    public String getTxnamount() {
        return txnamount;
    }

    public void setTxnamount(String txnamount) {
        this.txnamount = txnamount;
    }

    public String getIndustrytype() {
        return industrytype;
    }

    public void setIndustrytype(String industrytype) {
        this.industrytype = industrytype;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public String getMerchentkey() {
        return merchentkey;
    }

    public void setMerchentkey(String merchentkey) {
        this.merchentkey = merchentkey;
    }

    @SerializedName("MERCHANT_KEY")
    @Expose
    private String merchentkey;

    public String getChecksum() {
        return checksum;
    }

    public void setChecksum(String checksum) {
        this.checksum = checksum;
    }

    public String getOrderid() {
        return orderid;
    }

    public void setOrderid(String orderid) {
        this.orderid = orderid;
    }

    public String getPaytstatus() {
        return paytstatus;
    }

    public String getCUSTID() {
        return CUSTID;
    }

    public void setCUSTID(String CUSTID) {
        this.CUSTID = CUSTID;
    }

    public void setPaytstatus(String paytstatus) {
        this.paytstatus = paytstatus;
    }
}
