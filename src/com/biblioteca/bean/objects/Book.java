package com.biblioteca.bean.objects;

public class Book extends ResourcesLibrary{
    
    private String author;

    public Book(String name, String ISBN, String type, double price, String author) {
        super(name, ISBN, type, price);
        this.author = author;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

}
