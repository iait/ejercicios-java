package com.iait.conversor.tasas;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

import java.math.BigDecimal;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

import com.iait.conversor.tasas.calculadoras.razon.CalculadoraRazon;
import com.iait.conversor.tasas.calculadoras.razon.CalculadoraRazonFactory;
import com.iait.conversor.tasas.calculadoras.tasa.CalculadoraTasa;
import com.iait.conversor.tasas.calculadoras.tasa.CalculadoraTasaFactory;

@RunWith(SpringRunner.class)
public class ConversorTest {

    @TestConfiguration
    static class Config {

        @Bean
        public Conversor getConversor() {
            return new Conversor();
        }
    }

    @Autowired
    private Conversor conversor;

    @MockBean
    private CalculadoraRazonFactory calculadoraRazonFactory;

    @MockBean
    private CalculadoraTasaFactory calculadoraTasaFactory;

    @Before
    public void setup() {
        Mockito.when(calculadoraRazonFactory.create(Mockito.any(TipoTasaEnum.class)))
                .thenReturn(new CalculadoraRazon() {
                    @Override
                    public BigDecimal calcular(
                            BigDecimal tasa, Long diasAmortizacion, Long modulo) {
                        return BigDecimal.ONE;
                    }
                });

        Mockito.when(calculadoraTasaFactory.create(Mockito.any(TipoTasaEnum.class)))
                .thenReturn(new CalculadoraTasa() {
                    @Override
                    public BigDecimal calcular(
                            BigDecimal razon, Long diasAmortizacion, Long modulo) {
                        return razon;
                    }
                });
    }

    @Test
    public void testConversor() throws Exception {

        Tasa tasaOrigen = new Tasa(TipoTasaEnum.EFECTIVA, BigDecimal.ZERO, 1L);
        Tasa tasa = conversor.convertir(tasaOrigen, 1L, TipoTasaEnum.NOMINAL_VENCIDA, 1L);
        assertEquals(Long.valueOf(1L), tasa.getModulo());
        assertEquals(TipoTasaEnum.NOMINAL_VENCIDA, tasa.getTipo());
        assertThat(tasa.getValor().compareTo(BigDecimal.ONE), is(0));
    }

}
