package com.iait.conversor.tasas.calculadoras.tasa;

import java.math.BigDecimal;

public interface CalculadoraTasa {

    BigDecimal calcular(BigDecimal razon, Long diasAmortizacion, Long modulo);
}
