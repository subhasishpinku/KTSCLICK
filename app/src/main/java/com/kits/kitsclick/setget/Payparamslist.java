package com.kits.kitsclick.setget;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Payparamslist {
    @SerializedName("param_level")
    @Expose
    private String paramlevel;

    @SerializedName("param_name")
    @Expose
    private String paramname;

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
}
