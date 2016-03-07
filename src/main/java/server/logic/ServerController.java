package server.logic;


import java.util.List;
import server.datamodel.SchoolTest;
import server.datamodel.Student;

/**
 *
 * Server controller class.
 *
 * All calls from clients are routed through this class that handles all connections to db through the entities and JPA.
 *
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
    public static void setReference(ServerController controllerHandler){
        controller = controllerHandler;
    }

    /**
     * Returns the reference to the controller.
     *
     * @return ServerController
     */
    public static ServerController getController(){
        return controller;
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
     * Gets all the availabe tests for the client
     *
     * @param perNumber String
     * @return List
     */
    public List<SchoolTest> getAlltestsFromDB(String perNumber){
        List<SchoolTest> listOfTests = dbc.getStudentTests(perNumber);
        return listOfTests;
        
    }
    
    
    
    
    public void startTest(Long clientId, String testId){
    
        int intTestId = Integer.parseInt(testId);
        Student student = dbc.getStudent(Long.toString(clientId));
	int index = -1;	   
	for (SchoolTest currTest: student.getTestsToTake()){
		   if (currTest.getId() == intTestId){
			   index = student.getTestsToTake().indexOf(currTest);
		   }
        }
        
        if(index > -1){
            SchoolTest toRemove = student.getTestsToTake().get(index);
            student.removeTest(toRemove);
        }
        
    }
    
}
