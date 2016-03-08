/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server.network;

import com.google.gson.Gson;
import com.google.gson.JsonParser;
import server.beans.Login;
import server.beans.Message;
import server.datamodel.SchoolTest;
import server.logic.ServerController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * Class that handles all connections between controller and connected client.
 */
public class CommandHandler {

	private ServerController controller;
	private Client client;
	private String clientId;
	private boolean loggedIn = false;
	private Gson gson;
	private JsonParser parser;

	public CommandHandler(Client client) {
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
	public void parse(String jsonData) {

		Message currMessage = gson.fromJson(jsonData, Message.class);
		List<String> cmdData = currMessage.getCommandData();

        /* The following switch statements calls for the appropriate method in controller.
		   and always includes a reference to this instance of CommandHandler for possible
           return data.
         */
		switch (currMessage.getCommand()) {
			/**
			 * Login switch. Checks loginId and password against db and if requested sends a list
			 * of all tests available back to client.
			 */
			case "login":
				Login currLogin = gson.fromJson(cmdData.get(0), Login.class);
				if (controller.checkLogin(currLogin.getLoginId(), currLogin.getPassword())) {
					clientId = currLogin.getLoginId();
					setLogin(true);
					if (currLogin.isGetTests()) {
						List listOfTests = controller.getAlltestsFromDB(clientId);
						Message sendMessage = new Message("availableTests");
						sendMessage.addCommandData(listOfTests);
						send(sendMessage);
					}
				}
				break;
			case "starttest":
				// Set test as started in controller.
				String testId = cmdData.get(0);
				controller.startTest(clientId, testId);
				break;
			case "getresult":
				// send result data if possible
				break;
			case "getresults":
				// send all test results for given criteria
				break;
			case "gettest":
				break;
			case "gettestlist":
				/**
				 * Returns a map of testname and id that client has access to.
				 */
				Map<String,String> listMap = new HashMap<>();
				for(SchoolTest currTest: controller.getAlltestsFromDB(clientId)){
					listMap.put(currTest.getName(),Integer.toString(currTest.getId()));
				}
				send(new Message("testlist",listMap));
				break;
			case "gettests":
				/**
				 * Returns all tests available to client as a list of SchoolTest
				 */
				Message getTestMessage = new Message("availableTests");
				getTestMessage.addCommandData(controller.getAlltestsFromDB(clientId));
				send(getTestMessage);
				break;
			case "getalltests":
				// send a list of all tests
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
	public void send(Message currMessage) {
		String jsonData = gson.toJson(currMessage);
		System.out.println(jsonData); // TEST
		client.send(jsonData);
	}

	/**
	 * Sets the logged in status to ok if verification is succesfull. Otherwise drops connection.
	 *
	 * @param logginOk boolean
	 */
	private void setLogin(boolean logginOk) {
		if (logginOk) {
			loggedIn = true;
		} else {
			client.disconnect();
		}
	}

}
