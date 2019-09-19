package com.iait.conversor.tasas;

import java.math.BigDecimal;

public class Tasa {

    private TipoTasaEnum tipo;
    private BigDecimal valor;
    private Long modulo;

    public Tasa(TipoTasaEnum tipo, BigDecimal valor, Long modulo) {
        this.tipo = tipo;
        this.valor = valor;
        this.modulo = modulo;
    }

    public TipoTasaEnum getTipo() {
        return tipo;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public Long getModulo() {
        return modulo;
    }
}
