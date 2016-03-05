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
import java.util.Arrays;


public class CommandHandler {
    
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
        */
        
        
    }
    
    
}
