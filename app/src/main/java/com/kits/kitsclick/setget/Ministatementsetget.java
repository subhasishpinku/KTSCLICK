package com.kits.kitsclick.setget;

public class Ministatementsetget {
    private String stname;
    private String img;
    private String devicetype;
    private String model;
    private String serial;
    public Ministatementsetget(String stname,String img,String devicetype,String model,String serial){
        this.stname=stname;
        this.img=img;
        this.devicetype=devicetype;
        this.model=model;
        this.serial=serial;
    }

    public String getStname() {
        return stname;
    }

    public void setStname(String stname) {
        this.stname = stname;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getDevicetype() {
        return devicetype;
    }

    public void setDevicetype(String devicetype) {
        this.devicetype = devicetype;
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
}
