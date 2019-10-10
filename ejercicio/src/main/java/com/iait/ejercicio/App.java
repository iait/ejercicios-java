package com.iait.ejercicio;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ClassPathXmlApplicationContext;

@Configuration
public class App {
    
    public static final ApplicationContext CTX;
    
    static {
        CTX = new ClassPathXmlApplicationContext("app-config.xml");
    }
    
    private static final String SQL = "select * from provincias;";
    private static final String SQL_INSERT = "insert into provincias (id, nombre) values (?, ?)";
    
    public static void main(String[] args) throws SQLException {
        App app = CTX.getBean(App.class);
        app.run();
    }
    
    @Autowired
    private DataSource ds;
    
    public void run() throws SQLException {
        
        try (Connection conn = ds.getConnection();
                PreparedStatement stmt = conn.prepareStatement(SQL_INSERT);) {
            
            conn.setAutoCommit(false);
            
            for (int i = 10; i < 13; i++) {
                
                stmt.setInt(1, i);
                stmt.setString(2, "Provincia " + i);
                
                stmt.execute();
            }
            
            System.out.println("listo paso 1");
            
            stmt.setInt(1, 12);
            stmt.setString(2, "Provincia");
            
            try {
                stmt.execute();
            } catch (Exception e) {
                conn.rollback();
            }
            
            conn.commit();
            
            System.out.println("listo paso 2");
            
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        
        buscaProvincias();
    }
    
    public void buscaProvincias() {
        
        try (Connection conn = ds.getConnection();
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery(SQL)) {
            
            while (rs.next()) {
                System.out.println("Provincia: " + rs.getString("nombre"));
            }
            
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
