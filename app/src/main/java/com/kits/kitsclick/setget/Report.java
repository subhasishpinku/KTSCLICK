package com.kits.kitsclick.setget;

public class Report {
    private String name;
    private String statement;
    private String image;
    public Report(String name,String statement,String image){
        this.name=name;
        this.statement=statement;
        this.image=image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStatement() {
        return statement;
    }

    public void setStatement(String statement) {
        this.statement = statement;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
