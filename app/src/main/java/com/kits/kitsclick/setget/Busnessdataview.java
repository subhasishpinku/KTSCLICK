package com.kits.kitsclick.setget;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class Busnessdataview {
    @SerializedName("list")
    @Expose
    private ArrayList<Listdatabusness> list;

    public ArrayList<Listdatabusness> getList() {
        return list;
    }

    public void setList(ArrayList<Listdatabusness> list) {
        this.list = list;
    }
}
