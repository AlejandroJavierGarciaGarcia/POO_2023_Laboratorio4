package com.biblioteca.bean;

import java.util.ArrayList;

import com.biblioteca.bean.objects.ResourcesLibrary;

public class PremiumUser extends User {


    public PremiumUser(String name, String password, String user, ArrayList<ResourcesLibrary> loan, boolean isPremium) {
        super(name, password, user, loan, isPremium);

    }

    @Override
    public void login() {
        System.out.println("Inicio de sesión exitoso para usuario premium: " + getName());
    }

    @Override
    public void register() {
        System.out.println("Registro exitoso para usuario premium: " + getName());
    }

    @Override
    public void changePassword() {
        System.out.println("Contraseña cambiada para usuario premium: " + getName());
    }
}
