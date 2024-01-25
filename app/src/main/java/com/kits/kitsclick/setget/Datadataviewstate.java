package com.kits.kitsclick.setget;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class Datadataviewstate {
    @SerializedName("list")
    @Expose
    private ArrayList<Listdatastate> list;

    public ArrayList<Listdatastate> getList() {
        return list;
    }

    public void setList(ArrayList<Listdatastate> list) {
        this.list = list;
    }
}
