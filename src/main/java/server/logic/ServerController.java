package server.logic;


import javafx.embed.swing.SwingFXUtils;
import javafx.scene.image.Image;
import server.beans.SubmittedTest;
import server.datamodel.AnswerSubmited;
import server.datamodel.NewtonClass;
import server.datamodel.SchoolTest;
import server.datamodel.Student;

import javax.imageio.ImageIO;
import javax.imageio.stream.ImageOutputStream;
import java.awt.image.BufferedImage;
import java.io.*;
import java.net.Socket;
import java.net.UnknownHostException;
import java.nio.file.Files;
import java.util.List;

/**
 * Server controller class.
 *
 * All calls from clients are routed through this class that handles all connections to db through the entities and JPA.
 *
 * Created by Johan on 2016-03-04.
 */

public class ServerController {

	private static ServerController controller = new ServerController();
	private DatabaseConnection dbc;
	private String IMAGE_PATH = "main/images/";

	public ServerController() {
		dbc = new DatabaseConnection();
	}

	/**
	 * Sets the reference to the controller.
	 *
	 * @param controllerHandler ServerController.
	 */
	public static void setReference(ServerController controllerHandler) {
		controller = controllerHandler;
	}

	/**
	 * Returns the reference to the controller.
	 *
	 * @return ServerController
	 */
	public static ServerController getController() {
		return controller;
	}

	// METHODS FOR IMAGES

	/**
	 * Gets an image from server and send through new socket.
	 *
	 * @param ipAddress String
	 * @param imgName String
     */
	public void getImage(String ipAddress, String imgName) {
		// Init socket.
		Socket client = null;

		// Clean IP-address.
		ipAddress = ipAddress.replace("[/]","");

		try {
			client = new Socket(ipAddress,3400);
			BufferedImage currImage = ImageIO.read(new File(IMAGE_PATH+imgName));

			if(client.isConnected()) {
				ImageIO.write(currImage,"PNG",client.getOutputStream());
				client.getOutputStream().flush();
			}
		} catch (IOException e) {
			System.out.println("CONTROLLER; getImage(); Problem sending image: "+IMAGE_PATH+imgName);
		} finally {
			if (client != null) {
				try {
					client.close();
				} catch (IOException e) {
					System.out.println("CONTROLLER; getImage(); Problem closing socket");
				}
			}
		}
	}

	/**
	 * Receives and saves image on server on new socket.
	 *
	 * @param ipAddress String
	 * @param imgName String
     */
	public void storeImage(String ipAddress, String imgName){
		Socket client = null;
		BufferedImage currImg = null;

		// Clean IP-address.
		ipAddress = ipAddress.replace("[/]","");

		try {
			client = new Socket(ipAddress,3400);
			if(client.isConnected()) {
				currImg = ImageIO.read(client.getInputStream());
			}
		} catch (IOException e) {
			System.out.println("CONTROLLER; storeImage(); Problem receiving image: "+imgName);
		} finally {
			if (client != null){
				try {
					client.close();
				} catch (IOException e) {
					System.out.println("CONTROLLER; storeImage(); Problem closing socket");
				}
			}
		}

		if(currImg != null){
			try {
				ImageIO.write(currImg,"PNG",new File(IMAGE_PATH+imgName));
			} catch (IOException e) {
				System.out.println("CONTROLLER; getImage(); Problem saving file: "+IMAGE_PATH+imgName);
			}
		}
	}

	// METHODS CALLED FROM COMMANDHANDLER

	/**
	 * Checks that supplied credentials from login is accurate and returns result.
	 *
	 * @param login String
	 * @param password String
	 * @return boolean
	 */
	public boolean checkLogin(String login, String password) {
		Student studentDB = dbc.getStudent(login);
		return studentDB != null && studentDB.checkLogin(password);
	}

	/**
	 * Gets all the available tests for the client
	 *
	 * @param perNumber String
	 * @return List SchoolTest
	 */
	public List<SchoolTest> getAlltestsFromDB(String perNumber) {
		return dbc.getStudentTests(perNumber);
	}

	/**
	 * Returns a specific test
	 *
	 * @param testId String
	 * @return SchoolTest
	 */
	public SchoolTest getTest(String testId) {
		return dbc.getTest(testId);
	}

	/**
	 * Sets test as started by removing it from list of available tests from
	 * current client.
	 *
	 * @param clientId String
	 * @param testId   String
	 */
	public void startTest(String clientId, String testId) {
		int intTestId = Integer.parseInt(testId);
		Student student = dbc.getStudent(clientId);
		int index = -1;
		for (SchoolTest currTest : student.getTestsToTake()) {
			if (currTest.getId() == intTestId) {
				index = student.getTestsToTake().indexOf(currTest);
			}
		}
		if (index > -1) {
			SchoolTest toRemove = student.getTestsToTake().get(index);
			student.removeTest(toRemove);
			dbc.updateEntity(student);
		}
	}

	/**
	 * Gets all the students registered in the database and sends the list
	 */
	public List<Student> getAllStudentsFromDB() {
		List<Student> listOfStudents = dbc.getStudents();
		return listOfStudents;

	}

	/**
	 * Returns list of classes in database
	 *
	 * @return List\<NewtonClass\>
	 */
	public List<NewtonClass> getAllClasses() {
		return dbc.getAllClasses();
	}

	/**
	 * Submits test answers from client to database.
	 *
	 * @param subMittedTest
	 * @param clientId
	 */
	public void submitTestToDB(SubmittedTest subMittedTest, String clientId) {
		Student currStudent = dbc.getStudent(clientId);
		for (AnswerSubmited currAnswer: subMittedTest.getAnswersSubmited()) {
			currStudent.addAnswer(currAnswer);
		}
		dbc.updateEntity(currStudent);
	}
        
     /**
	 * Gets a test from client and persists it
	 *
	 * @param schoolTest SchoolTest
	 */
	 public void putTest(SchoolTest schoolTest) {
		 // check for images
		dbc.updateEntity(schoolTest);
	}
        
	/**
	 * Gets a student from client and persists it
	 *
	 * @param student Student
	 */
     public void putStudent(Student student) {
		dbc.updateEntity(student);
	}
        
}
