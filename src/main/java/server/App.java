package server;

import server.logic.ServerController;
import server.network.NetworkListener;

import java.util.ConcurrentModificationException;

/**
 *
 * Entry point to application
 *
 * Starts controller and networklistening loop.
 * Gives controller a static reference to itself for further calls to a controller reference.
 *
 * Created by Johan on 2016-03-04.
 */
public class App {
    public static void main(String[] args) {
        // Starts controller
        ServerController controller = new ServerController();

        // Sends reference of the controller to the controller for further reference
        ServerController.setReference(controller);

        // Starts network listening in separate thread
        Thread networkListener = new Thread(new NetworkListener());
        networkListener.start();
    }
}
