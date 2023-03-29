package de.bytephil.services;

import de.bytephil.enums.MessageType;

import de.bytephil.utils.Console;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Schedular {

    public static Date lastSaved = null;

    public static void startSchedular() {
        RestAPI.main();
        try {
            Thread.sleep(5000);

            saveDayData();
            FileReader.readFile();
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