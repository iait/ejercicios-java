package com.iait.clase;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class PotenciaImpl implements Potencia {

    @Value("${app.exponente:3}")
    private long exponente;

    @Override
    public double calcular(double base) {
        return Math.pow(base, exponente);
    }

}
