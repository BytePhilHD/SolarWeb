package de.bytephil.services;

import de.bytephil.enums.MessageType;

import de.bytephil.utils.Console;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

public class Schedular {

    public static Date lastSaved = null;

    public static void startSchedular() {
        Timer timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                try {
                    RestAPI.main();
                    saveDayData();
             //       FileReader.readFile();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }, 0, 5000);
    }

    public static void saveDayData() throws IOException {
        Date currentDate = new Date();
        int wh = Integer.parseInt(RestAPI.data[7].replace(",", ""));

        if (currentDate.getHours() == 19) {
            if (lastSaved == null || currentDate.getDate() != lastSaved.getDate()) {

                LogService.writetoFile("WH_TODAY: " + wh);
                Console.printout("Saved data from today!", MessageType.INFO);
                lastSaved = currentDate;
            }
        }
    }

}