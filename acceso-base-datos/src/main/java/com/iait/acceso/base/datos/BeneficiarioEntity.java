package com.iait.acceso.base.datos;

public class BeneficiarioEntity {
    
    private Long id;
    private String nombre;
    private String apellido;
    
    public BeneficiarioEntity(Long id, String nombre, String apellido) {
        this.id = id;
        this.nombre = nombre;
        this.apellido = apellido;
    }

    public Long getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public String getApellido() {
        return apellido;
    }

    @Override
    public String toString() {
        return "BeneficiarioEntity [id=" + id + ", nombre=" + nombre + ", apellido=" + apellido 
                + "]";
    }
}
