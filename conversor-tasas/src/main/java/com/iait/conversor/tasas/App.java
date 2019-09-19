package com.iait.conversor.tasas;

import java.math.BigDecimal;
import java.util.Scanner;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class App implements CommandLineRunner {

    private static Logger LOG = LoggerFactory.getLogger(App.class);

    public static void main(String[] args) {
        SpringApplication.run(App.class, args);
    }

    @Autowired
    private Conversor conversor;

    @Override
    public void run(String... args) throws Exception {
        LOG.debug("Inicializa la aplicación de conversor de tasas");

        final BigDecimal tasaOriginal;
        final TipoTasaEnum tipoOrigen;
        final Long moduloOrigen;
        final Long diasAmortizacion;
        final TipoTasaEnum tipoDestino;
        final Long moduloDestino;
        try (Scanner scanner = new Scanner(System.in)) {
            //TODO validaciones
            System.out.println("Ingese el valor porcentual de la tasa a convertir: ");
            tasaOriginal = scanner.nextBigDecimal();

            System.out.println("Ingrese el tipo de la tasa: ");
            tipoOrigen = leerTipoTasa(scanner);

            System.out.println("Ingrese el tiempo de expresión de la tasa: ");
            moduloOrigen = scanner.nextLong();

            System.out.println("Ingrese los días de amortización: ");
            diasAmortizacion = scanner.nextLong();

            System.out.println("Ingrese el tipo de tasa al que desea convertir: ");
            tipoDestino = leerTipoTasa(scanner);

            System.out.println("Ingrese el tiempo de expresión de la tasa convertida: ");
            moduloDestino = scanner.nextLong();
        }

        Tasa tasaOrigen = new Tasa(tipoOrigen, tasaOriginal, moduloOrigen);
        Tasa tasaDestino = conversor.convertir(
                tasaOrigen, diasAmortizacion, tipoDestino, moduloDestino);
        System.out.println(String.format("Tasa calculada: %s", tasaDestino.getValor()));
    }

    private TipoTasaEnum leerTipoTasa(Scanner scanner) {
        System.out.println("1) Efectiva");
        System.out.println("2) Nominal Adelantada");
        System.out.println("3) Nominal Vencida");
        TipoTasaEnum tipoTasa = TipoTasaEnum.of(scanner.nextInt());
        return tipoTasa;
    }
}
