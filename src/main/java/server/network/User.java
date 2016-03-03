/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server.network;

import java.net.Socket;


public class User {
    
    private int personalNumber = 0;
    private String password = "";
    private boolean adminFlag = false;
    private boolean loginOk = false;
    private Socket connection;
    
    public User(Socket socket){
        
        this.connection = socket;
        
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
    
}
