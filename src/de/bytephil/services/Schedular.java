package de.bytephil.services;

import de.bytephil.enums.MessageType;
import org.apache.commons.logging.Log;

import de.bytephil.utils.Console;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.TimeUnit;

public class Schedular {

    public static Date lastSaved = null;

    public static void startSchedular() {
        RestAPI.main();
        try {
            Thread.sleep(5000);

            /*
            String[] liveData = RestAPI.data;
            new LogService().writetoFile("Leistung Gesamt: " + liveData[2]);
            new LogService().writetoFile("KWh Total: " + liveData[6]);

             */
            saveDayData();
            startSchedular();
        } catch (InterruptedException | IOException e) {
            startSchedular();
            e.printStackTrace();

        }
    }

    public static void saveDayData() throws IOException {
        Date currentDate = new Date();
        int wh = Integer.parseInt(RestAPI.data[7].replace(",", ""));

        if (currentDate.getHours() == 18) {
            if (lastSaved == null || currentDate.getDate() != lastSaved.getDate()) {

                LogService.writetoFile("WH_TODAY: " + wh);
                Console.printout("Saved data from today!", MessageType.INFO);
                lastSaved = currentDate;
            }
        }
    }

}
/*
            System.out.println("Leistung Gesamt: " + liveData[2]);
            System.out.println("Spannung: " + liveData[0]);
            System.out.println("Temperatur: " + liveData[5]);
            System.out.println("KWh Total: " + liveData[6]);
 */