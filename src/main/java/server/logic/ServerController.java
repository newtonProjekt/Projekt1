package server.logic;


import server.datamodel.NewtonClass;
import server.datamodel.SchoolTest;
import server.datamodel.Student;

import java.util.List;

/**
 * Server controller class.
 * <p>
 * All calls from clients are routed through this class that handles all connections to db through the entities and JPA.
 * <p>
 * Created by Johan on 2016-03-04.
 */


public class ServerController {

	private static ServerController controller = new ServerController();
	private DatabaseConnection dbc;

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

	// METHODS CALLED FROM COMMANDHANDLER

	/**
	 * Checks that supplied credentials from login is accurate and returns result.
	 *
	 * @param login    String
	 * @param password String
	 * @return boolean
	 */
	public boolean checkLogin(String login, String password) {
		Student studentDB = dbc.getStudent(login);
		return studentDB != null && studentDB.checkLogin(password);
	}


	/**
	 * Gets all the availabe tests for the client
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
	 * gets all the students registered in the database and sends the list
	 */
	public List<Student> getAllStudentsFromDB() {
		List<Student> listOfStudents = dbc.getStudents();
		return listOfStudents;

	}

	public List<NewtonClass> getAllClasses() {
		return dbc.getAllClasses();
	}

}
