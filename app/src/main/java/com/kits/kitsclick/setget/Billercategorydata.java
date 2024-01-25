package com.kits.kitsclick.setget;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class Billercategorydata {
    @SerializedName("list")
    @Expose
    private ArrayList<ListBillercategory> list;

    public ArrayList<ListBillercategory> getList() {
        return list;
    }

    public void setList(ArrayList<ListBillercategory> list) {
        this.list = list;
    }
}
