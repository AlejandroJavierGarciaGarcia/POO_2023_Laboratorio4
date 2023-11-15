package com.biblioteca.bean;

import java.util.ArrayList;

import com.biblioteca.bean.objects.ResourcesLibrary;

public class BaseUser extends User {

    public BaseUser(String name, String password, String ID, ArrayList<ResourcesLibrary> loan) {
        super(name, password, ID, loan, false);
    }

    @Override
    public void login() {
        System.out.println("Inicio de sesión exitoso para usuario base: " + getName());

    }

    @Override
    public void register() {
        System.out.println("Registro exitoso para usuario base: " + getName());
    }

    @Override
    public void changePassword() {
        System.out.println("Contraseña cambiada para usuario base: " + getName());
    }
}
