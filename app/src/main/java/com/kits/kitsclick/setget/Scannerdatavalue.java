package com.kits.kitsclick.setget;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class Scannerdatavalue {
    @SerializedName("device")
    @Expose
    private ArrayList<Listdevice> device;

    public ArrayList<Listdevice> getDevice() {
        return device;
    }

    public void setDevice(ArrayList<Listdevice> device) {
        this.device = device;
    }
}
