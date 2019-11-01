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

@RestController
@RequestMapping("/programaciones")
public class ProgramacionCtrl {
    
    @Autowired
    private ProgramacionService programacionService;
    
    @PostMapping
    private ResponseEntity<ProgramacionResponse> alta(
            @RequestBody ProgramacionRequest request) {
        
        LocalDateTime fechaHora = request.getFechaHora();
        programacionService.schedule(fechaHora);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new ProgramacionResponse(fechaHora));
    }
    
    @PostMapping(path = "/reloj")
    public ResponseEntity<RelojResponse> offset(@RequestBody OffsetRequest request) {
        
        Duration duration = Duration.of(request.getCantidad(), request.getUnidad());
        LocalDateTime fechaHora = programacionService.offset(duration);
        
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new RelojResponse(fechaHora));
    }
    
    @GetMapping(path = "/reloj")
    public ResponseEntity<RelojResponse> buscar() {
        
        LocalDateTime fechaHora = programacionService.getLocalDateTime();
        return ResponseEntity.ok(new RelojResponse(fechaHora));
    }
}
