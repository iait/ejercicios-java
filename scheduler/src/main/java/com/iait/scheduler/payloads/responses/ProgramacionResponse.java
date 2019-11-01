package com.iait.scheduler.payloads.responses;

import java.time.LocalDateTime;

public class ProgramacionResponse {
    
    private LocalDateTime fechaHora;
    
    public ProgramacionResponse(LocalDateTime fechaHora) {
        this.fechaHora = fechaHora;
    }
    
    public LocalDateTime getFechaHora() {
        return fechaHora;
    }
    
    public void setFechaHora(LocalDateTime fechaHora) {
        this.fechaHora = fechaHora;
    }
}
