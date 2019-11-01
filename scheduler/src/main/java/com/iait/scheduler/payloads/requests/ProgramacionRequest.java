package com.iait.scheduler.payloads.requests;

import java.time.LocalDateTime;

public class ProgramacionRequest {
    
    private LocalDateTime fechaHora;
    
    public ProgramacionRequest() {}
    
    public LocalDateTime getFechaHora() {
        return fechaHora;
    }
    
    public void setFechaHora(LocalDateTime fechaHora) {
        this.fechaHora = fechaHora;
    }
}
