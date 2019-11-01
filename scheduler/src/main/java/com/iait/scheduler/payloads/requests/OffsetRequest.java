package com.iait.scheduler.payloads.requests;

import java.time.temporal.ChronoUnit;

public class OffsetRequest {
    
    private ChronoUnit unidad;
    private Long cantidad;
    
    public OffsetRequest() {}

    public ChronoUnit getUnidad() {
        return unidad;
    }

    public void setUnidad(ChronoUnit unidad) {
        this.unidad = unidad;
    }

    public Long getCantidad() {
        return cantidad;
    }

    public void setCantidad(Long cantidad) {
        this.cantidad = cantidad;
    }
}
