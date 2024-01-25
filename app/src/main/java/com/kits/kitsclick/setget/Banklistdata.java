package com.kits.kitsclick.setget;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class Banklistdata {
    @SerializedName("list")
    @Expose
    private ArrayList<Listbankdatalist> list;

    public ArrayList<Listbankdatalist> getList() {
        return list;
    }

    public void setList(ArrayList<Listbankdatalist> list) {
        this.list = list;
    }
}
