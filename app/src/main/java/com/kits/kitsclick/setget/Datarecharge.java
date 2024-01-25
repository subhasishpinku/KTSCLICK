package com.kits.kitsclick.setget;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Datarecharge {
    @SerializedName("recharge_number")
    @Expose
    private String rechargenumber;

    @SerializedName("amount")
    @Expose
    private String amount;

    @SerializedName("operator_ref_no")
    @Expose
    private String operatorrefno;

    @SerializedName("soft_ref_no")
    @Expose
    private String softrefno;

    @SerializedName("TxnDate")
    @Expose
    private String TxnDate;


    public String getRechargenumber() {
        return rechargenumber;
    }

    public void setRechargenumber(String rechargenumber) {
        this.rechargenumber = rechargenumber;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getOperatorrefno() {
        return operatorrefno;
    }

    public void setOperatorrefno(String operatorrefno) {
        this.operatorrefno = operatorrefno;
    }

    public String getSoftrefno() {
        return softrefno;
    }

    public void setSoftrefno(String softrefno) {
        this.softrefno = softrefno;
    }

    public String getTxnDate() {
        return TxnDate;
    }

    public void setTxnDate(String txnDate) {
        TxnDate = txnDate;
    }
}
