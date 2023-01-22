package de.bytephil.services;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class Schedular {

    public void mainScheduler() {

        Runnable mainRunnable = new Runnable() {
            public void run() {
                System.out.println("Read Input");
                InputHandler.handleInput();
            }
        };

        ScheduledExecutorService executor = Executors.newScheduledThreadPool(1);
        executor.scheduleAtFixedRate(mainRunnable, 0, 2, TimeUnit.SECONDS);
    }
}
