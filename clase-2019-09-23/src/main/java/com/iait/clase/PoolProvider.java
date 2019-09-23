package com.iait.clase;

import javax.sql.DataSource;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

public class PoolProvider implements DataSourceProvider {

    private static final String URL = "jdbc:h2:mem:testdb;"
            + "DB_CLOSE_DELAY=-1;INIT=runscript from 'src/main/resources/import.sql'";
    private static final String USER = "sa";
    private static final String DRIVER = "org.h2.Driver";

    private DataSource ds;

    public PoolProvider() {
        HikariConfig config = new HikariConfig();
        config.setJdbcUrl(URL);
        config.setUsername(USER);
        config.setMaximumPoolSize(1);
        config.setDriverClassName(DRIVER);

        ds = new HikariDataSource(config);
    }

    @Override
    public DataSource getDataSource() {
        return ds;
    }

}
