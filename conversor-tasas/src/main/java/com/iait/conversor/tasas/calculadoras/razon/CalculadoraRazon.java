package com.iait.conversor.tasas.calculadoras.razon;

import java.math.BigDecimal;

public interface CalculadoraRazon {

    BigDecimal calcular(BigDecimal tasa, Long diasAmortizacion, Long modulo);
}
