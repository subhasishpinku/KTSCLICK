package com.kits.kitsclick.setget;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class Datadataview {
    @SerializedName("list")
    @Expose
    private ArrayList<Listdata> list;

    public ArrayList<Listdata> getList() {
        return list;
    }

    public void setList(ArrayList<Listdata> list) {
        this.list = list;
    }
}
