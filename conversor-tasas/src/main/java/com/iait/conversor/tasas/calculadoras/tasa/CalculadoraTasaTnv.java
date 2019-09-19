package com.iait.conversor.tasas.calculadoras.tasa;

import java.math.BigDecimal;
import java.math.RoundingMode;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CalculadoraTasaTnv implements CalculadoraTasa {

    private static Logger LOG = LoggerFactory.getLogger(CalculadoraTasaTnv.class);

    @Override
    public BigDecimal calcular(BigDecimal razon, Long diasAmortizacion, Long modulo) {
        LOG.debug("Calculando tasa nominal vencida...");

        BigDecimal tasa = razon.multiply(BigDecimal.valueOf(modulo))
                .multiply(new BigDecimal("100")).divide(
                        BigDecimal.valueOf(diasAmortizacion), 8, RoundingMode.HALF_EVEN);
        return tasa;
    }
}
