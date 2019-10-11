package com.iait.concurrency.ctrl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.iait.concurrency.entities.TestEntity;
import com.iait.concurrency.payloads.TestResponse;
import com.iait.concurrency.services.TestService;

@RestController
@RequestMapping("/test")
public class TestCtrl {
    
    @Autowired
    private TestService service;
    
    @GetMapping(path = "/{id}")
    public ResponseEntity<TestResponse> buscar(@PathVariable(name = "id") Long id) {
        TestEntity entity = service.buscarPorId(id).orElseThrow(RuntimeException::new);
        TestResponse response = new TestResponse(entity);
        return ResponseEntity.ok(response);
    }
    
    @GetMapping
    public ResponseEntity<List<TestResponse>> buscar() {
        List<TestEntity> entities = service.buscar();
        List<TestResponse> response = 
                entities.stream().map(TestResponse::new).collect(Collectors.toList());
        return ResponseEntity.ok(response);
    }
    
    @PostMapping
    public ResponseEntity<TestResponse> alta(@RequestBody Long noId) throws InterruptedException {
        TestEntity entity = service.alta(noId);
        TestResponse response = new TestResponse(entity);
        return ResponseEntity.ok(response);
    }
}
