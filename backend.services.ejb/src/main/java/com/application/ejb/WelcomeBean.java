package com.application.ejb;

import jakarta.ejb.LocalBean;
import jakarta.ejb.Stateless;

@Stateless
@LocalBean
public class WelcomeBean {

    public String getBienvenidaApp() {
        return "Hello, Servicio para el login";
    }

}
