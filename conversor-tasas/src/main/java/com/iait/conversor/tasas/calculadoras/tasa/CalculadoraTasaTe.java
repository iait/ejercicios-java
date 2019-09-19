package com.iait.conversor.tasas.calculadoras.tasa;

import java.math.BigDecimal;
import java.math.RoundingMode;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CalculadoraTasaTe implements CalculadoraTasa {

    private static Logger LOG = LoggerFactory.getLogger(CalculadoraTasaTe.class);

    @Override
    public BigDecimal calcular(BigDecimal razon, Long diasAmortizacion, Long modulo) {
        LOG.debug("Calculando tasa efectiva...");

        BigDecimal base = razon.add(BigDecimal.ONE);

        BigDecimal exponente = BigDecimal.valueOf(modulo)
                .divide(BigDecimal.valueOf(diasAmortizacion), 8, RoundingMode.HALF_EVEN);

        double potencia = Math.pow(base.doubleValue(), exponente.doubleValue());
        BigDecimal tasa = BigDecimal.valueOf(potencia)
                .subtract(BigDecimal.ONE).multiply(new BigDecimal("100"));
        return tasa;
    }
}
