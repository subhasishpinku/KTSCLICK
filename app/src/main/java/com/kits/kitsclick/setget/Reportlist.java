package com.kits.kitsclick.setget;

public class Reportlist {
    private String page;
    private String totalpage;

    public Reportlist(String page,String totalpage){
        this.page=page;
        this.totalpage=totalpage;

    }

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
}
