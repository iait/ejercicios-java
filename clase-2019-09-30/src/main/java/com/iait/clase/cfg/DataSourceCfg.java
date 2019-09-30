package com.iait.clase.cfg;

import java.sql.Connection;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.h2.jdbcx.JdbcDataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DataSourceCfg {
    
    @Value("${app.url}")
    private String url;
    
    @Value("${app.user}'")
    private String user;
    
    @Bean
    DataSource getDataSource() {
        JdbcDataSource ds = new JdbcDataSource();
        ds.setUrl(url);
        ds.setUser(user);
        return ds;
    }
    
    @Bean
    Connection getConnection() {
        DataSource ds = getDataSource();
        try {
            return ds.getConnection();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
