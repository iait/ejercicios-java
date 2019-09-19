package com.iait.conversor.tasas;

import java.math.BigDecimal;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.iait.conversor.tasas.calculadoras.razon.CalculadoraRazon;
import com.iait.conversor.tasas.calculadoras.razon.CalculadoraRazonFactory;
import com.iait.conversor.tasas.calculadoras.tasa.CalculadoraTasa;
import com.iait.conversor.tasas.calculadoras.tasa.CalculadoraTasaFactory;

@Component
public class Conversor {

    private static final Logger LOG = LoggerFactory.getLogger(Conversor.class);

    @Autowired
    private CalculadoraRazonFactory calculadoraRazonFactory;

    @Autowired
    private CalculadoraTasaFactory calculadoraTasaFactory;

    public Tasa convertir(
            Tasa tasaOrigen, Long diasAmortizacion, TipoTasaEnum tipoDestino, Long moduloDestino) {

        CalculadoraRazon calculadoraRazon = calculadoraRazonFactory.create(tasaOrigen.getTipo());
        BigDecimal razon = calculadoraRazon.calcular(
                tasaOrigen.getValor(), diasAmortizacion, tasaOrigen.getModulo());
        LOG.debug("Razon calculada: {}", razon);

        CalculadoraTasa calculadoraTasa = calculadoraTasaFactory.create(tipoDestino);
        BigDecimal tasa = calculadoraTasa.calcular(razon, diasAmortizacion, moduloDestino);
        LOG.debug("Tasa calculada: {}", tasa);

        Tasa tasaDestino = new Tasa(tipoDestino, tasa, moduloDestino);
        return tasaDestino;
    }

}
