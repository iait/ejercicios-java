package com.iait.acceso.base.datos.ctx.cfg;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

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
        HikariConfig config = new HikariConfig();
        config.setJdbcUrl(url);
        config.setUsername(user);
        config.setPassword(pwd);
        config.setMaximumPoolSize(10);
        config.setDriverClassName(driver);
        return new HikariDataSource(config);
    }
}
