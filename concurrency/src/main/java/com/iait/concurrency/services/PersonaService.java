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

import com.iait.concurrency.entities.PersonaEntity;
import com.iait.concurrency.repositories.PersonaRepository;

@Service
public class PersonaService {
    
    private static final Logger LOG = LoggerFactory.getLogger(PersonaService.class);
    
    @Autowired
    private PersonaRepository repository;
    
    @PersistenceContext
    private EntityManager em;
    
    @Transactional(readOnly = true)
    public Optional<PersonaEntity> buscarPorId(Long id) {
        return repository.findById(id);
    }
    
    @Transactional(readOnly = true)
    public List<PersonaEntity> buscar() {
        return repository.findAll();
    }
    
    @Transactional
    public PersonaEntity alta(String value) {
        
        LOG.info("Guardando valor: {}", value);
        
        PersonaEntity entity = new PersonaEntity();
        entity.setValue(value);
        
        return repository.save(entity);
    }
}
