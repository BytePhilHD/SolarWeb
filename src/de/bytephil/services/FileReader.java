package de.bytephil.services;

import java.io.*;

public class FileReader {

    public void readFile() throws IOException {
        String fileName = "logs/log.txt";
        File file = new File(fileName);
        FileInputStream fis = new FileInputStream(file);
        InputStreamReader isr = new InputStreamReader(fis);
        BufferedReader br = new BufferedReader(isr);

        String line;
        while((line = br.readLine()) != null){
            //process the line
            System.out.println(line);
        }
        br.close();
    }
}
