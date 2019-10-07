package com.iait.clase;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.sql.DataSource;

import org.h2.jdbcx.JdbcDataSource;
import org.h2.tools.Server;

public class AppAutocommit {
    
    private static final String SQL = "select * from provincias;";
    private static final String SQL_INSERT = "insert into provincias (id, nombre) values (?, ?)";
    
    public static void main(String[] args) throws SQLException {
        AppAutocommit app = new AppAutocommit();
        app.run();
    }
    
    public void run() throws SQLException {
        
        Server.main();
        
        try (Connection conn = getDataSource().getConnection();
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
        
    }
    
    public void run1() {
        
        try (Connection conn = getDataSource().getConnection();
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery(SQL)) {
            
            while (rs.next()) {
                System.out.println("Provincia: " + rs.getString("nombre"));
            }
            
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    
    private DataSource getDataSource() {
        
        JdbcDataSource ds = new JdbcDataSource();
        ds.setUrl("jdbc:h2:mem:testdb;INIT=runscript from 'src/main/resources/import.sql'");
        ds.setUser("sa");
        return ds;
    }
}
