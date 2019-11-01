package com.iait.scheduler.time;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Task {
    
    private static Logger LOG = LoggerFactory.getLogger(Task.class);
    
    private Runnable runnable;
    private String name;
    private boolean cancelled;
    
    public Task(Runnable runnable, String name) {
        this.runnable = runnable;
        this.name = name;
    }
    
    public void cancel() {
        cancelled = true;
    }
    
    public void execute() {
        if (!cancelled) {
            LOG.debug("Ejecutando tarea: {}", name);
            Thread thread = new Thread(runnable);
            thread.start();
        }
    }
}
