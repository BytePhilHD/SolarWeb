package de.bytephil.services;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import de.bytephil.enums.MessageType;
import de.bytephil.utils.Console;

public class RestAPI {

    public static String[] data = null;

    public static void main() {
        String uri = "http://192.168.178.161/api/inverter/id/0";
        String[] liveData = new String[10];

        try {
            // make the GET request
            URLConnection request = new URL(uri).openConnection();
            request.connect();
            InputStreamReader inputStreamReader = new InputStreamReader((InputStream) request.getContent());

            // map to GSON objects
            JsonObject root = new JsonParser().parse(inputStreamReader).getAsJsonObject();

           // JsonObject inverter = root.getAsJsonArray("").get(0).getAsJsonObject();

          //  String chData = inverter.get("ch").toString().replace("[[", "");
            String chData = root.get("ch").toString().replace("[[", "");
            liveData = chData.split(",");
            //
            // Stellen und Werte
            // 0 - U_AC
            // 1 - I_AC
            // 2 - P_AC
            // 5 - Temp
            // 6 - YieldTotal
            // 7 - YieldDay
            // 9 - Efficieny

            /*
            System.out.println("Leistung Gesamt: " + liveData[2]);
            System.out.println("Spannung: " + liveData[0]);
            System.out.println("Temperatur: " + liveData[5]);
            System.out.println("KWh Total: " + liveData[6]);
            System.out.println("--------------------------------");
             */
            liveData[0] = liveData[0].replace(".", ",");
            liveData[2] = liveData[2].replace(".", ",");
            liveData[6] = liveData[6].replace(".", ",");                                 // Ändern von . zu , (18.300 KWh => 18,300 KWh)

            // Umformatierung von Wh in KWh
            if (liveData[7].length() < 4) {
                liveData[7] = "0," + liveData[7];        // Wenn es unter einer Kilowattstudne ist, wird ein 0, davor gesetzt
            } else {
                liveData[7] = liveData[7].substring(0, 1) + "," + liveData[7].substring(1);     // Bei 4 Stellen wird ein , an zweiter Stelle gesetzt (1000 Wh -> 1,000 kWH)
            }

            data = liveData;

        } catch (IOException e) {
            Console.printout(e.getMessage(), MessageType.ERROR);
        }

    }
}
