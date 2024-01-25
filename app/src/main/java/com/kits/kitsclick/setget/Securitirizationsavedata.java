package com.kits.kitsclick.setget;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Securitirizationsavedata {
    @SerializedName("co_ordinator")
    @Expose
    private String coordinator;

    @SerializedName("officesapce")
    @Expose
    private String officesapce;

    @SerializedName("bussiness")
    @Expose
    private String bussiness;

    @SerializedName("iata")
    @Expose
    private String iata;

    @SerializedName("business_year")
    @Expose
    private String business_year;

    @SerializedName("turnover")
    @Expose
    private String turnover;


    @SerializedName("securitization")
    @Expose
    private String securitization;


    public String getCoordinator() {
        return coordinator;
    }

    public void setCoordinator(String coordinator) {
        this.coordinator = coordinator;
    }

    public String getOfficesapce() {
        return officesapce;
    }

    public void setOfficesapce(String officesapce) {
        this.officesapce = officesapce;
    }

    public String getBussiness() {
        return bussiness;
    }

    public void setBussiness(String bussiness) {
        this.bussiness = bussiness;
    }

    public String getIata() {
        return iata;
    }

    public void setIata(String iata) {
        this.iata = iata;
    }

    public String getBusiness_year() {
        return business_year;
    }

    public void setBusiness_year(String business_year) {
        this.business_year = business_year;
    }

    public String getTurnover() {
        return turnover;
    }

    public void setTurnover(String turnover) {
        this.turnover = turnover;
    }

    public String getSecuritization() {
        return securitization;
    }

    public void setSecuritization(String securitization) {
        this.securitization = securitization;
    }
}
