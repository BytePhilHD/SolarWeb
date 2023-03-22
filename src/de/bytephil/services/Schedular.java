package de.bytephil.services;

import org.apache.commons.logging.Log;

import java.io.IOException;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.TimeUnit;

public class Schedular {


    public static void startSchedular() {
        RestAPI.main();
        try {
            Thread.sleep(5000);     // TODO Zeit auch erhöhen auf 15 oder 30 Sek

            String[] liveData = RestAPI.data;
            new LogService().writetoFile("Leistung Gesamt: " + liveData[2]);
            new LogService().writetoFile("KWh Total: " + liveData[6]);

            startSchedular();
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            startSchedular();
            e.printStackTrace();
            return;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
/*
            System.out.println("Leistung Gesamt: " + liveData[2]);
            System.out.println("Spannung: " + liveData[0]);
            System.out.println("Temperatur: " + liveData[5]);
            System.out.println("KWh Total: " + liveData[6]);
 */