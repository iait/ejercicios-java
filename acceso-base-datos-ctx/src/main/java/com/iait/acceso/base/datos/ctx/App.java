package com.iait.acceso.base.datos.ctx;

import java.util.List;
import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.iait.acceso.base.datos.ctx.entities.BeneficiarioEntity;

@Configuration
public class App {
    
    public static final ApplicationContext CTX;
    
    static {
        CTX = new ClassPathXmlApplicationContext("app-config.xml");
    }
    
    private static final String SQL = 
            "select * from beneficiarios where estado_civil_id = ?";
    
    public static void main(String[] args) {
        App app = CTX.getBean(App.class);
        app.run();
    }
    
    @Autowired
    private JdbcTemplate template;
    
    @Autowired
    private Scanner scanner;
    
    public void run() {
        
        System.out.println("Ingrese el código del estado civil a buscar: ");
        Integer estadoCivil = scanner.nextInt();
        
        List<BeneficiarioEntity> beneficiarios = template.query(SQL, 
                (rs, rowNum) -> {
                    Long id = rs.getLong("id");
                    String nombre = rs.getString("nombre");
                    String apellido = rs.getString("apellido");
                    return new BeneficiarioEntity(id, nombre, apellido);
                }, estadoCivil);
        
        beneficiarios.forEach(b -> System.out.println(b));
    }
}
