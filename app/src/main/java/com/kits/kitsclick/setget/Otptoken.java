package com.kits.kitsclick.setget;

public class Otptoken {
    private String phonenumberkey;
    public Otptoken(String phonenumberkey){
        this.phonenumberkey= phonenumberkey;
    }

    public String getPhonenumberkey() {
        return phonenumberkey;
    }

    public void setPhonenumberkey(String phonenumberkey) {
        this.phonenumberkey = phonenumberkey;
    }
}
