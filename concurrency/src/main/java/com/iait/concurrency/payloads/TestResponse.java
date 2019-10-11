package com.iait.concurrency.payloads;

import com.iait.concurrency.entities.TestEntity;

public class TestResponse {
    
    private Long id;
    private Long value;
    
    public TestResponse(TestEntity entity) {
        id = entity.getId();
        value = entity.getValue();
    }
    
    public Long getId() {
        return id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    
    public Long getValue() {
        return value;
    }
    
    public void setValue(Long value) {
        this.value = value;
    }
}
