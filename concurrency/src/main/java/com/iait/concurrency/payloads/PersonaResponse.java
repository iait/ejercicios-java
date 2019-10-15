package com.iait.concurrency.payloads;

import com.iait.concurrency.entities.PersonaEntity;

public class PersonaResponse {
    
    private Long id;
    private String value;
    
    public PersonaResponse(PersonaEntity entity) {
        id = entity.getId();
        value = entity.getValue();
    }
    
    public Long getId() {
        return id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    
    public String getValue() {
        return value;
    }
    
    public void setValue(String value) {
        this.value = value;
    }
}
