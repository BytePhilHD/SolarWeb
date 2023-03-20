package de.bytephil.services;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.net.http.HttpClient;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.web.client.RestTemplate;

public class RestAPITEST {

    public static List<String> main() {
        String uri = "http://192.168.178.161/api/record/live";
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
                    .get("inverter").getAsJsonArray();

            // flatten nested arrays
            JsonArray links = new JsonArray();
            items.forEach(item -> links.addAll((JsonArray) item));

            // filter links with "href" properties

            links.forEach(link -> {
                JsonObject linkObject = link.getAsJsonObject();
                String relString = linkObject.get("rel").getAsString();
                if ("preview".equals(relString)) {
                    hrefs.add(linkObject.get("href").getAsString());
                }
            });

        } catch (IOException e) {
            e.printStackTrace();
        }

        return hrefs;
    }
}
