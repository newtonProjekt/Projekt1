/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server.network;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import server.beans.Message;
import server.logic.ServerController;


/**
 * Class that handles all connections between controller and connected client.
 *
 *
 */
public class CommandHandler {

    private ServerController controller;
    private Client client;
    private long clientId;
    private boolean loggedIn = false;
    private Gson gson;
    private JsonParser parser;

    public CommandHandler(Client client){
        this.client = client;
        controller = ServerController.getController();
        gson = new Gson();
    }

	/**
     * Method that parses the JSON and calls for the appropriate method in controller.
     *
     * @param jsonData String
     */
    public void parse(String jsonData){
        
        JsonObject commandObj = parser.parse(jsonData).getAsJsonObject();
        String command = commandObj.get("command").getAsString();
        JsonArray commandDataArray = commandObj.getAsJsonArray("commandData");

        /* The following switch statements calls for the appropriate method in controller.
           and always includes a reference to this instance of CommandHandler for possible
           return data.
         */
        switch(command){
            case "login":
                // do login routine
                break;
            case "starttest":
                // set test as started controller
                break;
            case "getresult":
                // send result data if possible
                break;
            case "getresults":
                // send all test results for given criteria
                break;
            case "gettest":
                // send test
                break;
            case "getalltests":
                // send a list of all tests
                break;
            case "gettestlist":
                // send list of available tests
                break;
            case "submit":
                // do submit routine
                break;
            case "maketest":
                // make new test
                break;
            case "getallusers":
                // send list of all users
                break;
            default:
                // Do nothing
                break;
        }
    }

	/**
     * Method that takes the message-bean returned from the controller, converts it to JSON
     * and sends it to the client.
     *
     * @param currMessage Message
     */
    public void send(Message currMessage){
        String jsonData = gson.toJson(currMessage);
        client.send(jsonData);
    }

	/**
	 * Sets the logged in status to ok if verification is succesfull. Otherwise drops connection.
     *
     * @param logginOk boolean
     */
    public void setLogin(boolean logginOk){
        if (logginOk){
            loggedIn = true;
        } else {
            client.disconnect();
        }
    }

}
    /*
    Gson gson = new Gson();
    
    public CommandHandler(String fromuser){
        
        //System.out.println(gson.toJson(fromuser));
        
        //System.out.println(gson.toJson("roles"));
        
        JsonParser parser = new JsonParser();
        
        JsonObject obj = parser.parse(fromuser).getAsJsonObject();
        String cmd = obj.get("command").getAsString();
        System.out.println("The command is: " + cmd);
        
        JsonObject obj2 = obj.getAsJsonObject("login");
        String id = obj2.get("loginId").getAsString();
        System.out.println("The loginId is: " + id);
        
        String pw = obj2.get("password").getAsString();
        System.out.println("The passwrod is: " + pw);
        
        
        /*
        String id = obj.get("loginId").getAsString();
        String pw = obj.get("password").getAsString();
        
        System.out.println("The loginId is: " + id);
        System.out.println("The password is: " + pw);
        */
        
        /*
        typeofjson:
        login
        starttest
        seeresults
        maketest
        edittest
        seeresults
        */
        
        /*
        String r = obj.get("firstName").getAsString();
        System.out.println(r);
        
        JsonArray obj2 = obj.get("roles").getAsJsonArray();
        for(JsonElement t : obj2){
            System.out.println(t);
        }
        
        
        JsonObject obj3 = obj.getAsJsonObject("department");
        String s = obj3.get("deptName").getAsString();
        System.out.println(s);

        
        
    }
    
    
}
*/