package com.iait.conversor.tasas.calculadoras.tasa;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import com.iait.conversor.tasas.TipoTasaEnum;
import com.iait.conversor.tasas.TipoTasaNominal;

@Component
public class CalculadoraTasaFactory {

    @Autowired
    @Qualifier("tasaTna")
    private CalculadoraTasa calculadoraTasaTna;

    @Autowired 
    @Qualifier("tasaTnv")
    private CalculadoraTasa calculadoraTasaTnv;

    @Autowired 
    @Qualifier("tasaTe")
    private CalculadoraTasa calculadoraTasaTe;

    @Value("${app.tasa.nominal.tipo:vencida}")
    private TipoTasaNominal tipoTasaNominal;

    public CalculadoraTasa create(TipoTasaEnum tipo) {
        Assert.notNull(tipo, "El tipo de tasa no puede ser nulo");
        switch (tipo) {
            case EFECTIVA:
                return calculadoraTasaTe;
            case NOMINAL:
                switch (tipoTasaNominal) {
                    case ADELANTADA:
                        return calculadoraTasaTna;
                    case VENCIDA:
                        return calculadoraTasaTnv;
                    default:
                        throw new IllegalArgumentException(String.format(
                                "Se desconoce el tipo de tasa nominal: %s", tipo));
                }
            default:
                throw new IllegalArgumentException(String.format(
                        "Se desconoce el tipo de tasa: %s", tipo));
        }
    }
}
