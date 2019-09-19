package com.iait.conversor.tasas.calculadoras.tasa;

import java.math.BigDecimal;
import java.math.RoundingMode;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CalculadoraTasaTna implements CalculadoraTasa {

    private static Logger LOG = LoggerFactory.getLogger(CalculadoraTasaTna.class);

    @Override
    public BigDecimal calcular(BigDecimal razon, Long diasAmortizacion, Long modulo) {
        LOG.debug("Calculando tasa nominal adelantada...");

        BigDecimal factor1 = BigDecimal.ONE.subtract(BigDecimal.ONE.divide(
                BigDecimal.ONE.add(razon), 8, RoundingMode.HALF_EVEN));

        BigDecimal factor2 = BigDecimal.valueOf(modulo).multiply(new BigDecimal("100")).divide(
                BigDecimal.valueOf(diasAmortizacion), 8, RoundingMode.HALF_EVEN);

        BigDecimal tasa = factor1.multiply(factor2);
        return tasa;
    }
}
