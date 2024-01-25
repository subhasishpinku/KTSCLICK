package com.kits.kitsclick.setget;

public class Transactiondata {
    private String transaction;
    private String trandate;
    private String traaft;
    private String type;
   public Transactiondata(String transaction,String trandate,String traaft,String type){
       this.transaction=transaction;
       this.trandate=trandate;
       this.traaft=traaft;
       this.type= type;
   }

    public String getTransaction() {
        return transaction;
    }

    public void setTransaction(String transaction) {
        this.transaction = transaction;
    }

    public String getTrandate() {
        return trandate;
    }

    public void setTrandate(String trandate) {
        this.trandate = trandate;
    }

    public String getTraaft() {
        return traaft;
    }

    public void setTraaft(String traaft) {
        this.traaft = traaft;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
