package com.iait.conversor.tasas.calculadoras.tasa;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import com.iait.conversor.tasas.TipoTasaEnum;

@Component
public class CalculadoraTasaFactory {

    @Autowired
    @Qualifier("tasaTna")
    private CalculadoraTasa calculadoraTasaTna;

    @Autowired 
    @Qualifier("tasaTnv")
    private CalculadoraTasa calculadoraTasaTnv;

    @Autowired 
    @Qualifier("tasaTe")
    private CalculadoraTasa calculadoraTasaTe;

    public CalculadoraTasa create(TipoTasaEnum tipo) {
        Assert.notNull(tipo, "El tipo de tasa no puede ser nulo");
        switch (tipo) {
            case EFECTIVA:
                return calculadoraTasaTe;
            case NOMINAL_ADELANTADA:
                return calculadoraTasaTna;
            case NOMINAL_VENCIDA:
                return calculadoraTasaTnv;
            default:
                throw new IllegalArgumentException(String.format(
                        "Se desconoce el tipo de tasa: %s", tipo));
        }
    }
}
