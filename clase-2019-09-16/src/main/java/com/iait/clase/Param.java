package com.iait.clase;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "app.potencia")
public class Param {

    private double exponente;
    private double bias;

    public double getExponente() {
        return exponente;
    }

    public void setExponente(double exponente) {
        this.exponente = exponente;
    }

    public double getBias() {
        return bias;
    }

    public void setBias(double bias) {
        this.bias = bias;
    }
}
