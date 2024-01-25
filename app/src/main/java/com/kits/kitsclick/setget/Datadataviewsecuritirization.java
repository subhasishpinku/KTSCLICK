package com.kits.kitsclick.setget;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class Datadataviewsecuritirization {
    @SerializedName("list")
    @Expose
    private ArrayList<Listdatasecuritirization> list;

    public ArrayList<Listdatasecuritirization> getList() {
        return list;
    }

    public void setList(ArrayList<Listdatasecuritirization> list) {
        this.list = list;
    }
}
