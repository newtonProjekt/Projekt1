package server;

import java.io.IOException;
import server.logic.ServerController;
import server.network.NetworkListener;

import java.util.ConcurrentModificationException;

/**
 *
 * Entry point to application
 *
 * Starts controller and network listening loop.
 * Gives controller a static reference to itself for further calls to a controller reference.
 *
 * Created by Johan on 2016-03-04.
 */
public class App {

    public static NetworkListener networkListener;

    public static void main(String[] args) {
        // Starts controller
        ServerController controller = new ServerController();

        // Sends reference of the controller to the controller for further reference
        ServerController.setReference(controller);

        // Starts network listening in separate thread
        networkListener = new NetworkListener();

        Thread networkThread = new Thread(networkListener);
        networkThread.start();
    }

    public static void disconnect(){
        networkListener.stopServerConnection();
    }
}
