package com.iait.conversor.tasas;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.Scanner;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.util.Assert;

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

        try (Scanner scanner = new Scanner(System.in)) {
            while (true) {
                cicloPrincipal(scanner);
            }
        }
    }

    private void cicloPrincipal(Scanner scanner) {

        final BigDecimal tasaOriginal;
        final TipoTasaEnum tipoOrigen;
        final Long moduloOrigen;
        final Long diasAmortizacion;
        final TipoTasaEnum tipoDestino;
        final Long moduloDestino;

        System.out.println("Ingese el valor porcentual de la tasa a convertir: ");
        tasaOriginal = scanner.nextBigDecimal();

        System.out.println("Ingrese el tipo de la tasa: ");
        tipoOrigen = leerTipoTasa(scanner);

        System.out.println("Ingrese el tiempo de expresión de la tasa: ");
        moduloOrigen = scanner.nextLong();
        Assert.isTrue(moduloOrigen > 0, 
                "El tiempo de expresión de la tasa debe ser un número positivo");

        System.out.println("Ingrese los días de amortización: ");
        diasAmortizacion = scanner.nextLong();
        Assert.isTrue(diasAmortizacion > 0, 
                "El valor de días de amortización debe ser un número positivo");

        System.out.println("Ingrese el tipo de tasa al que desea convertir: ");
        tipoDestino = leerTipoTasa(scanner);

        System.out.println("Ingrese el tiempo de expresión de la tasa convertida: ");
        moduloDestino = scanner.nextLong();
        Assert.isTrue(moduloDestino > 0, 
                "El tiempo de expresión de la tasa debe ser un número positivo");

        Tasa tasaOrigen = new Tasa(tipoOrigen, tasaOriginal, moduloOrigen);
        Tasa tasaDestino = conversor.convertir(
                tasaOrigen, diasAmortizacion, tipoDestino, moduloDestino);
        DecimalFormat df = new DecimalFormat();
        df.setMaximumFractionDigits(3);
        df.setMinimumFractionDigits(3);
        System.out.println(String.format("Tasa calculada: %s%%", 
                df.format(tasaDestino.getValor())));
    }

    private TipoTasaEnum leerTipoTasa(Scanner scanner) {
        System.out.println("1) Efectiva");
        System.out.println("2) Nominal Adelantada");
        System.out.println("3) Nominal Vencida");
        TipoTasaEnum tipoTasa = TipoTasaEnum.of(scanner.nextInt());
        return tipoTasa;
    }
}
