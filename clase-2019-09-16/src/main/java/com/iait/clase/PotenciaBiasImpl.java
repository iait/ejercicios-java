package com.iait.clase;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PotenciaBiasImpl implements Potencia {

    @Autowired
    private Param param;

    @Override
    public double calcular(double base) {
        return Math.pow(base, param.getExponente()) + param.getBias();
    }

}
