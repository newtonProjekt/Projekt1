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
    private Thread thread ;
    private Socket connection;
    
    
    /**
     * Constructor that calls the startServerLoop.
     *
     * 
     */
    public NetworkListener(){
        
        startServerLoop();
        
    }
    
    
    /**
     * Starts the server loop.
     * An instance of the serversocket is created at the given port.
     * creates an instance of a thread and starts threading this class.
     */
    public void startServerLoop(){
        
        
        try {
            
            System.out.println("Starting server...");
            server = new ServerSocket(portNumber);
            
            thread = new Thread(this);
            thread.start();
            System.out.println("Server started and listening on port " + portNumber);
            
        } catch (IOException ex) {
            Logger.getLogger(NetworkListener.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        
        
    }
    
    /**
     * Main method.
     *
     * 
     */
    public static void main(String[] args) {
        
        NetworkListener n = new NetworkListener();
        
    }
    
    /**
     * This is what is threaded. An endless loop that waits for incoming connections, every 0.1 seconds.
     * The server accepts incoming sockets. A new client is created for every client that are threaded.
     * Starting the client thread.
     */
    @Override
    public void run() {
        
        while(true){
            try {
                Thread.sleep(100);
            
                connection = new Socket();
                connection = server.accept();
                System.out.println(connection.getInetAddress());
                Thread clientThread = new Thread(new Client(connection));
                clientThread.start();
                
            
            
        } catch (InterruptedException ex) {
            Logger.getLogger(NetworkListener.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(NetworkListener.class.getName()).log(Level.SEVERE, null, ex);
        } 
        
        }
    }
    

}
