package com.iait.scheduler.payloads.responses;

import java.time.LocalDateTime;

public class RelojResponse {
    
    private LocalDateTime fechaHora;
    
    public RelojResponse(LocalDateTime fechaHora) {
        this.fechaHora = fechaHora;
    }
    
    public LocalDateTime getFechaHora() {
        return fechaHora;
    }
}
