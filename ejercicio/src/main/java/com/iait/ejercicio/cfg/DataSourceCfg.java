package com.iait.ejercicio.cfg;

import javax.sql.DataSource;

import org.h2.jdbcx.JdbcDataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DataSourceCfg {
    
    @Value("${app.url}")
    private String url;
    
    @Value("${app.user}")
    private String user;
    
    @Value("${app.pwd}")
    private String pwd;
    
    @Value("${app.driver}")
    private String driver;
    
    @Bean
    DataSource getDataSource() {
        JdbcDataSource ds = new JdbcDataSource();
        ds.setUrl("jdbc:h2:mem:testdb;INIT=runscript from 'src/main/resources/import.sql'");
        ds.setUser("sa");
        return ds;
    }
}
