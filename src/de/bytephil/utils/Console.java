package de.bytephil.utils;

import de.bytephil.enums.MessageType;
import de.bytephil.main.Main;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Console {

    public static void printout(String message, MessageType type) {

        System.out.println("[" + getTime() + "] " + type + " - " + message);
    }

    private static String getTime() {
        return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
    }
}
