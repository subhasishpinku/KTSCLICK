package com.kits.kitsclick.setget;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class Mobleopertordata {
    @SerializedName("list")
    @Expose
    private ArrayList<Mobleoperatorlist> list;

    public ArrayList<Mobleoperatorlist> getList() {
        return list;
    }

    public void setList(ArrayList<Mobleoperatorlist> list) {
        this.list = list;
    }
}
