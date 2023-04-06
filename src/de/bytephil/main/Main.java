package de.bytephil.main;

import de.bytephil.enums.MessageType;
import de.bytephil.services.FileReader;
import de.bytephil.services.LogService;
import de.bytephil.services.RestAPI;
import de.bytephil.services.Schedular;
import de.bytephil.utils.Console;
import io.javalin.Javalin;
import io.javalin.http.staticfiles.Location;
import org.apache.commons.logging.Log;

import java.lang.reflect.Field;
import java.util.Timer;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class Main {



    public static void main(String[] args) {

        try {
            LogService.logFileCreation();
        } catch(Exception e1) {}

        new Main().StartApp();
        Schedular.startSchedular();

    }

    public void StartApp() {
        Console.printout("Starting Application... ", MessageType.INFO);

        Javalin app = Javalin.create(config -> {
            config.addStaticFiles("public", Location.CLASSPATH);
            config.showJavalinBanner = false;
        }).start();

        app.ws("/main", ws -> {
            ws.onConnect(ctx -> {
                Console.printout("User connected to main websocket. (IP: " + ctx.session.getRemoteAddress().getAddress().toString().replace("/", "") + ")", MessageType.INFO);
                ctx.send("P_AC: " + RestAPI.data[2]);
                ctx.send("Yield_Total: " + RestAPI.data[6]);
                ctx.send("Yield_Day: " + RestAPI.data[7]);

            });
            ws.onClose(ctx -> {
                Console.printout("User disconnected from main websocket. (IP: " + ctx.session.getRemoteAddress().getAddress().toString().replace("/", "") + ")", MessageType.INFO);
            });
            ws.onMessage(ctx -> {
               if (ctx.message().equals("refresh")) {
                   ctx.send("P_AC: " + RestAPI.data[2]);
                   ctx.send("Yield_Total: " + RestAPI.data[6]);
                   ctx.send("Yield_Day: " + RestAPI.data[7]);

               }
            });
        });
    }

}
