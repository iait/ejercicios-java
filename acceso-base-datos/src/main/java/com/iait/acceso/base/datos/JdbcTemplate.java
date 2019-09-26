package com.iait.acceso.base.datos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

public class JdbcTemplate {
    
    private DataSource ds;
    
    public JdbcTemplate(DataSource ds) {
        this.ds = ds;
    }
    
    public <T> List<T> query(String sql, RowMapper<T> rowMapper) {
        List<T> result = new ArrayList<>();
        try (Connection conn = ds.getConnection();
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery(sql)) {
            
            int rowNum = 0;
            while (rs.next()) {
                T t = rowMapper.mapRow(rs, rowNum++);
                result.add(t);
            }
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }
    
    public <T> List<T> query(String sql, RowMapper<T> rowMapper, Object... args) {
        List<T> result = new ArrayList<>();
        try (Connection conn = ds.getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql);) {
            
            for (int i = 0; i < args.length; i++) {
                stmt.setObject(i + 1, args[i]);
            }
            
            try (ResultSet rs = stmt.executeQuery()) {
                
                int rowNum = 0;
                while (rs.next()) {
                    T t = rowMapper.mapRow(rs, rowNum++);
                    result.add(t);
                }
                
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }
}
