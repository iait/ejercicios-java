package com.iait.clase;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ClassPathXmlApplicationContext;

@Configuration
public class App implements Runnable {
    
    public static final ApplicationContext CTX;
    private static final String SQL = "select * from provincias;";
    
    static {
        CTX = new ClassPathXmlApplicationContext("app-config.xml");
    }
    
    @Autowired
    private Connection conn;
    
    public static void main(String[] args) {
        
        App app = CTX.getBean(App.class);
        app.run();
    }
    
    public void run() {
        
        try (PreparedStatement stmt = conn.prepareStatement(SQL);
                ResultSet rs = stmt.executeQuery()) {
            
            while (rs.next()) {
                System.out.println(rs.getString("nombre"));
            }
            
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
