package com.biblioteca.bean.objects;

public class Magazine extends ResourcesLibrary {

    private String issue;

    public Magazine(String name, String ISBN, String type, double price, String issue) {
        super(name, ISBN, type, price);
        this.issue = issue;
    }

    public String getIssue() {
        return issue;
    }

    public void setIssue(String issue) {
        this.issue = issue;
    }
}
