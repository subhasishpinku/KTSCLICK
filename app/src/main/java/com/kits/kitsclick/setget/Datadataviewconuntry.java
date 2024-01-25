package com.kits.kitsclick.setget;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class Datadataviewconuntry {
    @SerializedName("list")
    @Expose
    private ArrayList<Listdatacountry> list;

    public ArrayList<Listdatacountry> getList() {
        return list;
    }

    public void setList(ArrayList<Listdatacountry> list) {
        this.list = list;
    }
}
