package com.iait.ejercicio.dtos;

public class ProvinciaFrm implements ProvinciaDto {
    
    private String nombre;
    
    public ProvinciaFrm() {}
    
    @Override
    public String getNombre() {
        return nombre;
    }
    
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    
}
