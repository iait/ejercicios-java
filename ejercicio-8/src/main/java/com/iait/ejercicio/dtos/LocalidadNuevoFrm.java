package com.iait.ejercicio.dtos;

public class LocalidadNuevoFrm {

    private String nombre;
    private Long provinciaId;
    
    public LocalidadNuevoFrm() {
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Long getProvinciaId() {
        return provinciaId;
    }

    public void setProvinciaId(Long provinciaId) {
        this.provinciaId = provinciaId;
    }
    
}
