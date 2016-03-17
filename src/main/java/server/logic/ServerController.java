package server.logic;


import javafx.embed.swing.SwingFXUtils;
import javafx.scene.image.Image;
import server.beans.*;
import server.datamodel.*;
import server.datamodel.CorrectedTest;

import javax.imageio.ImageIO;
import javax.imageio.stream.ImageOutputStream;
import java.awt.image.BufferedImage;
import java.io.*;
import java.net.Socket;
import java.net.UnknownHostException;
import java.nio.file.Files;
import java.util.ArrayList;
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
    private CorrectionHandler corrHandler;
    private String IMAGE_PATH = "main/images/";

    public ServerController() {
        dbc = new DatabaseConnection();
        corrHandler = new CorrectionHandler(this);
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
     * @param imgName   String
     */
    public void getImage(String ipAddress, String imgName) {
        // Init socket.
        Socket client = null;

        // Clean IP-address.
        ipAddress = ipAddress.replace("[/]", "");

        try {
            client = new Socket(ipAddress, 3400);
            BufferedImage currImage = ImageIO.read(new File(IMAGE_PATH + imgName));

            if (client.isConnected()) {
                ImageIO.write(currImage, "PNG", client.getOutputStream());
                client.getOutputStream().flush();
            }
        } catch (IOException e) {
            System.out.println("CONTROLLER; getImage(); Problem sending image: " + IMAGE_PATH + imgName);
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
     * @param imgName   String
     */
    public void storeImage(String ipAddress, String imgName) {
        Socket client = null;
        BufferedImage currImg = null;

        // Clean IP-address.
        ipAddress = ipAddress.replace("[/]", "");

        try {
            client = new Socket(ipAddress, 3400);
            if (client.isConnected()) {
                currImg = ImageIO.read(client.getInputStream());
            }
        } catch (IOException e) {
            System.out.println("CONTROLLER; storeImage(); Problem receiving image: " + imgName);
        } finally {
            if (client != null) {
                try {
                    client.close();
                } catch (IOException e) {
                    System.out.println("CONTROLLER; storeImage(); Problem closing socket");
                }
            }
        }

        if (currImg != null) {
            try {
                ImageIO.write(currImg, "PNG", new File(IMAGE_PATH + imgName));
            } catch (IOException e) {
                System.out.println("CONTROLLER; getImage(); Problem saving file: " + IMAGE_PATH + imgName);
            }
        }
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
     * Gets all tests from database.
     *
     * @return List SchoolTest
     */
    public List<SchoolTest> getAllTests() {
        return dbc.getAllTests();
    }

    /**
     * Gets all the available tests for the client.
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
     * Gets all the students registered in the database and sends the list.
     */
    public List<Student> getAllStudentsFromDB() {
        List<Student> listOfStudents = dbc.getStudents();
        return listOfStudents;

    }

    public void deleteStudent(long persNumber) {
        dbc.deleteStudent(persNumber);
    }

    public void deleteStudentsFromClass(int classId) {
        dbc.deleteStudentsFromClass(classId);
    }

    /**
     * Returns a list of students from a specific class
     *
     * @param classId int
     * @return List\<Student\>
     */
    public List<Student> getStudentsFromClass(int classId) {
        return dbc.getStudentFromClass(classId);
    }

    /**
     * Returns list of classes in database.
     *
     * @return List\<NewtonClass\>
     */
    public List<NewtonClass> getAllClasses() {
        return dbc.getAllClasses();
    }

	/**
	 * Deletes a SchoolTest from database.
	 *
	 * @param testId int
	 */
    public void deleteSchoolTest(int testId) {
        //TODO Check if schooltest is in any students testsToDo and remove it.
        List<Student> affectedStudents = dbc.getStudentsTestList(testId);
        SchoolTest toRemove = null;

        for (Student currStudent : affectedStudents) {
            List<SchoolTest> currTestsToTake = currStudent.getTestsToTake();
            for (SchoolTest currTest : currTestsToTake) {
                if (currTest.getId() == testId) {
                    toRemove = currTest;
                    break;
                }
            }
            currStudent.removeTest(toRemove);
            dbc.updateEntity(currStudent);
        }
        dbc.deleteSchoolTest(testId);
    }

	/**
	 * Remove a test from a student.
	 *
	 * @param persNumber long
	 * @param testId int
	 */
    public void deleteTestFromStudent(long persNumber, int testId) {
        Student currStudent = dbc.getStudent(Long.toString(persNumber));
        List<SchoolTest> testsToTake = currStudent.getTestsToTake();
        SchoolTest toRemove = null;

        for (SchoolTest currTest : testsToTake) {
            if (currTest.getId() == testId) {
                toRemove = currTest;
                break;
            }
        }
        currStudent.removeTest(toRemove);
        dbc.updateEntity(currStudent);
    }

    /**
     * Submits test answers from client to database.
     *
     * @param subMittedTest SubmittedTest
     * @param clientId      String
     */
    public void submitTestToDB(SubmittedTest subMittedTest, String clientId) {
        Student currStudent = dbc.getStudent(clientId);
        for (AnswerSubmited currAnswer : subMittedTest.getAnswersSubmited()) {
            currStudent.addAnswer(currAnswer);
        }
        dbc.updateEntity(currStudent);
        correctTest(clientId, subMittedTest);
    }

    public void updateAnswer(AnswerSubmited currAnswer){
        dbc.updateEntity(currAnswer);
    }

    public void deleteClass(int classId) {
        dbc.deleteClass(classId);
    }

    /**
     * Gets a test from client and persists it.
     *
     * @param schoolTest SchoolTest
     */
    public void putTest(SchoolTest schoolTest) {
        // check for images
        dbc.persistEntity(schoolTest);
    }

    /**
     * Updates a SchoolTest entity.
     *
     * @param schoolTest
     */
    public void updateTest(SchoolTest schoolTest) {
        dbc.updateEntity(schoolTest);
    }

    /**
     * Get list of student persNumber that has access to a test.
     *
     * @param testId int
     * @return List\<Long\>
     */
    public List<Long> getTestStudents(int testId) {
        return dbc.getStudentsTest(testId);
    }

    /**
     * Gets a student from client and persists it.
     *
     * @param student Student
     */
    public void putStudent(Student student) {
        dbc.updateEntity(student);
    }

    /**
     * Updates a Student entity.
     *
     * @param student
     */
    public void updateStudent(Student student) {
        dbc.updateEntity(student);
    }

    /**
     * Gets a class from client and persists it.
     *
     * @param currClass NewtonClass
     */
    public void putNewtonClass(NewtonClass currClass) {
        dbc.persistEntity(currClass);
    }

    /**
     * Updates a NewtonClass entity.
     *
     * @param currClass NewtonClass
     */
    public void updateNewtonClass(NewtonClass currClass) {
        dbc.updateEntity(currClass);
    }

    /**
     * Adds a test to take for all students in a class.
     *
     * @param classId int
     * @param testId  int
     */
    public void addTestToClass(int classId, int testId) {
        List<Student> classStudents = dbc.getStudentFromClass(classId);
        for (Student currStudent : classStudents) {
            currStudent.addTest(dbc.getTest(Integer.toString(testId)));
            dbc.updateEntity(currStudent);
        }
    }

	/**
	 * Adds a test to student.
	 *
	 * @param persNummer long
	 * @param testId int
	 */
    public void addTestToStudent(long persNummer, int testId) {
        Student currStudent = dbc.getStudent(Long.toString(persNummer));
        currStudent.addTest(dbc.getTest(Integer.toString(testId)));
        dbc.updateEntity(currStudent);
    }

    // Question

    /**
     * Gets specified question from database.
     *
     * @param questionId int
     * @return Question
     */
    public Question getQuestion(int questionId) {
        return dbc.getQuestion(questionId);
    }

    // Answers

    public AnswerSubmited getAnswerSubmitted(int questionId, long pNumber){
        return dbc.getAnswerToQuestion(pNumber,questionId);
    }

    // Correction

    /**
     * Takes a submitted test and corrects it.
     *
     * @param persNumber    String
     * @param submittedTest SubmittedTest
     */
    public void correctTest(String persNumber, SubmittedTest submittedTest) {
        Student currStudent = dbc.getStudent(persNumber);
        CorrectedTest currTest = corrHandler.correctTest(currStudent, submittedTest);
        dbc.updateEntity(currStudent);
        dbc.persistEntity(currTest);
    }

    /**
     * Gets all uncorrected tests and sends them as a list of TestsToCorrect
     *
     * @return List\<TestsToCorrect\>
     */
    public List<TestsToCorrect> getTestsToCorrect() {
        List<TestsToCorrect> sendList = new ArrayList<>();
        List<CorrectedTest> incompleteTests = dbc.getTestsToCorrect();
        for (CorrectedTest currIncomplete : incompleteTests) {
            SchoolTest currTest = dbc.getTest(Integer.toString(currIncomplete.getTestId()));
            Student currStudent = dbc.getStudent(Long.toString(currIncomplete.getPersNumber()));
            TestsToCorrect currTestToAdd = new TestsToCorrect(currTest.getId(), currStudent.getPersNumber(), currTest.getName(), currStudent.getFirstName() + " " + currStudent.getSurName());
            System.out.println(currTestToAdd); // TEST
            sendList.add(currTestToAdd);
        }
        return sendList;
    }

	/**
	 * Returns a Message containing both user submitted answer and the SchoolTest they belong to.
	 *
	 * @param persNumber long
	 * @param testId int
	 * @return Message
	 */
    public Message getTestToCorrect(long persNumber, int testId) {
        Message messageToSend = new Message("gettesttocorrect");
		Student currStudent = dbc.getStudent(Long.toString(persNumber));
		SchoolTest currTest = dbc.getTest(Integer.toString(testId));

		SubmittedTest currSub = new SubmittedTest();
		currSub.setTestId(testId);

		for(AnswerSubmited currAnswer: currStudent.getAnswersSubmited()){
			if (currAnswer.getTestId() == testId){
				currSub.addAnswer(currAnswer.getQuestionId(),currAnswer);
			}
		}
		messageToSend.addCommandData(currSub);
		messageToSend.addCommandData(currTest);
        return messageToSend;
    }

	/**
     * Updates a manually corrected test in database.
     *
     * @param currCorrected CorrectedTestBean
     */
    public void submitCorrectedTest(CorrectedTestBean currCorrected) {
        int testId = currCorrected.getCorrected().get(0).getTestId();
        long persNumber = currCorrected.getStudentId();

        CorrectedTest corrected = dbc.getCorrectedTest(persNumber, testId);

        dbc.updateEntity(corrHandler.completeCorrection(corrected, currCorrected));
    }

    /**
     * Gets a corrected test from database.
     *
     * @param persNumber long
     * @param testId int
     * @return CorrectedTest
     */
    public CorrectedTest getCorrectedTest(long persNumber, int testId){
        return dbc.getCorrectedTest(persNumber,testId);
    }

    // Statistics

    public Results getStatisticsSingleTest(long persNumber, int testId){
        return corrHandler.singleTestStats(persNumber,testId);
    }

}
