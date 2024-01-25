package com.kits.kitsclick.setget;

public class Reportshowlist {

        private String page;
        private String totalpage;
        private String totalrecord;
        private String name;
        private String agentid;
        private String merchentid;
        private String fqtransation;
        private String service;
        private String bankrnn;
        private String balanceamount;
        private String rqtransactiontime;
        private String transamount;
        private String transcationstatus;
        public Reportshowlist(String page,String totalpage,
                          String totalrecord,String name,
                          String agentid,String merchentid,
                          String fqtransation,
                          String service,String bankrnn,String balanceamount,String rqtransactiontime,String transamount,String transcationstatus){
            this.page=page;
            this.totalpage=totalpage;
            this.totalrecord=totalrecord;
            this.name=name;
            this.agentid= agentid;
            this.merchentid=merchentid;
            this.fqtransation=fqtransation;
            this.service=service;
            this.bankrnn=bankrnn;
            this.balanceamount=balanceamount;
            this.rqtransactiontime=rqtransactiontime;
            this.transamount=transamount;
            this.transcationstatus=transcationstatus;
            this.transcationstatus=transcationstatus;
        }

        public String getPage() {
            return page;
        }

        public void setPage(String page) {
            this.page = page;
        }

        public String getTotalpage() {
            return totalpage;
        }

        public void setTotalpage(String totalpage) {
            this.totalpage = totalpage;
        }

        public String getTotalrecord() {
            return totalrecord;
        }

        public void setTotalrecord(String totalrecord) {
            this.totalrecord = totalrecord;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getAgentid() {
            return agentid;
        }

        public void setAgentid(String agentid) {
            this.agentid = agentid;
        }

        public String getMerchentid() {
            return merchentid;
        }

        public void setMerchentid(String merchentid) {
            this.merchentid = merchentid;
        }

        public String getFqtransation() {
            return fqtransation;
        }

        public void setFqtransation(String fqtransation) {
            this.fqtransation = fqtransation;
        }

        public String getService() {
            return service;
        }

        public void setService(String service) {
            this.service = service;
        }

        public String getBankrnn() {
            return bankrnn;
        }

        public void setBankrnn(String bankrnn) {
            this.bankrnn = bankrnn;
        }

        public String getBalanceamount() {
            return balanceamount;
        }

        public void setBalanceamount(String balanceamount) {
            this.balanceamount = balanceamount;
        }

        public String getRqtransactiontime() {
            return rqtransactiontime;
        }

        public void setRqtransactiontime(String rqtransactiontime) {
            this.rqtransactiontime = rqtransactiontime;
        }

        public String getTransamount() {
            return transamount;
        }

        public void setTransamount(String transamount) {
            this.transamount = transamount;
        }

        public String getTranscationstatus() {
            return transcationstatus;
        }

        public void setTranscationstatus(String transcationstatus) {
            this.transcationstatus = transcationstatus;
        }
    }
