package com.iait.clase;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource("classpath:saludo.properties")
public class SaludoCfg {

    @Bean
    public Saludo getSaludo() {
        return new SaludoImpl();
    }

}
