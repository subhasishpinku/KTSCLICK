package com.kits.kitsclick.setget;

public class Payinlist {
    private String name;
    private String nameid;
    public Payinlist(String name,String nameid){
        this.name=name;
        this.nameid=nameid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNameid() {
        return nameid;
    }

    public void setNameid(String nameid) {
        this.nameid = nameid;
    }
}
