package com.iait.concurrency.ctrl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.iait.concurrency.entities.TestEntity;
import com.iait.concurrency.repositories.TestRepository;

@RestController
@RequestMapping("/test")
public class TestCtrl {
    
    @Autowired
    private TestRepository repository;
    
    @GetMapping
    public ResponseEntity<List<TestEntity>> buscar() {
        List<TestEntity> response = repository.findAll();
        return ResponseEntity.ok(response);
    }
    
    @PostMapping
    public ResponseEntity<TestEntity> alta(@RequestBody TestEntity entity) {
        TestEntity response = repository.save(entity);
        return ResponseEntity.ok(response);
    }
}
