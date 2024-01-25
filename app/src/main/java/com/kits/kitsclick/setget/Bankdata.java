package com.kits.kitsclick.setget;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class Bankdata {
    @SerializedName("list")
    @Expose
    private ArrayList<Listbankdata> list;


    public ArrayList<Listbankdata> getList() {
        return list;
    }

    public void setList(ArrayList<Listbankdata> list) {
        this.list = list;
    }
}
