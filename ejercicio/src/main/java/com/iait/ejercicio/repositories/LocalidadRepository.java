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

import com.iait.ejercicio.entities.LocalidadEntity;

@Repository
public class LocalidadRepository implements CrudRepository<LocalidadEntity, Long> {
    
    private static final String SQL_FIND_BY_ID = 
            "select * from localidades where id = :id;";
    private static final String SQL_FIND_ALL = 
            "select * from localidades;";
    private static final String SQL_INSERT = 
            "insert into localidades values (:id, :nombre, :provincia);";
    
    @Autowired
    private NamedParameterJdbcTemplate jdbcTemplate;
    
    @Autowired
    private ProvinciaRepository provinciaRepository;
    
    private final RowMapper<LocalidadEntity> rowMapper = (rs, rowNum) -> {
        LocalidadEntity entity = new LocalidadEntity();
        entity.setId(rs.getLong("id"));
        entity.setNombre(rs.getString("nombre"));
        provinciaRepository.findById(rs.getLong("provincia_id")).ifPresent(entity::setProvincia);
        return entity;
    };
    
    @Override
    public Optional<LocalidadEntity> findById(Long id) {
        
        SqlParameterSource paramSource = new MapSqlParameterSource().addValue("id", id);
        
        Optional<LocalidadEntity> result;
        try {
            LocalidadEntity loc = jdbcTemplate.queryForObject(
                    SQL_FIND_BY_ID, paramSource, rowMapper);
            result = Optional.of(loc);
        } catch (EmptyResultDataAccessException e) {
            result = Optional.empty();
        }
        return result;
    }
    
    @Override
    public List<LocalidadEntity> findAll() {
        
        List<LocalidadEntity> locs = jdbcTemplate.query(SQL_FIND_ALL, rowMapper);
        return locs;
    }
    
    @Override
    public void save(LocalidadEntity entity) {
        
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("id", entity.getId());
        paramMap.put("nombre", entity.getNombre());
        paramMap.put("provincia", entity.getProvincia().getId());
        jdbcTemplate.update(SQL_INSERT, paramMap);
    }
}
