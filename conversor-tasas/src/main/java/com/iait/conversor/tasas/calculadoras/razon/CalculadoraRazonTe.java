package com.iait.conversor.tasas.calculadoras.razon;

import java.math.BigDecimal;
import java.math.RoundingMode;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CalculadoraRazonTe implements CalculadoraRazon {

    private static Logger LOG = LoggerFactory.getLogger(CalculadoraRazonTe.class);

    @Override
    public BigDecimal calcular(BigDecimal tasa, Long diasAmortizacion, Long modulo) {
        LOG.debug("Calculando razón desde tasa efectiva...");

        BigDecimal base = tasa.divide(new BigDecimal("100")).add(BigDecimal.ONE);

        BigDecimal exponente = BigDecimal.valueOf(diasAmortizacion)
                .divide(BigDecimal.valueOf(modulo), 8, RoundingMode.HALF_EVEN);

        double potencia = Math.pow(base.doubleValue(), exponente.doubleValue());
        BigDecimal razon = BigDecimal.valueOf(potencia).subtract(BigDecimal.ONE);
        return razon;
    }
}
