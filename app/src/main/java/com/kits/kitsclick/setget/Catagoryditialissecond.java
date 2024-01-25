package com.kits.kitsclick.setget;

public class Catagoryditialissecond {
    private String paramlevel;
    private String paramname;
    private String value;
    public Catagoryditialissecond (String paramlevel,String paramname,String value){
        this.paramlevel=paramlevel;
        this.paramname=paramname;
        this.value=value;
    }

    public String getParamlevel() {
        return paramlevel;
    }

    public void setParamlevel(String paramlevel) {
        this.paramlevel = paramlevel;
    }

    public String getParamname() {
        return paramname;
    }

    public void setParamname(String paramname) {
        this.paramname = paramname;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
