package com.kits.kitsclick.setget;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class DatadataviewMonth {
    @SerializedName("list")
    @Expose
    private ArrayList<ListdataMonth> list;

    public ArrayList<ListdataMonth> getList() {
        return list;
    }

    public void setList(ArrayList<ListdataMonth> list) {
        this.list = list;
    }
}
