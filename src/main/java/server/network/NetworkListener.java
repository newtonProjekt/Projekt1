package server.network;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * NetworkListener class
 *
 * Starts the server that is threaded and waits for incoming connections.
 * Accepts the connections and creates an instance of the client class for every client and threads the clients.
 *
 */

public class NetworkListener implements Runnable {
    
    private int portNumber = 3004;
    private ServerSocket server;
    private Socket connection;
    private boolean serverSocketOk = false;


    public NetworkListener() {
        try {
            server = new ServerSocket(portNumber);
            serverSocketOk = true;
        } catch (IOException e) {
            Logger.getLogger(NetworkListener.class.getName()).log(Level.SEVERE, null, e);
        }

    }

    /**
     * This is what is threaded. An endless loop that waits for incoming connections, every 0.1 seconds.
     * The server accepts incoming sockets. A new client is created for every client that are threaded.
     * Starting the client thread.
     */

    @Override
    public void run() {
        System.out.println("Starting server..."); // TO BE LOGGED

        while(serverSocketOk){
            try {
                Thread.sleep(100);
                connection = new Socket();

                connection = server.accept();
                System.out.println(connection.getInetAddress()); // TO BE LOGGED
                Thread clientThread = new Thread(new Client(connection));
                clientThread.start();

        } catch (InterruptedException ex) {
            Logger.getLogger(NetworkListener.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(NetworkListener.class.getName()).log(Level.SEVERE, null, ex);
        } 
        
        }
    }
    
    public void stopServerConnection(){
        serverSocketOk = false;
        try {
            server.close();
        } catch (IOException e) {
            Logger.getLogger(NetworkListener.class.getName()).log(Level.SEVERE, null, e);
        }
    }

}
