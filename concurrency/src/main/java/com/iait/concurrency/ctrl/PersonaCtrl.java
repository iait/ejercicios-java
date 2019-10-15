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

import com.iait.concurrency.entities.PersonaEntity;
import com.iait.concurrency.payloads.PersonaResponse;
import com.iait.concurrency.services.PersonaService;

@RestController
@RequestMapping("/personas")
public class PersonaCtrl {
    
    @Autowired
    private PersonaService service;
    
    @GetMapping(path = "/{id}")
    public ResponseEntity<PersonaResponse> buscar(@PathVariable(name = "id") Long id) {
        PersonaEntity entity = service.buscarPorId(id).orElseThrow(RuntimeException::new);
        PersonaResponse response = new PersonaResponse(entity);
        return ResponseEntity.ok(response);
    }
    
    @GetMapping
    public ResponseEntity<List<PersonaResponse>> buscar() {
        List<PersonaEntity> entities = service.buscar();
        List<PersonaResponse> response = 
                entities.stream().map(PersonaResponse::new).collect(Collectors.toList());
        return ResponseEntity.ok(response);
    }
    
    @PostMapping
    public ResponseEntity<PersonaResponse> alta(@RequestBody String value) {
        PersonaEntity entity = service.alta(value);
        PersonaResponse response = new PersonaResponse(entity);
        return ResponseEntity.ok(response);
    }
}
