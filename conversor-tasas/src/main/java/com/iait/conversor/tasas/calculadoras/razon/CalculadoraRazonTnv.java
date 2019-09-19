package com.iait.conversor.tasas.calculadoras.razon;

import java.math.BigDecimal;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CalculadoraRazonTnv implements CalculadoraRazon {

    private static Logger LOG = LoggerFactory.getLogger(CalculadoraRazonTnv.class);

    @Override
    public BigDecimal calcular(BigDecimal tasa, Long diasAmortizacion, Long modulo) {
        LOG.debug("Calculando razón desde tasa nominal vencida...");
        return new BigDecimal("1");
    }
}
