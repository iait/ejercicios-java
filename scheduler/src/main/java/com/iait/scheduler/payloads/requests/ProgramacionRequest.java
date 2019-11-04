package com.iait.scheduler.payloads.requests;

import java.time.LocalDateTime;

public class ProgramacionRequest {
    
    private LocalDateTime fechaHora;
    private String mensaje;
    
    public ProgramacionRequest() {}
    
    public LocalDateTime getFechaHora() {
        return fechaHora;
    }
    
    public void setFechaHora(LocalDateTime fechaHora) {
        this.fechaHora = fechaHora;
    }
    
    public String getMensaje() {
        return mensaje;
    }
    
    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }
}
