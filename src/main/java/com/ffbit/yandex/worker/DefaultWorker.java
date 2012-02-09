package com.ffbit.yandex.worker;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;


public class DefaultWorker implements Worker {
    private Runnable task;
    private long period;
    private TimeUnit unit;
    private ScheduledExecutorService scheduler;

    public DefaultWorker(Runnable task, long period, TimeUnit unit) {
        this.task = task;
        this.period = period;
        this.unit = unit;
        scheduler = Executors.newSingleThreadScheduledExecutor();
    }

    @Override
    public void run() {
        scheduler.scheduleAtFixedRate(task, 0, period, unit);
    }

}
