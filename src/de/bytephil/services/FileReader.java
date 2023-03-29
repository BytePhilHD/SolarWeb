package de.bytephil.services;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

public class FileReader {

    public static HashMap<String, Integer> readData = new HashMap<>();          // Date und WH die an dem Tag erreicht wurden


    public static void readFile() throws IOException {
        String fileName = "logs/log.txt";
        File file = new File(fileName);
        FileInputStream fis = new FileInputStream(file);
        InputStreamReader isr = new InputStreamReader(fis);
        BufferedReader br = new BufferedReader(isr);

        readData.clear();
        String line;
        while((line = br.readLine()) != null){
            //process the line
            String date = line.substring(1, 11);
            date = date.substring(8, 10) + "." + date.substring(5, 7) + "." + date.substring(0, 4);
            int wh = Integer.parseInt(line.substring(35, 39));
            readData.put(date, wh);
        }
        br.close();
    }
}
