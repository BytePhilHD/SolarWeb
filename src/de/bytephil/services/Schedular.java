package de.bytephil.services;

import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.TimeUnit;

public class Schedular {


    public static void startSchedular() {
        RestAPI.main();
        try {
            Thread.sleep(5000);
            startSchedular();
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
