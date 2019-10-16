package com.iait.concurrency.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "personas")
public class PersonaEntity {
    
    @Id
//    @TableGenerator(name = "table_generator", table = "id_gen", pkColumnName = "gen_name",
//            valueColumnName = "gen_val")
//  @GeneratedValue(strategy = GenerationType.TABLE, generator = "table_generator")
//    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    
    @Column(name = "value")
    private String value;
    
    public PersonaEntity() {}
    
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
        if (!(obj instanceof PersonaEntity)) {
            return false;
        }
        PersonaEntity other = (PersonaEntity) obj;
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
        return "PersonaEntity [id=" + id + ", value=" + value + "]";
    }
}
