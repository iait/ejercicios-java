package com.iait.ejercicio.repositories;

import java.sql.Types;
import java.util.List;
import java.util.Optional;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.SqlParameterValue;
import org.springframework.stereotype.Repository;

import com.iait.ejercicio.entities.ProvinciaEntity;

@Repository
public class ProvinciaRepository implements CrudRepository<ProvinciaEntity, Long> {
    
    private static final String SQL_FIND_BY_ID = "select * from provincias where id = ?;";
    
    private JdbcTemplate jdbcTemplate;
    
    private final RowMapper<ProvinciaEntity> rowMapper = (rs, rowNum) -> {
        ProvinciaEntity entity = new ProvinciaEntity();
        entity.setId(rs.getLong("id"));
        entity.setNombre(rs.getString("nombre"));
        return entity;
    };
    
    @Autowired
    public ProvinciaRepository(DataSource ds) {
        jdbcTemplate = new JdbcTemplate(ds);
    }
    
    @Override
    public Optional<ProvinciaEntity> findById(Long id) {
        
        SqlParameterValue paramId = new SqlParameterValue(Types.BIGINT, id);
        
        ProvinciaEntity entity = jdbcTemplate.queryForObject(SQL_FIND_BY_ID, rowMapper, paramId);
        return Optional.ofNullable(entity);
    }
    
    @Override
    public List<ProvinciaEntity> findAll() {
        // TODO Auto-generated method stub
        return null;
    }
}
