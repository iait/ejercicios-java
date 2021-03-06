package com.iait.ejercicio.repositories;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import java.util.Optional;

import javax.sql.DataSource;

import org.h2.jdbcx.JdbcDataSource;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;

import com.iait.ejercicio.entities.ProvinciaEntity;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = ProvinciaRepositoryIT.TestCfg.class)
public class ProvinciaRepositoryIT {

    @Configuration
    @EnableTransactionManagement
    public static class TestCfg {
        
        @Bean
        public DataSource getDataSource() {
            JdbcDataSource ds = new JdbcDataSource();
            ds.setUrl("jdbc:h2:mem:testdb"
                    + ";INIT=runscript from 'src/test/resources/test-provincias.sql'");
            ds.setUser("sa");
            return ds;
        }
        
        @Bean
        public PlatformTransactionManager transactionManager() {
            return new DataSourceTransactionManager(getDataSource());
        }
    }
    
    @Test
    public void whenProvinciaId1_thenIsPresent() {
        
        ProvinciaRepository provinciaRepository = new ProvinciaRepository(dataSource);
        Optional<ProvinciaEntity> optional = provinciaRepository.findById(1L);
        
        assertThat(optional).isPresent();
    }
    
    @Test
    public void whenProvinciaId10_thenIsNotPresent() {
        
        ProvinciaRepository provinciaRepository = new ProvinciaRepository(dataSource);
        Optional<ProvinciaEntity> optional = provinciaRepository.findById(10L);
        
        assertThat(optional).isNotPresent();
    }
    
    @Test
    public void givenAllProvincias_whenFindById_thenFindProvincia() {
        
        ProvinciaRepository provinciaRepository = new ProvinciaRepository(dataSource);
        List<ProvinciaEntity> provinciaEntities = provinciaRepository.findAll();
        
        assertThat(provinciaEntities).hasSize(5);
        
        provinciaEntities.forEach(item -> {
            
            Optional<ProvinciaEntity> optional = provinciaRepository.findById(item.getId());
            
            assertThat(optional)
                    .contains(item);
        });
    }
    
    @Test
    public void whenFindMaxId_thenMaxId5() {
        
        ProvinciaRepository provinciaRepository = new ProvinciaRepository(dataSource);
        Optional<Long> optional = provinciaRepository.maxId();
        
        assertThat(optional).isPresent().contains(5L);
    }
    
    @Test
    @Transactional
    public void givenNewProvincia_whenId_thenSave() {
        
        ProvinciaEntity entity = new ProvinciaEntity(10L, "NUEVA");
        
        ProvinciaRepository provinciaRepository = new ProvinciaRepository(dataSource);
        provinciaRepository.save(entity);
        
        Optional<ProvinciaEntity> optional = provinciaRepository.findById(10L);
        assertThat(optional).contains(entity);
    }

    @Test
    @Transactional
    public void givenNewProvincia_whenNoId_thenSave() {
        
        ProvinciaEntity entity = new ProvinciaEntity(-1L, "NUEVA");
        
        ProvinciaRepository provinciaRepository = new ProvinciaRepository(dataSource);
        provinciaRepository.save(entity);
        
        Optional<ProvinciaEntity> optional = provinciaRepository.findById(entity.getId());
        assertThat(optional).contains(entity);
    }
    
    @Test
    @Transactional
    public void givenExistProvincia_thenUpdated() {
        
        final long provinciaId = 1L;
        final String provinciaNombre = "TEST";
        
        ProvinciaRepository provinciaRepository = new ProvinciaRepository(dataSource);
        
        Optional<ProvinciaEntity> optional = provinciaRepository.findById(provinciaId);
        assertThat(optional).isPresent();
        
        ProvinciaEntity entity = optional.get();
        
        entity.setNombre(provinciaNombre);
        provinciaRepository.save(entity);
        
        assertThat(entity.getNombre()).isEqualTo(provinciaNombre);
        
        Optional<ProvinciaEntity> saved = provinciaRepository.findById(provinciaId);
        assertThat(saved).isPresent().contains(entity);
        
        assertThat(saved.get().getNombre()).isEqualTo(provinciaNombre);
        
    }
    
    @Test
    @Transactional
    public void eliminaProvinciaExistente() {
        
        final long provinciaId = 1L;
        
        ProvinciaRepository provinciaRepository = new ProvinciaRepository(dataSource);
        
        Optional<ProvinciaEntity> before = provinciaRepository.findById(provinciaId);
        assertThat(before).isPresent();
        
        Optional<ProvinciaEntity> deleted = provinciaRepository.deleteById(provinciaId);
        assertThat(deleted).isPresent();
        assertThat(deleted).isEqualTo(before);
        
        Optional<ProvinciaEntity> after = provinciaRepository.findById(provinciaId);
        assertThat(after).isNotPresent();
    }
    
    @Test
    @Transactional
    public void eliminaProvinciaNoExistente() {
        
        final long provinciaId = 10L;
        
        ProvinciaRepository provinciaRepository = new ProvinciaRepository(dataSource);
        
        Optional<ProvinciaEntity> before = provinciaRepository.findById(provinciaId);
        assertThat(before).isNotPresent();
        
        Optional<ProvinciaEntity> deleted = provinciaRepository.deleteById(provinciaId);
        assertThat(deleted).isNotPresent();
    }
    
    @Autowired DataSource dataSource;
}
