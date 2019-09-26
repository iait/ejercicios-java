package com.iait.acceso.base.datos;

import javax.sql.DataSource;

public interface DataSourceProvider {

    DataSource getDataSource();

}
