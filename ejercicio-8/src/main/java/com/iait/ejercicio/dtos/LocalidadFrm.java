package com.iait.ejercicio.dtos;

public class LocalidadFrm implements LocalidadDto {

    private String nombre;
    private Long provinciaId;
    
    public LocalidadFrm() {}

    @Override
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @Override
    public Long getProvinciaId() {
        return provinciaId;
    }

    public void setProvinciaId(Long provinciaId) {
        this.provinciaId = provinciaId;
    }
    
}
