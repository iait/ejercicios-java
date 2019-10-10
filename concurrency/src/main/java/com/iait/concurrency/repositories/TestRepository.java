package com.iait.concurrency.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

import com.iait.concurrency.entities.TestEntity;

public interface TestRepository extends 
        JpaRepository<TestEntity, Long>, QuerydslPredicateExecutor<TestEntity> {

}
