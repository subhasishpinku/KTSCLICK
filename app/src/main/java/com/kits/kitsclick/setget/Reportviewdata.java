package com.kits.kitsclick.setget;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class Reportviewdata {
    @SerializedName("page")
    @Expose
    private String page;

    @SerializedName("total_page")
    @Expose
    private String totalpage;

    @SerializedName("total_record")
    @Expose
    private String totalrecord;

    @SerializedName("list")
    @Expose
    private ArrayList<ListReportview> list;

    public String getPage() {
        return page;
    }

    public void setPage(String page) {
        this.page = page;
    }

    public String getTotalpage() {
        return totalpage;
    }

    public void setTotalpage(String totalpage) {
        this.totalpage = totalpage;
    }

    public String getTotalrecord() {
        return totalrecord;
    }

    public void setTotalrecord(String totalrecord) {
        this.totalrecord = totalrecord;
    }

    public ArrayList<ListReportview> getList() {
        return list;
    }

    public void setList(ArrayList<ListReportview> list) {
        this.list = list;
    }
}
