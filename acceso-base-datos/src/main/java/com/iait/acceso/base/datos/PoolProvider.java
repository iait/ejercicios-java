package com.iait.acceso.base.datos;

import javax.sql.DataSource;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

public class PoolProvider implements DataSourceProvider {
    
    public static PoolProvider instance = new PoolProvider();
    
    private static final String URL = 
            "jdbc:sqlserver://sqlserver\\sql2012;databaseName=SAD_MAIN_DEV";
    private static final String USER = "eivfinanciero2";
    private static final String PWD = "rv760";
    private static final String DRIVER = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
    
    private DataSource ds;
    
    private PoolProvider() {
        HikariConfig config = new HikariConfig();
        config.setJdbcUrl(URL);
        config.setUsername(USER);
        config.setPassword(PWD);
        config.setMaximumPoolSize(10);
        config.setDriverClassName(DRIVER);

        ds = new HikariDataSource(config);
    }
    
    @Override
    public DataSource getDataSource() {
        return ds;
    }
}
