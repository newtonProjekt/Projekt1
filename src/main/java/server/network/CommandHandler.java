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
import server.beans.SubmittedTest;
import server.datamodel.SchoolTest;
import server.datamodel.Student;
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
					//Returns a map of testname and id that client has access to.
                                        Map<String, String> listMap1 = new HashMap<>();
                                        for (SchoolTest currTest1 : controller.getAlltestsFromDB(clientId)) {
                                            listMap1.put(currTest1.getName(), Integer.toString(currTest1.getId()));
                                        }
                                        send("gettestlist", listMap1);
					
				}
				break;
			case "starttest":
				/**
				 * Starts test by removing it from the current Students available tests.
				 */
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
				/**
				 * Gets a specific test.
				 */
				send("gettest", controller.getTest(cmdData.get(0)));
				break;
			case "gettestlist":
				/**
				 * Returns a map of testname and id that client has access to.
				 */
				Map<String, String> listMap = new HashMap<>();
				for (SchoolTest currTest : controller.getAlltestsFromDB(clientId)) {
					listMap.put(currTest.getName(), Integer.toString(currTest.getId()));
				}
				send("gettestlist", listMap);
				break;
			case "gettests":
				/**
				 * Returns all tests available to client as a list of SchoolTest.
				 */
				send("gettests", controller.getAlltestsFromDB(clientId));
				break;
			case "submit":
				/**
				 * Persists a submitted test from client.
				 */
				SubmittedTest subMittedTest = gson.fromJson(cmdData.get(0), SubmittedTest.class);
				controller.submitTestToDB(subMittedTest, clientId);
				break;
			case "puttest":
				/**
				 * Updates if existent or creates a new managed entity of SchoolTest.
				 */
				SchoolTest schoolTest = gson.fromJson(cmdData.get(0), SchoolTest.class);
				controller.putTest(schoolTest);
				break;
			case "putstudent":
				/**
				 * Updates if existent or creates a new managed entity of Student.
				 */
				Student student = gson.fromJson(cmdData.get(0), Student.class);
				controller.putStudent(student);
			case "getallstudents":
				/**
				 * Returns all students in database.
				 */
				send("getallstudents", controller.getAllStudentsFromDB());
				break;
			case "getallstudentclasses":
				/**
				 * Returns all NewtonClasses in database
				 */
				send("getallstudentclasses", controller.getAllClasses());
			case "putimage":
				/**
				 * Stores an image on server.
				 */
				controller.getImage(client.getIP(),gson.fromJson(cmdData.get(0),String.class));
				break;
			case "getimage":
				/**
				 * Retrieves an image from server.
				 */
				controller.storeImage(client.getIP(),gson.fromJson(cmdData.get(0),String.class));
			default:
				// Do nothing
				break;
		}
	}

	/**
	 * Method that takes the message-bean returned from the controller, converts it to JSON
	 * and sends it to the client.
	 *
	 * @param command String
	 * @param commandData Object
	 */
	public <T> void send(String command, T commandData) {
		Message currMessage = new Message(command, commandData);
		String jsonData = gson.toJson(currMessage);
		client.send(jsonData);
	}

	/**
	 * Sets the logged in status to ok if verification is successful. Otherwise drops connection.
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
