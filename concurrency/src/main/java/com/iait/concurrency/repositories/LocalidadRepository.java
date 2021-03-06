package com.iait.concurrency.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

import com.iait.concurrency.entities.LocalidadEntity;

public interface LocalidadRepository extends 
        JpaRepository<LocalidadEntity, Long>, QuerydslPredicateExecutor<LocalidadEntity> {
    
}
