package com.iait.ejercicio.repositories;

import java.sql.Types;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.SqlParameterValue;
import org.springframework.stereotype.Repository;

import com.iait.ejercicio.entities.ProvinciaEntity;

@Repository
public class ProvinciaRepository implements CrudRepository<ProvinciaEntity, Long> {
    
    private static final String SQL_FIND_BY_ID = "select * from provincias where id = ?;";
    private static final String SQL_FIND_ALL = "select * from provincias;";
    private static final String SQL_INSERT = "insert into provincias values (?, ?);";
    
    @Autowired
    private JdbcTemplate jdbcTemplate;
    
    private final RowMapper<ProvinciaEntity> rowMapper = (rs, rowNum) -> {
        ProvinciaEntity entity = new ProvinciaEntity();
        entity.setId(rs.getLong("id"));
        entity.setNombre(rs.getString("nombre"));
        return entity;
    };
    
    @Override
    public Optional<ProvinciaEntity> findById(Long id) {
        
        SqlParameterValue paramId = new SqlParameterValue(Types.BIGINT, id);
        
        Optional<ProvinciaEntity> result;
        try {
            ProvinciaEntity prov = jdbcTemplate.queryForObject(SQL_FIND_BY_ID, rowMapper, paramId);
            result = Optional.of(prov);
        } catch (EmptyResultDataAccessException e) {
            result = Optional.empty();
        }
        return result;
    }
    
    @Override
    public List<ProvinciaEntity> findAll() {
        
        List<ProvinciaEntity> provincias = jdbcTemplate.query(SQL_FIND_ALL, rowMapper);
        return provincias;
    }
    
    @Override
    public void save(ProvinciaEntity entity) {
        
        jdbcTemplate.update(SQL_INSERT, entity.getId(), entity.getNombre());
    }
}
