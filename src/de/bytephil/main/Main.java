package de.bytephil.main;

import com.pi4j.io.gpio.GpioController;
import de.bytephil.enums.MessageType;
import de.bytephil.services.Schedular;
import de.bytephil.utils.Console;
import io.javalin.Javalin;
import io.javalin.http.staticfiles.Location;

public class Main {

    public static GpioController gpio;

    public static void main(String[] args) {
        //gpio = GpioFactory.getInstance();
        //Gpio.pinMode(0, Gpio.INPUT);                           // https://pi4j.com/1.2/pins/model-3b-plus-rev1.html

        //Gpio.analogRead(0);

        new Schedular().mainScheduler();
        new Main().StartApp();
    }

    public void StartApp() {
        Console.printout("Starting Application... ", MessageType.INFO);

        Javalin app = Javalin.create(config -> {
            config.addStaticFiles("public", Location.CLASSPATH);
            config.showJavalinBanner = false;
        }).start();
    }

}
