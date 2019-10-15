package com.iait.concurrency.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

import com.iait.concurrency.entities.PersonaEntity;

public interface PersonaRepository extends 
        JpaRepository<PersonaEntity, Long>, QuerydslPredicateExecutor<PersonaEntity> {

}
