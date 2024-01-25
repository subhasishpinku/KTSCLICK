package com.kits.kitsclick.setget;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Listdatadevice {
    @SerializedName("company")
    @Expose
    private String company;

    @SerializedName("model")
    @Expose
    private String model;

    @SerializedName("serial")
    @Expose
    private String serial;

    @SerializedName("div_type")
    @Expose
    private String divtype;

    @SerializedName("img")
    @Expose
    private String img;

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getSerial() {
        return serial;
    }

    public void setSerial(String serial) {
        this.serial = serial;
    }

    public String getDivtype() {
        return divtype;
    }

    public void setDivtype(String divtype) {
        this.divtype = divtype;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }
}
