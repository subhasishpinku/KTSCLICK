package com.kits.kitsclick.setget;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
public class RegistrationValue {
    @SerializedName("first_name")
    @Expose
    private String firstname;

    @SerializedName("last_name")
    @Expose
    private String lastname;

    @SerializedName("login_name")
    @Expose
    private String loginname;

    @SerializedName("agency_name")
    @Expose
    private String agencyname;

    @SerializedName("address")
    @Expose
    private String address;

    @SerializedName("mobile")
    @Expose
    private String mobile;

    @SerializedName("pannumber")
    @Expose
    private String pannumber;

    @SerializedName("email")
    @Expose
    private String email;

    @SerializedName("password")
    @Expose
    private String password;

    @SerializedName("password_confirmation")
    @Expose
    private String passwordconfirmation;

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getLoginname() {
        return loginname;
    }

    public void setLoginname(String loginname) {
        this.loginname = loginname;
    }

    public String getAgencyname() {
        return agencyname;
    }

    public void setAgencyname(String agencyname) {
        this.agencyname = agencyname;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getPannumber() {
        return pannumber;
    }

    public void setPannumber(String pannumber) {
        this.pannumber = pannumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPasswordconfirmation() {
        return passwordconfirmation;
    }

    public void setPasswordconfirmation(String passwordconfirmation) {
        this.passwordconfirmation = passwordconfirmation;
    }
}
