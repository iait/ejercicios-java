package com.iait.conversor.tasas.cfg;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.iait.conversor.tasas.calculadoras.razon.CalculadoraRazon;
import com.iait.conversor.tasas.calculadoras.razon.CalculadoraRazonTe;
import com.iait.conversor.tasas.calculadoras.razon.CalculadoraRazonTna;
import com.iait.conversor.tasas.calculadoras.razon.CalculadoraRazonTnv;
import com.iait.conversor.tasas.calculadoras.tasa.CalculadoraTasa;
import com.iait.conversor.tasas.calculadoras.tasa.CalculadoraTasaTe;
import com.iait.conversor.tasas.calculadoras.tasa.CalculadoraTasaTna;
import com.iait.conversor.tasas.calculadoras.tasa.CalculadoraTasaTnv;

@Configuration
public class CalculadoraCfg {

    @Bean(name = "razonTna")
    public CalculadoraRazon getCalculadoraRazonTna() {
        return new CalculadoraRazonTna();
    }

    @Bean(name = "razonTnv")
    public CalculadoraRazon getCalculadoraRazonTnv() {
        return new CalculadoraRazonTnv();
    }

    @Bean(name = "razonTe")
    public CalculadoraRazon getCalculadoraRazonTe() {
        return new CalculadoraRazonTe();
    }

    @Bean(name = "tasaTna")
    public CalculadoraTasa getCalculadoraTasaTna() {
        return new CalculadoraTasaTna();
    }

    @Bean(name = "tasaTnv")
    public CalculadoraTasa getCalculadoraTasaTnv() {
        return new CalculadoraTasaTnv();
    }

    @Bean(name = "tasaTe")
    public CalculadoraTasa getCalculadoraTasaTe() {
        return new CalculadoraTasaTe();
    }
}
