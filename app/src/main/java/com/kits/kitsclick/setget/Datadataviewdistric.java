package com.kits.kitsclick.setget;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class Datadataviewdistric {
    @SerializedName("list")
    @Expose
    private ArrayList<Listdatadistric> list;


    public ArrayList<Listdatadistric> getList() {
        return list;
    }

    public void setList(ArrayList<Listdatadistric> list) {
        this.list = list;
    }
}
