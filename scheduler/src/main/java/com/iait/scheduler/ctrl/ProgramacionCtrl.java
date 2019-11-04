package com.iait.scheduler.ctrl;

import java.time.Duration;
import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.iait.scheduler.payloads.requests.OffsetRequest;
import com.iait.scheduler.payloads.requests.ProgramacionRequest;
import com.iait.scheduler.payloads.responses.ProgramacionResponse;
import com.iait.scheduler.payloads.responses.RelojResponse;
import com.iait.scheduler.services.ProgramacionService;
import com.iait.scheduler.time.Scheduler;

@RestController
@RequestMapping("/programaciones")
public class ProgramacionCtrl {
    
    @Autowired
    private ProgramacionService programacionService;
    
    @Autowired
    private Scheduler scheduler;
    
    @PostMapping
    private ResponseEntity<ProgramacionResponse> alta(
            @RequestBody ProgramacionRequest request) {
        
        LocalDateTime fechaHora = request.getFechaHora();
        String mensaje = request.getMensaje();
        programacionService.programar(fechaHora, mensaje);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new ProgramacionResponse(fechaHora));
    }
    
    @PostMapping(path = "/reloj")
    public ResponseEntity<RelojResponse> offset(@RequestBody OffsetRequest request) {
        
        Duration duration = Duration.of(request.getCantidad(), request.getUnidad());
        LocalDateTime fechaHora = scheduler.offset(duration);
        
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new RelojResponse(fechaHora));
    }
    
    @GetMapping(path = "/reloj")
    public ResponseEntity<RelojResponse> buscar() {
        
        LocalDateTime fechaHora = scheduler.getLocalDateTime();
        return ResponseEntity.ok(new RelojResponse(fechaHora));
    }
}
