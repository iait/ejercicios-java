package com.iait.scheduler.services;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.iait.scheduler.time.Scheduler;
import com.iait.scheduler.time.Task;

@Service
public class ProgramacionService {
    
    @Autowired
    private Scheduler scheduler;
    
    public void programar(LocalDateTime dateTime, String mensaje) {
        scheduler.schedule(new Task(() -> System.out.println(mensaje), mensaje), dateTime);
    }
}
