package com.iait.acceso.base.datos.ctx;

import javax.sql.DataSource;

public interface DataSourceProvider {

    DataSource getDataSource();

}
