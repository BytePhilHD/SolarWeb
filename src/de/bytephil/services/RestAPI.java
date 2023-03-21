package de.bytephil.services;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class RestAPI {

    public static String[] data = null;

    public static void main() {
        String uri = "http://192.168.178.161/api/live";
        String[] liveData = new String[10];

        try {
            // make the GET request
            URLConnection request = new URL(uri).openConnection();
            request.connect();
            InputStreamReader inputStreamReader = new InputStreamReader((InputStream) request.getContent());

            // map to GSON objects
            JsonObject root = new JsonParser().parse(inputStreamReader).getAsJsonObject();

            JsonObject inverter = root.getAsJsonArray("inverter").get(0).getAsJsonObject();

            String chData = inverter.get("ch").toString().replace("[[", "");
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

            System.out.println("Leistung Gesamt: " + liveData[2]);
            System.out.println("Spannung: " + liveData[0]);
            System.out.println("Temperatur: " + liveData[5]);
            System.out.println("KWh Total: " + liveData[6]);
            System.out.println("--------------------------------");

            data = liveData;

        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
/*

    public static List<String> main() {
        String uri = "http://192.168.178.161/api/live";
        List<String> hrefs = new ArrayList<>();

        try {
            // make the GET request
            URLConnection request = new URL(uri).openConnection();
            request.connect();
            InputStreamReader inputStreamReader = new InputStreamReader((InputStream) request.getContent());

            // map to GSON objects
            JsonElement root = new JsonParser().parse(inputStreamReader);

            System.out.println(root.toString());
            // traverse the JSON data
            JsonArray items = root
                    .getAsJsonObject()
                    .get("collection").getAsJsonObject()
                    .get("items").getAsJsonArray();

            // flatten nested arrays
            JsonArray links = new JsonArray();
            items.forEach(item -> links.addAll(root
                    .getAsJsonObject()
                    .get("inverter")
                    .getAsJsonArray()));

            // filter links with "href" properties
            links.forEach(link -> {
                JsonObject linkObject = link.getAsJsonObject();
                String relString = linkObject.get("ch").getAsString();

            });

        } catch (IOException e) {
            e.printStackTrace();
        }

        return hrefs;
    }
 */