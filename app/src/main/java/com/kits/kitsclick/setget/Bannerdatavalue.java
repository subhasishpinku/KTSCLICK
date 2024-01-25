package com.kits.kitsclick.setget;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class Bannerdatavalue {
    @SerializedName("list")
    @Expose
    private ArrayList<Listbanner> list;

    public ArrayList<Listbanner> getList() {
        return list;
    }

    public void setList(ArrayList<Listbanner> list) {
        this.list = list;
    }
}
