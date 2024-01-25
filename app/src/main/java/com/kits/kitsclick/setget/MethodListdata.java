package com.kits.kitsclick.setget;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class MethodListdata {
    @SerializedName("list")
    @Expose
    private ArrayList<ListMethodListdata> list;

    public ArrayList<ListMethodListdata> getList() {
        return list;
    }

    public void setList(ArrayList<ListMethodListdata> list) {
        this.list = list;
    }
}
