package com.iait.clase;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.util.Assert;

//@Component
public class SaludoImpl implements Saludo {

    @Value("${app.saludo.msj:Hola %s}")
    private String msj;

    @Override
    public String saludar(String nombre) {
        Assert.notNull(nombre, "No se conoce el nombre");
        return String.format(msj, nombre);
    }
}
