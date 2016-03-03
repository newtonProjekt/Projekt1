package server.network;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

public class NetworkListener implements Runnable{
    
    private int portNumber = 3004;
    private ServerSocket server;
    private Thread thread ;
    private Socket connection;
    private User user;
    
    
    public NetworkListener(){
        
        startServerLoop();
        
    }
    
    
    
    public void startServerLoop(){
        
        
        try {
            
            System.out.println("Startar server...");
            server = new ServerSocket(portNumber);
            
            thread = new Thread(this);
            thread.start();
            
            
        } catch (IOException ex) {
            Logger.getLogger(NetworkListener.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        
        
    }
    
    
    public static void main(String[] args) {
        
        NetworkListener n = new NetworkListener();
        
    }

    @Override
    public void run() {
        
        while(true){
            try {
                Thread.sleep(100);
            
                connection = new Socket();
                connection = server.accept();
                System.out.println(connection.getInetAddress());
                user = new User(connection);
                
            
            
        } catch (InterruptedException ex) {
            Logger.getLogger(NetworkListener.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(NetworkListener.class.getName()).log(Level.SEVERE, null, ex);
        } 
        
        }
    }
    

}
