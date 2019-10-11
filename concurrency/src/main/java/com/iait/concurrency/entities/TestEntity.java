package com.iait.concurrency.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.TableGenerator;

@Entity
@Table(name = "test")
public class TestEntity {
    
    @Id
    @TableGenerator(name = "table_generator", table = "id_gen", pkColumnName = "gen_name", 
        valueColumnName = "gen_val")
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "table_generator") 
    private Long id;
    
    @Column(name = "value")
    private Long value;
    
    public TestEntity() {}
    
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
    
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        return result;
    }
    
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof TestEntity)) {
            return false;
        }
        TestEntity other = (TestEntity) obj;
        if (getId() == null) {
            if (other.getId() != null) {
                return false;
            }
        } else if (!getId().equals(other.getId())) {
            return false;
        }
        return true;
    }
    
    @Override
    public String toString() {
        return "TestEntity [id=" + id + ", value=" + value + "]";
    }
}
