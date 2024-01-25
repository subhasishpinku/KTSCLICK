package com.kits.kitsclick.setget;

public class Catagoryditialis {
    private String paramlevel;
    private String paramname;
    private String minlength;
    private String maxlength;
//    private String sparamlevel;
//    private String sparamname;
    private String value;
    public Catagoryditialis (String paramlevel,String paramname,String minlength,String maxlength){
        this.paramlevel=paramlevel;
        this.paramname=paramname;
        this.minlength=minlength;
        this.maxlength=maxlength;
//        this.sparamlevel=sparamlevel;
//        this.sparamname=sparamname;
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

    public String getMinlength() {
        return minlength;
    }

    public void setMinlength(String minlength) {
        this.minlength = minlength;
    }

    public String getMaxlength() {
        return maxlength;
    }

    public void setMaxlength(String maxlength) {
        this.maxlength = maxlength;
    }

//    public String getSparamlevel() {
//        return sparamlevel;
//    }
//
//    public void setSparamlevel(String sparamlevel) {
//        this.sparamlevel = sparamlevel;
//    }
//
//    public String getSparamname() {
//        return sparamname;
//    }
//
//    public void setSparamname(String sparamname) {
//        this.sparamname = sparamname;
//    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
