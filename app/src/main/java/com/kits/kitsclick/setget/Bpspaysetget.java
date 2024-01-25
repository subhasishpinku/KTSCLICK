package com.kits.kitsclick.setget;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Bpspaysetget {
    @SerializedName("reqid")
    @Expose
    private String reqid;

    @SerializedName("biller_id")
    @Expose
    private String billerid;

    @SerializedName("biller_Name")
    @Expose
    private String billerName;

    @SerializedName("bbps_params")
    @Expose
    private String bbpsparams;

    @SerializedName("bbps_static_params")
    @Expose
    private String bbpsstaticparams;

    public String getReqid() {
        return reqid;
    }

    public String getBbpsstaticparams() {
        return bbpsstaticparams;
    }

    public void setBbpsstaticparams(String bbpsstaticparams) {
        this.bbpsstaticparams = bbpsstaticparams;
    }

    public void setReqid(String reqid) {
        this.reqid = reqid;
    }

    public String getBillerid() {
        return billerid;
    }

    public void setBillerid(String billerid) {
        this.billerid = billerid;
    }

    public String getBillerName() {
        return billerName;
    }

    public void setBillerName(String billerName) {
        this.billerName = billerName;
    }

    public String getBbpsparams() {
        return bbpsparams;
    }

    public void setBbpsparams(String bbpsparams) {
        this.bbpsparams = bbpsparams;
    }
}
