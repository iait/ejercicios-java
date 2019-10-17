package com.iait.ejercicio;

import java.sql.SQLException;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.datasource.init.DatabasePopulator;
import org.springframework.jdbc.datasource.init.DatabasePopulatorUtils;

import com.iait.ejercicio.dtos.LocalidadFrm;
import com.iait.ejercicio.dtos.ProvinciaFrm;
import com.iait.ejercicio.entities.LocalidadEntity;
import com.iait.ejercicio.entities.ProvinciaEntity;
import com.iait.ejercicio.services.LocalidadService;
import com.iait.ejercicio.services.ProvinciaService;

@Configuration
public class App {

    public static final ApplicationContext CONTEXT;
    
    static {
        CONTEXT = new ClassPathXmlApplicationContext("app-config.xml");
    }
    
    public static void main(String[] args) throws SQLException {
        App app = CONTEXT.getBean(App.class);
        app.run();
    }
    
    @Autowired private DataSource dataSource;
    @Autowired private DatabasePopulator populator;
    
    @Autowired private ProvinciaService provinciaService;
    @Autowired private LocalidadService localidadService;
    
    public void run() throws SQLException {
        
        DatabasePopulatorUtils.execute(populator, dataSource);
        
        // crea provincia
        ProvinciaFrm provinciaNueva = new ProvinciaFrm();
        provinciaNueva.setNombre("Buenos Aires");
        Long provinciaNuevaId = provinciaService.nueva(provinciaNueva).getId();
        
        // busca provincia
        ProvinciaEntity provinciaNuevaEntity = provinciaService.buscarPorId(provinciaNuevaId).get();
        System.out.println(String.format("Nueva provincia: %s", provinciaNuevaEntity));
        
        // crea localidad
        LocalidadFrm localidadNueva = new LocalidadFrm();
        localidadNueva.setNombre("Olivos");
        localidadNueva.setProvinciaId(provinciaNuevaId);
        Long localidadNuevaId = localidadService.nueva(localidadNueva).getId();
        
        // busca localidad
        LocalidadEntity localidadNuevaEntity = localidadService.buscarPorId(localidadNuevaId).get();
        System.out.println(String.format("Nueva localidad: %s", localidadNuevaEntity));
        
        // elimina localidad y provincia
        try {
            provinciaService.eliminar(provinciaNuevaId);
        } catch (RuntimeException e) {
            e.printStackTrace();
        }
        LocalidadEntity localidadEliminada = localidadService.eliminar(localidadNuevaId);
        System.out.println(String.format("Localidad eliminada: %s", localidadEliminada));
        ProvinciaEntity provinciaEliminada = provinciaService.eliminar(provinciaNuevaId);
        System.out.println(String.format("Provincia eliminada: %s", provinciaEliminada));
    }
}
