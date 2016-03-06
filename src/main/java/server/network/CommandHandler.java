/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server.network;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
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
        parser = new JsonParser();
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
                
                System.out.println("This is login");
                
                System.out.println(commandDataArray);
                JsonElement obj3 = commandDataArray.get(0);
                JsonObject obj4 = obj3.getAsJsonObject();
                String id = obj4.get("loginId").getAsString();
                System.out.println("The loginId is : " + id);
                
                String pw = obj4.get("password").getAsString();
                System.out.println("The password is : " + pw);
                
                
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
