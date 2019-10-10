package com.iait.ejercicio.cfg;

import javax.sql.DataSource;

import org.h2.jdbcx.JdbcDataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.datasource.init.DatabasePopulatorUtils;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;

@Configuration
public class DataSourceCfg {
    
    @Value("${app.url}")
    private String url;
    
    @Value("${app.user}")
    private String user;
    
    @Value("${app.pwd}")
    private String pwd;
    
    @Value("classpath:import.sql")
    private Resource importScript;
    
    @Bean
    public DataSource getDataSource() {
        JdbcDataSource ds = new JdbcDataSource();
        ds.setUrl(url);
        ds.setUser(user);
        ds.setPassword(pwd);
        ResourceDatabasePopulator populator = new ResourceDatabasePopulator();
        populator.addScript(importScript);
        DatabasePopulatorUtils.execute(populator, ds);
        return ds;
    }
    
    @Bean
    public NamedParameterJdbcTemplate getJdbcTemplate(DataSource ds) {
        return new NamedParameterJdbcTemplate(ds);
    }
}
