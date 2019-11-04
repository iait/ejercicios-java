package com.iait.scheduler.time;

import java.time.Clock;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.SortedMap;
import java.util.TreeMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class Scheduler {
    
    private static Logger LOG = LoggerFactory.getLogger(Scheduler.class);
    
    private Clock clock;
    private SortedMap<Long, List<Task>> tasksQueue;
    
    public Scheduler() {
        tasksQueue = new TreeMap<>();
        clock = Clock.systemDefaultZone();
    }
    
    /**
     * Proceso que se ejecuta cada minuto que busca y ejecuta las tareas programadas.
     */
    @Scheduled(initialDelay = 60000, fixedDelay = 60000)
    public void scheduleFixedDelayTask() {
        LOG.debug("Inicio de ejecución de tareas programadas. "
                + "Fecha interna: {}", 
                getLocalDateTime());
        
        Integer count = 0;
        if (!tasksQueue.isEmpty()) {
            Long taskMillis = tasksQueue.firstKey();
            Long currentMillis = clock.millis();
            while (taskMillis <= currentMillis) {
                List<Task> tasks = tasksQueue.remove(taskMillis);
                for (Task task : tasks) {
                    task.execute();
                    count++;
                }
                if (tasksQueue.isEmpty()) {
                    break;
                }
                taskMillis = tasksQueue.firstKey();
            }
        }
        
        LOG.debug("Finaliza ejecución de tareas programadas. "
                + "Fecha interna: {}. "
                + "Total de tareas ejecutadas: {}", 
                getLocalDateTime(),
                count);
    }
    
    /**
     * Fecha y hora actual interna.
     * 
     * @return Fecha y hora actual interna.
     */
    public LocalDateTime getLocalDateTime() {
        return clock.instant().atZone(clock.getZone()).toLocalDateTime();
    }
    
    /**
     * Fecha actual interna.
     * 
     * @return Fecha actual interna.
     */
    public LocalDate getLocalDate() {
        return clock.instant().atZone(clock.getZone()).toLocalDate();
    }
    
    /**
     * Adelanta el reloj interno.
     * 
     * @param offset Lapso de tiempo a adelantar el reloj interno.
     * @return Fecha y hora resultado.
     */
    public LocalDateTime offset(Duration offset) {
        clock = Clock.offset(clock, offset);
        return getLocalDateTime();
    }
    
    /**
     * Programa la tarea para ser ejecutada en una determinada fecha y hora, 
     * con respecto al reloj interno.
     * 
     * @param task Tarea a ejecutar.
     * @param dateTime Momento en el cual ejecutar la tarea.
     */
    public void schedule(Task task, LocalDateTime dateTime) {
        ZonedDateTime zonedDateTime = dateTime.atZone(clock.getZone());
        Long millis = zonedDateTime.toInstant().toEpochMilli();
        List<Task> tasks = tasksQueue.get(millis);
        if (tasks == null) {
            tasks = new ArrayList<>();
            tasksQueue.put(millis, tasks);
        }
        tasks.add(task);
    }
}
