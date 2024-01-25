package com.kits.kitsclick.setget;

public class Travel {
    private String itemid;
    private String itemname;
    private int img;
    public Travel(String itemid, String itemname, int img){
        this.itemid = itemid;
        this.itemname = itemname;
        this.img = img;
    }

    public String getItemid() {
        return itemid;
    }

    public void setItemid(String itemid) {
        this.itemid = itemid;
    }

    public String getItemname() {
        return itemname;
    }

    public void setItemname(String itemname) {
        this.itemname = itemname;
    }

    public int getImg() {
        return img;
    }

    public void setImg(int img) {
        this.img = img;
    }
}
