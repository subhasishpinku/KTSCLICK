package com.kits.kitsclick.setget;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class Devicedata {
    @SerializedName("list")
    @Expose
    private ArrayList<Listdatadevice> list;

    public ArrayList<Listdatadevice> getList() {
        return list;
    }

    public void setList(ArrayList<Listdatadevice> list) {
        this.list = list;
    }
}
