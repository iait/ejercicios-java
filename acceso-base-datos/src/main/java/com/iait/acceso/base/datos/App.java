package com.iait.acceso.base.datos;

import java.util.List;

import javax.sql.DataSource;

public class App {
    
    public static void main(String[] args) {
        App app = new App();
        app.run();
    }
    
    public void run() {
        
        DataSourceProvider dsp = PoolProvider.instance;
        DataSource ds = dsp.getDataSource();
        
        JdbcTemplate template = new JdbcTemplate(ds);
        List<BeneficiarioEntity> beneficiarios = template.query(
                "select id, nombre, apellido from beneficiarios where estado_civil_id = ?", 
                (rs, rowNum) -> {
                    Long id = rs.getLong("id");
                    String nombre = rs.getString("nombre");
                    String apellido = rs.getString("apellido");
                    return new BeneficiarioEntity(id, nombre, apellido);
                }, 6);
        
        beneficiarios.forEach(b -> System.out.println(b));
    }
}
