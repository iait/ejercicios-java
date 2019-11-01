package com.iait.scheduler.services;

import java.time.Clock;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.SortedMap;
import java.util.TreeMap;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.iait.scheduler.time.Task;

@Service
public class ProgramacionService {
    
    private static Logger LOG = LoggerFactory.getLogger(ProgramacionService.class);
    
    private Clock clock;
    private SortedMap<Long, List<Task>> tasksQueue;
    
    @PostConstruct
    public void inicializar() {
        tasksQueue = new TreeMap<>();
        clock = Clock.systemDefaultZone();
    }
    
    @Scheduled(initialDelay = 60000, fixedDelay = 60000)
    public void scheduleFixedDelayTask() {
        LOG.debug("Inicio de ejecución de tareas programadas");
        if (tasksQueue.isEmpty()) {
            LOG.debug("No se encontró ninguna tarea para ejecutar");
            return;
        }
        Long taskMillis = tasksQueue.firstKey();
        Long currentMillis = clock.millis();
        while (taskMillis <= currentMillis) {
            List<Task> tasks = tasksQueue.remove(taskMillis);
            for (Task task : tasks) {
                task.execute();
            }
            if (tasksQueue.isEmpty()) {
                LOG.debug("No hay más tareas para ejecutar");
                return;
            }
            taskMillis = tasksQueue.firstKey();
        }
        LOG.debug("No hay más tareas para ejecutar");
    }
    
    public Clock getClock() {
        return clock;
    }
    
    public LocalDateTime getLocalDateTime() {
        return clock.instant().atZone(clock.getZone()).toLocalDateTime();
    }
    
    public LocalDateTime offset(Duration offset) {
        clock = Clock.offset(clock, offset);
        return clock.instant().atZone(clock.getZone()).toLocalDateTime();
    }
    
    public void schedule(LocalDateTime dateTime) {
        ZonedDateTime zonedDateTime = dateTime.atZone(clock.getZone());
        Long millis = zonedDateTime.toInstant().toEpochMilli();
        List<Task> tasks = tasksQueue.get(millis);
        if (tasks == null) {
            tasks = new ArrayList<>();
            tasksQueue.put(millis, tasks);
        }
        Runnable runnable = () -> System.out.println("Hola!");
        tasks.add(new Task(runnable, "test"));
    }
    
}
