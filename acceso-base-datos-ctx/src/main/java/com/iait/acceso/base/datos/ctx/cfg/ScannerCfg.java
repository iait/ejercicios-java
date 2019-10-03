package com.iait.acceso.base.datos.ctx.cfg;

import java.util.Scanner;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ScannerCfg {
    
    @Bean
    public Scanner getScanner() {
        return new Scanner(System.in);
    }
}
