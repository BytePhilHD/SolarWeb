package de.bytephil.main;

import de.bytephil.enums.MessageType;
import de.bytephil.services.RestAPI;
import de.bytephil.utils.Console;
import io.javalin.Javalin;
import io.javalin.http.staticfiles.Location;

public class Main {


    public static void main(String[] args) {

        RestAPI.main();
      //  new Main().StartApp();

    }

    public void StartApp() {
        Console.printout("Starting Application... ", MessageType.INFO);

        Javalin app = Javalin.create(config -> {
            config.addStaticFiles("public", Location.CLASSPATH);
            config.showJavalinBanner = false;
        }).start();
    }

}
