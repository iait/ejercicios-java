package com.iait.ejercicio;

import java.sql.SQLException;
import java.util.Optional;
import java.util.Scanner;

import org.h2.tools.Server;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.iait.ejercicio.entities.AbstractEntity;
import com.iait.ejercicio.entities.LocalidadEntity;
import com.iait.ejercicio.entities.ProvinciaEntity;
import com.iait.ejercicio.repositories.LocalidadRepository;
import com.iait.ejercicio.repositories.ProvinciaRepository;

@Configuration
public class App {
    
    public static final ApplicationContext CTX;
    
    static {
        CTX = new ClassPathXmlApplicationContext("app-config.xml");
    }
    
    public static void main(String[] args) throws SQLException {
        App app = CTX.getBean(App.class);
        app.run();
    }
    
    @Autowired
    private ProvinciaRepository provinciaRepository;
    
    @Autowired
    private LocalidadRepository localidadRepository;
    
    @Autowired
    private Scanner scanner;
    
    public void run() throws SQLException {
        
        Server.main();
        
        cargaDatos();
        
        muestraDatos();
        
        buscar();
    }
    
    private void cargaDatos() {
        
        ProvinciaEntity provincia = new ProvinciaEntity();
        provincia.setId(4L);
        provincia.setNombre("Rio Negro");
        provinciaRepository.save(provincia);
        
        LocalidadEntity localidad = new LocalidadEntity();
        localidad.setId(5L);
        localidad.setNombre("San Carlos de Bariloche");
        localidad.setProvincia(provincia);
        localidadRepository.save(localidad);
    }
    
    private void muestraDatos() {
        
        System.out.println();
        System.out.println("Provincias:");
        provinciaRepository.findAll().forEach(System.out::println);
        
        System.out.println();
        System.out.println("Localidades:");
        localidadRepository.findAll().forEach(System.out::println);
    }
    
    private void buscar() {
        
        System.out.println();
        System.out.println("Ingrese entidad a buscar:");
        System.out.println("1 - Provincia, 2 - Localidad, 3 - Salir");
        int opcion = scanner.nextInt();
        if (opcion == 3) {
            return;
        }
        
        System.out.println();
        System.out.println("Ingrese id de entidad: ");
        long id = scanner.nextLong();
        
        Optional<? extends AbstractEntity> optEntity;
        if (opcion == 1) {
            optEntity = provinciaRepository.findById(id);
        } else if (opcion == 2) {
            optEntity = localidadRepository.findById(id);
        } else {
            System.out.println("Opción desconocida");
            buscar();
            return;
        }
        if (optEntity.isPresent()) {
            System.out.println();
            System.out.println(String.format("Entidad encontrada: %s", optEntity.get()));
        } else {
            System.out.println();
            System.out.println("Entidad no encontrada");
        }
        buscar();
    }
    
}
