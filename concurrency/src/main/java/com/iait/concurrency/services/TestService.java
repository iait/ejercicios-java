package com.iait.concurrency.services;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.iait.concurrency.entities.QTestEntity;
import com.iait.concurrency.entities.TestEntity;
import com.iait.concurrency.repositories.TestRepository;
import com.querydsl.jpa.impl.JPAQueryFactory;

@Service
public class TestService {
    
    private static final Logger LOG = LoggerFactory.getLogger(TestService.class);
    
    @Autowired
    private TestRepository repository;
    
    @PersistenceContext
    private EntityManager em;
    
    @Transactional(readOnly = true)
    public Optional<TestEntity> buscarPorId(Long id) {
        return repository.findById(id);
    }
    
    @Transactional(readOnly = true)
    public List<TestEntity> buscar() {
        return repository.findAll();
    }
    
    @Transactional
    public TestEntity alta(Long value) throws InterruptedException {
        
        LOG.info("Guardando valor: {}", value);
        
        TestEntity entity = new TestEntity();
        entity.setValue(value);
        
//        JPAQueryFactory queryFactory = new JPAQueryFactory(em);
//        QTestEntity q = QTestEntity.testEntity;
//        
//        Long id = queryFactory.from(q).select(q.id.max()).fetchFirst();
//        if (id == null) {
//            id = 0L;
//        }
//        id++;
//        entity.setId(id);
        return repository.save(entity);
    }
}
