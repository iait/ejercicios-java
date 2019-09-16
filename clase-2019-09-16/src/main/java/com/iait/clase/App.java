package com.iait.clase;

import java.util.Scanner;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class App implements CommandLineRunner {

    private static final Logger LOG = LoggerFactory.getLogger(App.class);

    public static void main(String[] args) {
        SpringApplication.run(App.class, args);
    }

    @Autowired
    private ApplicationContext ctx;

    //@Autowired
    //private Saludo saludo;

    @Autowired
    @Qualifier("potenciaBiasImpl")
    private Potencia potencia;

    @Override
    public void run(String... args) throws Exception {
        System.out.println("Ingrese su nombre: ");

        Saludo saludo = ctx.getBean(Saludo.class);

        try (Scanner scanner = new Scanner(System.in)) {
            String nombre = scanner.next();
            String mensaje = saludo.saludar(nombre);
            System.out.println(mensaje);
        }

        double result = potencia.calcular(2);
        LOG.debug("Resultado: {}", result);
    }
}
