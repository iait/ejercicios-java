package com.iait.conversor.tasas.calculadoras.razon;

import java.math.BigDecimal;
import java.math.RoundingMode;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CalculadoraRazonTna implements CalculadoraRazon {

    private static Logger LOG = LoggerFactory.getLogger(CalculadoraRazonTna.class);

    @Override
    public BigDecimal calcular(BigDecimal tasa, Long diasAmortizacion, Long modulo) {
        LOG.debug("Calculando razón desde tasa nominal adelantada...");

        BigDecimal factor = tasa.multiply(BigDecimal.valueOf(diasAmortizacion))
                .divide(new BigDecimal("100"))
                .divide(BigDecimal.valueOf(modulo), 8, RoundingMode.HALF_EVEN);

        BigDecimal razon = BigDecimal.ONE.divide(
                BigDecimal.ONE.subtract(factor), 8, RoundingMode.HALF_EVEN)
                .subtract(BigDecimal.ONE);
        return razon;
    }
}
