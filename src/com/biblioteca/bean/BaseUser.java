package com.biblioteca.bean;

public class BaseUser extends User {

    public BaseUser(String name, String ID, String password) {
        super(name, ID, password, false);
    }
}
