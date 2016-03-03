package server.network;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

public class NetworkListener implements Runnable {
    
    private int portNumber = 3004;
    private final int maxNumberOfClients = 100;
    public static int loopIterator = 0;
    public static int serverInstanceIndex = 0;
    private NetworkListener[] client = new NetworkListener[maxNumberOfClients];
    private ServerSocket server;
    private Thread thread ;
    private Socket connection;
    private String loginId = "";
    private String loginPassword = "";
    private boolean[] loginOk = new boolean[maxNumberOfClients];
    private NetworkListener[] serverArray = new NetworkListener[maxNumberOfClients];
    
    public NetworkListener(){
        
        if(serverInstanceIndex == 0){
            startServerLoop();
        
            for(int i = 0 ; i<maxNumberOfClients ; i++){
                loginOk[i] = false;
            }
        }
        
    }
    
    public NetworkListener(Socket so){
        
        this.connection = so;
        
    }
    
    public void startServerLoop(){
        
        
        
        try {
            System.out.println("Startar server...");
            
            server = new ServerSocket(portNumber);
            
            while(loopIterator < maxNumberOfClients){
                
                connection = new Socket();
                connection = server.accept();
                serverInstanceIndex++;
                
                System.out.println(connection.getInetAddress());
                
                serverArray[loopIterator] = new NetworkListener(connection);
                thread = new Thread(serverArray[loopIterator]);
                thread.start();
                
                loopIterator++;
            }
            
            
            
        } catch (IOException ex) {
            Logger.getLogger(NetworkListener.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        
    }
    
    
    public static void main(String[] args) {
        
        NetworkListener n = new NetworkListener();
        
    }

    @Override
    public void run() {
        
        try {
            Thread.sleep(100);
            
            
            
        } catch (InterruptedException ex) {
            Logger.getLogger(NetworkListener.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
    }
    

}
