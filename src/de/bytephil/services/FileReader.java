package de.bytephil.services;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

public class FileReader {

    public static HashMap<String, Integer> readData = new HashMap<>();          // Date und WH die an dem Tag erreicht wurden


    public static void readFile() throws IOException {
        HashMap<String, Integer> unhandledData = new HashMap<>();
        String fileName = "logs/log.txt";
        File file = new File(fileName);
        FileInputStream fis = new FileInputStream(file);
        InputStreamReader isr = new InputStreamReader(fis);
        BufferedReader br = new BufferedReader(isr);


        String line;
        while((line = br.readLine()) != null){
            //process the line
            String date = line.substring(1, 11);
            date = date.substring(8, 10) + "." + date.substring(5, 7) + "." + date.substring(0, 4);
            int wh = Integer.parseInt(line.substring(35, 39));
            unhandledData.put(date, wh);
        }
        br.close();

        readData.clear();

        if (unhandledData.size() >= 7) {
            List<String> keys = new ArrayList<>(unhandledData.keySet());
            int size = keys.size();
            int count = 0;
            for (int i = size - 1; i >= 0 && count < 7; i--) {
                String key = keys.get(i);
                int value = unhandledData.get(key);
                readData.put(key, value);
                System.out.println(key + ", " + value);
                count++;
            }
        } else {
           // TODO Wenn weniger als 7 Einträge drin sind
            System.out.println("ERROR");
        }

    }
}
