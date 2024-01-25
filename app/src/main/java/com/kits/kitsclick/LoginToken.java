package com.kits.kitsclick;

public class LoginToken {
    private String tokenkey;
    public LoginToken(String tokenkey){
        this.tokenkey= tokenkey;
    }

    public String getTokenkey() {
        return tokenkey;
    }

    public void setTokenkey(String tokenkey) {
        this.tokenkey = tokenkey;
    }
}
