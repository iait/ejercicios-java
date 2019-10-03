package com.iait.acceso.base.datos.ctx;

import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.iait.acceso.base.datos.ctx.entities.BeneficiarioEntity;
import com.iait.acceso.base.datos.ctx.entities.EstadoCivilEntity;

@Configuration
public class App {
    
    public static final ApplicationContext CTX;
    
    static {
        CTX = new ClassPathXmlApplicationContext("app-config.xml");
    }
    
    private static final String SQL_BENEFICIARIOS = 
            "select * from beneficiarios where estado_civil_id = ?";
    private static final String SQL_ESTADO_CIVIL =
            "select * from estado_civil;";
    
    public static void main(String[] args) {
        App app = CTX.getBean(App.class);
        app.run();
    }
    
    @Autowired
    private JdbcTemplate template;
    
    @Autowired
    private Scanner scanner;
    
    public void run() {
        
        List<EstadoCivilEntity> listaEstadoCivil = template.query(SQL_ESTADO_CIVIL, 
                (rs, rowNum) -> {
                    Long id = rs.getLong("id");
                    String nombre = rs.getString("nombre");
                    return new EstadoCivilEntity(id, nombre);
                });
        
        System.out.println("Ingrese el estado civil a buscar: ");
        String listaEstadoCivilStr = listaEstadoCivil.stream()
                .map(e -> String.format("%s - %s", e.getId(), e.getNombre()))
                .collect(Collectors.joining(", "));
        System.out.println("(" + listaEstadoCivilStr + ")");
        Integer estadoCivil = scanner.nextInt();
        
        List<BeneficiarioEntity> beneficiarios = template.query(SQL_BENEFICIARIOS, 
                (rs, rowNum) -> {
                    Long id = rs.getLong("id");
                    String nombre = rs.getString("nombre");
                    String apellido = rs.getString("apellido");
                    return new BeneficiarioEntity(id, nombre, apellido);
                }, estadoCivil);
        
        beneficiarios.forEach(b -> System.out.println(b));
    }
}
