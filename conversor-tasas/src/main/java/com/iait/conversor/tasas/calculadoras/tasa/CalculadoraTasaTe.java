package com.iait.conversor.tasas.calculadoras.tasa;

import java.math.BigDecimal;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CalculadoraTasaTe implements CalculadoraTasa {

    private static Logger LOG = LoggerFactory.getLogger(CalculadoraTasaTe.class);

    @Override
    public BigDecimal calcular(BigDecimal razon, Long diasAmortizacion, Long modulo) {
        LOG.debug("Calculando tasa efectiva...");
        return new BigDecimal("1");
    }
}
