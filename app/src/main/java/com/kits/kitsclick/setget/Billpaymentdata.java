package com.kits.kitsclick.setget;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class Billpaymentdata {
    @SerializedName("list")
    @Expose
    private ArrayList<Listdatabilpayment> list;

    public ArrayList<Listdatabilpayment> getList() {
        return list;
    }

    public void setList(ArrayList<Listdatabilpayment> list) {
        this.list = list;
    }
}
