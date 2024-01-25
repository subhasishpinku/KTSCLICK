package com.kits.kitsclick.setget;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class BillerDetailsdetails {
    @SerializedName("name")
    @Expose
    private String name;

    @SerializedName("biller_id")
    @Expose
    private String biller_id;

    @SerializedName("req_id")
    @Expose
    private String req_id;

    @SerializedName("pay_type")
    @Expose
    private String pay_type;

    public String getPay_type() {
        return pay_type;
    }

    public void setPay_type(String pay_type) {
        this.pay_type = pay_type;
    }

    @SerializedName("params")
    @Expose
    private ArrayList<ListBillerDetailsparams> params;

    public ArrayList<ListBillerDetailsstaticparams> getStaticparams() {
        return staticparams;
    }

    public void setStaticparams(ArrayList<ListBillerDetailsstaticparams> staticparams) {
        this.staticparams = staticparams;
    }

    @SerializedName("staticparams")
    @Expose
    private ArrayList<ListBillerDetailsstaticparams> staticparams;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBiller_id() {
        return biller_id;
    }

    public void setBiller_id(String biller_id) {
        this.biller_id = biller_id;
    }

    public String getReq_id() {
        return req_id;
    }

    public void setReq_id(String req_id) {
        this.req_id = req_id;
    }

    public ArrayList<ListBillerDetailsparams> getParams() {
        return params;
    }

    public void setParams(ArrayList<ListBillerDetailsparams> params) {
        this.params = params;
    }
}
