package com.iait.conversor.tasas.calculadoras.razon;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import com.iait.conversor.tasas.TipoTasaEnum;

@Component
public class CalculadoraRazonFactory {

    @Autowired @Qualifier("razonTna")
    private CalculadoraRazon calculadoraRazonTna;

    @Autowired @Qualifier("razonTnv")
    private CalculadoraRazon calculadoraRazonTnv;

    @Autowired @Qualifier("razonTe")
    private CalculadoraRazon calculadoraRazonTe;

    public CalculadoraRazon create(TipoTasaEnum tipo) {
        Assert.notNull(tipo, "El tipo de tasa no puede ser nulo");
        switch (tipo) {
            case EFECTIVA:
                return calculadoraRazonTe;
            case NOMINAL_ADELANTADA:
                return calculadoraRazonTna;
            case NOMINAL_VENCIDA:
                return calculadoraRazonTnv;
            default:
                throw new IllegalArgumentException(String.format(
                        "Se desconoce el tipo de tasa: %s", tipo));
        }
    }
}
