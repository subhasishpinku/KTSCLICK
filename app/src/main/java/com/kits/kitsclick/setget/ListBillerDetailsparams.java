package com.kits.kitsclick.setget;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ListBillerDetailsparams {
    @SerializedName("param_level")
    @Expose
    private String paramlevel;

    @SerializedName("param_name")
    @Expose
    private String paramname;

    @SerializedName("min_length")
    @Expose
    private String minlength;

    @SerializedName("max_length")
    @Expose
    private String maxlength;

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
}
