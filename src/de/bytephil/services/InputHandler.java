package de.bytephil.services;

import com.pi4j.io.gpio.GpioController;
import com.pi4j.wiringpi.Gpio;
import de.bytephil.main.Main;

import java.util.Date;
import java.util.HashMap;

public class InputHandler {

    HashMap<Date, Double> Volt = new HashMap<>();               // TODO: Save collected Data to file (RAM is getting full this way)
    HashMap<Date, Double> Amper = new HashMap<>();

    public static void handleInput() {
        GpioController gpioController = Main.gpio;

        double InputVolt = Gpio.analogRead(0);              // Port 0 ist Input für Spannung des Systems (0-5 V => 0-40V)
        double InputAmper = Gpio.analogRead(2);                // Port 2 ist Input für Stromstärke des Systems (0-5 V => 0-10 A)

        //TODO: Input zu richtiger Volt und Amper Zahl skalieren

        double Leistung = InputAmper * InputVolt;


    }
}
