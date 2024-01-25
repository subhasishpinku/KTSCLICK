package com.kits.kitsclick.setget;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class Daysdataview {
    @SerializedName("list")
    @Expose
    private ArrayList<Listdataday> list;

    public ArrayList<Listdataday> getList() {
        return list;
    }

    public void setList(ArrayList<Listdataday> list) {
        this.list = list;
    }
}
