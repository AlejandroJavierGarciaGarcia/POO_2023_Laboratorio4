package com.biblioteca.bean;

import java.util.ArrayList;

public abstract class User {
    private String name;
    private String password;
    private String user;
    private boolean isPremium;

    ArrayList<ResourcesLibrary> loan = new ArrayList<ResourcesLibrary>();

    public User(String name, String user, String password, boolean isPremium) {
        this.name = name;
        this.password = password;
        this.user = user;
        this.isPremium = isPremium;
    }

    public void login() {
    }

    public void register() {
    }

    public void changePassword() {
    }

    public String getName() {
        return name;
    }

    public String getPassword() {
        return password;
    }

    public String getUser() {
        return user;
    }

    public boolean isPremium() {
        return isPremium;
    }

}
