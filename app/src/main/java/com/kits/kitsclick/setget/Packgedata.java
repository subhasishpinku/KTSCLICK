package com.kits.kitsclick.setget;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class Packgedata {
//    @SerializedName("error")
//    @Expose
//    private String error;

    @SerializedName("list")
    @Expose
    private ArrayList<Packagelist> list;

//    public String getError() {
//        return error;
//    }
//
//    public void setError(String error) {
//        this.error = error;
//    }

    public ArrayList<Packagelist> getList() {
        return list;
    }

    public void setList(ArrayList<Packagelist> list) {
        this.list = list;
    }
}
