package com.kits.kitsclick.setget;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class Bpspaysetgetvaluedata {
    @SerializedName("payment_modes")
    @Expose
    private String paymentmodes;

    @SerializedName("reqid")
    @Expose
    private String reqid;

    @SerializedName("biller_Id")
    @Expose
    private String billerId;

    @SerializedName("biller_name")
    @Expose
    private String billername;

    @SerializedName("payee_number")
    @Expose
    private String payeenumber;

    @SerializedName("customer_number")
    @Expose
    private String customernumber;

    @SerializedName("customer_email")
    @Expose
    private String customeremail;

    @SerializedName("customer_name")
    @Expose
    private String customername;

    @SerializedName("amount")
    @Expose
    private String amount;

    @SerializedName("pay_type")
    @Expose
    private String pay_type;

    @SerializedName("conveniencefees")
    @Expose
    private String conveniencefees;

    @SerializedName("bill_date")
    @Expose
    private String billdate;

    @SerializedName("bill_period")
    @Expose
    private String billperiod;

    @SerializedName("bill_number")
    @Expose
    private String billnumber;

    @SerializedName("bill_due_date")
    @Expose
    private String billduedate;

    public String getConveniencefees() {
        return conveniencefees;
    }

    public void setConveniencefees(String conveniencefees) {
        this.conveniencefees = conveniencefees;
    }

    public String getBilldate() {
        return billdate;
    }

    public void setBilldate(String billdate) {
        this.billdate = billdate;
    }

    public String getBillperiod() {
        return billperiod;
    }

    public void setBillperiod(String billperiod) {
        this.billperiod = billperiod;
    }

    public String getBillnumber() {
        return billnumber;
    }

    public void setBillnumber(String billnumber) {
        this.billnumber = billnumber;
    }

    public String getBillduedate() {
        return billduedate;
    }

    public void setBillduedate(String billduedate) {
        this.billduedate = billduedate;
    }

    @SerializedName("pay_params")
    @Expose

    private ArrayList<Payparamslist> payparams;

    public String getPaymentmodes() {
        return paymentmodes;
    }

    public void setPaymentmodes(String paymentmodes) {
        this.paymentmodes = paymentmodes;
    }

    public String getReqid() {
        return reqid;
    }

    public void setReqid(String reqid) {
        this.reqid = reqid;
    }

    public String getBillerId() {
        return billerId;
    }

    public void setBillerId(String billerId) {
        this.billerId = billerId;
    }

    public String getBillername() {
        return billername;
    }

    public void setBillername(String billername) {
        this.billername = billername;
    }

    public String getPayeenumber() {
        return payeenumber;
    }

    public void setPayeenumber(String payeenumber) {
        this.payeenumber = payeenumber;
    }

    public String getCustomernumber() {
        return customernumber;
    }

    public void setCustomernumber(String customernumber) {
        this.customernumber = customernumber;
    }

    public String getCustomeremail() {
        return customeremail;
    }

    public void setCustomeremail(String customeremail) {
        this.customeremail = customeremail;
    }

    public String getCustomername() {
        return customername;
    }

    public void setCustomername(String customername) {
        this.customername = customername;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getPay_type() {
        return pay_type;
    }

    public void setPay_type(String pay_type) {
        this.pay_type = pay_type;
    }

    public ArrayList<Payparamslist> getPayparams() {
        return payparams;
    }

    public void setPayparams(ArrayList<Payparamslist> payparams) {
        this.payparams = payparams;
    }
}
