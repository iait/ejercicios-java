package com.iait.acceso.base.datos.ctx.entities;

public class EstadoCivilEntity {
    
    private Long id;
    private String nombre;
    
    public EstadoCivilEntity(Long id, String nombre) {
        this.id = id;
        this.nombre = nombre;
    }
    
    public Long getId() {
        return id;
    }
    
    public String getNombre() {
        return nombre;
    }
}
