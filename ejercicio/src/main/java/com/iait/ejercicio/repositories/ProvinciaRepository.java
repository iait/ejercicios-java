package com.iait.ejercicio.repositories;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import com.iait.ejercicio.entities.ProvinciaEntity;

@Repository
public class ProvinciaRepository implements CrudRepository<ProvinciaEntity, Long> {
    
    private static final String SQL_FIND_BY_ID = 
            "select * from provincias where id = :id;";
    private static final String SQL_FIND_ALL = 
            "select * from provincias;";
    private static final String SQL_INSERT = 
            "insert into provincias values (:id, :nombre);";
    
    @Autowired
    private NamedParameterJdbcTemplate jdbcTemplate;
    
    private final RowMapper<ProvinciaEntity> rowMapper = (rs, rowNum) -> {
        ProvinciaEntity entity = new ProvinciaEntity();
        entity.setId(rs.getLong("id"));
        entity.setNombre(rs.getString("nombre"));
        return entity;
    };
    
    @Override
    public Optional<ProvinciaEntity> findById(Long id) {
        
        SqlParameterSource paramSource = new MapSqlParameterSource().addValue("id", id);
        
        Optional<ProvinciaEntity> result;
        try {
            ProvinciaEntity prov = jdbcTemplate.queryForObject(
                    SQL_FIND_BY_ID, paramSource, rowMapper);
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
        
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("id", entity.getId());
        paramMap.put("nombre", entity.getNombre());
        jdbcTemplate.update(SQL_INSERT, paramMap);
    }
}
