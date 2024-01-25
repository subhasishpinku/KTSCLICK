package com.kits.kitsclick.setget;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Packagelist {
    @SerializedName("userID")
    @Expose
    private String userID;

    @SerializedName("name")
    @Expose
    private String name;

    @SerializedName("email")
    @Expose
    private String email;

    @SerializedName("agency_ID")
    @Expose
    private String agencyID;

    @SerializedName("emailID")
    @Expose
    private String emailID;

    @SerializedName("mobile")
    @Expose
    private String mobile;

    @SerializedName("complete_photo_kyc")
    @Expose
    private String completephotokyc;

    @SerializedName("complete_business_details")
    @Expose
    private String completebusinessdetails;

    @SerializedName("complete_personal_details")
    @Expose
    private String completepersonaldetails;


    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAgencyID() {
        return agencyID;
    }

    public void setAgencyID(String agencyID) {
        this.agencyID = agencyID;
    }

    public String getEmailID() {
        return emailID;
    }

    public void setEmailID(String emailID) {
        this.emailID = emailID;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getCompletephotokyc() {
        return completephotokyc;
    }

    public void setCompletephotokyc(String completephotokyc) {
        this.completephotokyc = completephotokyc;
    }

    public String getCompletebusinessdetails() {
        return completebusinessdetails;
    }

    public void setCompletebusinessdetails(String completebusinessdetails) {
        this.completebusinessdetails = completebusinessdetails;
    }

    public String getCompletepersonaldetails() {
        return completepersonaldetails;
    }

    public void setCompletepersonaldetails(String completepersonaldetails) {
        this.completepersonaldetails = completepersonaldetails;
    }
}
