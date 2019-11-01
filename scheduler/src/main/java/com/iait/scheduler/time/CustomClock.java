package com.iait.scheduler.time;

import java.time.Clock;
import java.time.Duration;
import java.time.Instant;
import java.time.ZoneId;

public class CustomClock extends Clock {
    
    private final Clock baseClock;
    private final Duration offset;
    
    public CustomClock() {
        baseClock = Clock.systemDefaultZone();
        offset = Duration.ZERO;
    }
    
    private CustomClock(Clock baseClock, Duration offset) {
        this.baseClock = baseClock;
        this.offset = offset;
    }
    
    @Override
    public ZoneId getZone() {
        return baseClock.getZone();
    }
    
    @Override
    public Clock withZone(ZoneId zone) {
        if (zone.equals(baseClock.getZone())) {
            return this;
        }
        return new CustomClock(baseClock.withZone(zone), offset);
    }
    
    @Override
    public long millis() {
        return Math.addExact(baseClock.millis(), offset.toMillis());
    }
    
    @Override
    public Instant instant() {
        return Instant.ofEpochMilli(millis());
    }
}
