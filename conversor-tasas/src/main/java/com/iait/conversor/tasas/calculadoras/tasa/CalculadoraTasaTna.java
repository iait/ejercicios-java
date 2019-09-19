package com.iait.conversor.tasas.calculadoras.tasa;

import java.math.BigDecimal;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CalculadoraTasaTna implements CalculadoraTasa {

    private static Logger LOG = LoggerFactory.getLogger(CalculadoraTasaTna.class);

    @Override
    public BigDecimal calcular(BigDecimal tasa, Long diasAmortizacion, Long modulo) {
        LOG.debug("Calculando razón desde tasa nominal adelantada...");
        return new BigDecimal("1");
    }
}
