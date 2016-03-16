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
import server.beans.TestsToCorrect;
import server.datamodel.NewtonClass;
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
			case "login":
				/**
				 * Login switch. Checks loginId and password against db and if requested sends a list
				 * of all tests available back to client.
				 */
				Login currLogin = gson.fromJson(cmdData.get(0), Login.class);
				if (controller.checkLogin(currLogin.getLoginId(), currLogin.getPassword())) {
					clientId = currLogin.getLoginId();
					setLogin(true);
					send("loginok", "");
					if (currLogin.isGetTests()) {
						send("gettestlist",controller.getAlltestsFromDB(currLogin.getLoginId()));
					}
				} else {
					send("loginfailed","");
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
				//TODO send result data if possible
				break;
			case "getresults":
				//TODO send all test results for given criteria
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
			case "getalltests":
				/**
				 * Returns a list of all tests in database.
				 */
				send("getalltests", controller.getAllTests());
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
				 * Creates a new managed entity of SchoolTest.
				 */
				SchoolTest schoolTest = gson.fromJson(cmdData.get(0), SchoolTest.class);
				controller.putTest(schoolTest);
				break;
			case "updatetest":
				/**
				 * Updates a existent test in database.
				 */
				SchoolTest updSchoolTest = gson.fromJson(cmdData.get(0), SchoolTest.class);
				controller.updateTest(updSchoolTest);
				break;
			case "putstudent":
				/**
				 * Updates if existent or creates a new managed entity of Student.
				 */
				Student student = gson.fromJson(cmdData.get(0), Student.class);
				controller.putStudent(student);
				break;
            case "updatestudent":
                /**
                 * Updates a existent student in database.
                 */
                Student updStudent = gson.fromJson(cmdData.get(0), Student.class);
                controller.updateStudent(updStudent);
                break;
			case "addtesttoclass":
				/**
				 * Adds a test to all student in a class.
				 */
				int classId = gson.fromJson(cmdData.get(0), int.class);
				int testid = gson.fromJson(cmdData.get(1), int.class);
				controller.addTestToClass(classId, testid);
				break;
			case "addtesttostudent":
				long persNumber = gson.fromJson(cmdData.get(0),long.class);
				int testtoadd = gson.fromJson(cmdData.get(1),int.class);
				controller.addTestToStudent(persNumber,testtoadd);
				break;
			case "removetestfromstudent":
				long pNumber = gson.fromJson(cmdData.get(0),long.class);
				int testtoremove = gson.fromJson(cmdData.get(1),int.class);
				controller.deleteTestFromStudent(pNumber,testtoremove);
				break;
			case "putnewtonclass":
				/**
				 * Creates a new managed entity of NewtonClass.
				 */
				NewtonClass newtonClass = gson.fromJson(cmdData.get(0), NewtonClass.class);
				controller.putNewtonClass(newtonClass);
				break;
			case "updatenewtonclass":
				/**
				 * Updates a existing NewtonClass.
				 */
				NewtonClass updNewtonClass = gson.fromJson(cmdData.get(0), NewtonClass.class);
				controller.updateNewtonClass(updNewtonClass);
				break;
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
				break;
			case "getstudentsfromclass":
				/**
				 * Get all students from a class.
				 */
				int classid = gson.fromJson(cmdData.get(0), int.class);
				send("getstudentsfromclass", controller.getStudentsFromClass(classid));
				break;
			case "getteststudents":
				/**
				 * Gets all student personal numbers that has access to a test.
				 */
				int testtosee = gson.fromJson(cmdData.get(0),int.class);
				send("getteststudents",controller.getTestStudents(testtosee));
				break;
			case "getteststocorrect":
				/**
				 * Sends a list of tests that need manual correction.
				 */
				send("getteststocorrect",controller.getTestsToCorrect());
				break;
			case "gettesttocorrect":
				//TODO send information of test to correct
				break;
			case "putimage":
				/**
				 * Stores an image on server.
				 */
				controller.getImage(client.getIP(), gson.fromJson(cmdData.get(0), String.class));
				break;
			case "getimage":
				/**
				 * Retrieves an image from server.
				 */
				controller.storeImage(client.getIP(), gson.fromJson(cmdData.get(0), String.class));
				break;
			case "deletestudent":
				long studId = gson.fromJson(cmdData.get(0), long.class);
				controller.deleteStudent(studId);
				break;
            case "deletestudentsfromclass":
                int studentClassId = gson.fromJson(cmdData.get(0), int.class);
                controller.deleteStudentsFromClass(studentClassId);
                break;
			case "deletetest":
				int testToDelete = gson.fromJson(cmdData.get(0), int.class);
				controller.deleteSchoolTest(testToDelete);
				break;
			case "deleteclass":
				int classToDelete = gson.fromJson(cmdData.get(0), int.class);
                controller.deleteClass(classToDelete);
				break;
			case "disconnect":
				/**
				 * Disconnect client gracefully.
				 */
				client.disconnect();
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
	 * @param command     String
	 * @param commandData Object
	 */
	public <T> void send(String command, T commandData) {
		Message currMessage = new Message(command, commandData);
		String jsonData = gson.toJson(currMessage);
		client.send(jsonData);
	}

	/**
	 * Sends a Message object as a JSON string to client.
	 *
	 * @param currMessage Message
     */
	public void sendMessage(Message currMessage){
		client.send(gson.toJson(currMessage));
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
			System.out.println("Wrong password. Dropped connection.");
			client.disconnect();
		}
	}

}
