/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server.network;

import server.beans.Login;

/**
 *
 * @author Reza
 */
public class Message {
    
    private String command = "";
    
    private Login login = new Login();
    
    public void setCommand(String command){
        this.command = command;
    }
    
    public String getCommand(){
        return command;
    }
    
    public void setLogin(Login login){
        this.login = login;
    }
    
    public Login getLogin(){
        return login;
    }
    
}
