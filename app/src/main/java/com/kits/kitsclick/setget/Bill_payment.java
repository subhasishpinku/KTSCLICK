package com.kits.kitsclick.setget;

public class Bill_payment {
    private String billname;
    private String img;
    public Bill_payment(String billname,String img){

        this.billname=billname;
        this.img=img;
    }

    public String getBillname() {
        return billname;
    }

    public void setBillname(String billname) {
        this.billname = billname;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }
}
