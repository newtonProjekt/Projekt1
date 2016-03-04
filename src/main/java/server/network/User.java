/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server.network;

import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;


public class User implements Runnable{
    
    private int personalNumber = 0;
    private String password = "";
    private boolean adminFlag = false;
    private boolean loginOk = false;
    private Socket connection;
    private Thread userThread;
    private CommandHandler cmd;
    
    public User(Socket socket){
        
        this.connection = socket;
        userThread = new Thread(this);
        userThread.start();
        
    }
    
    public boolean checkLogin(){
        
        
        
        return loginOk; 
    }
    
    public void setUserInfo(int pNum, String password){
        
        this.personalNumber = pNum;
        this.password = password;
        
    }
    
    public void determineUser(){
        
        
        
    }

    @Override
    public void run() {
        
        while(true){
            try {
                Thread.sleep(100);
                
                InputStream stream  = connection.getInputStream();
                Scanner reader = new Scanner(stream);
                
                String inputstring = reader.nextLine();
                System.out.println(inputstring); 
                
                
                cmd = new CommandHandler(inputstring);
                
            
            } catch (InterruptedException ex) {
                Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);
            }
        
        }
        
    }
    
}
