package server.datamodel;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

/**
 * Entity class for the students.
 *
 * Contains login (personal number), name, password and the answers student has submitted to questions.
 *
 * persNumber       = The personnumber of the student is the unique id.
 * firstName        = The first name of the student.
 * surName          = The surname of the student.
 * password         = The students password.
 * answersSubmited  = List of submitted answers to tests.
 * testsToTake      = List of SchoolTest to take.
 * newtonClassId    = The id of the class that the user belongs to.
 *
 */

@Entity
@NamedQueries({
        @NamedQuery(
                name = "getStudent",
                query = "select c FROM Student c WHERE c.persNumber = :pNumber"),
        @NamedQuery(
                name = "getAllStudents",
                query = "SELECT c FROM Student c"),
        @NamedQuery(
                name = "getStudentTests",
                query = "select c.testsToTake from Student c where c.persNumber = :pNumber"),
        @NamedQuery(
                name = "getStudentsFromClass",
                query = "select c FROM Student c where c.newtonClassId=:classId"
        ),
        @NamedQuery(
                name = "deleteStudent",
                query = "DELETE FROM Student c WHERE c.persNumber=:pNumber"
        ),
        @NamedQuery(
                name = "deleteStudentsFromClass",
                query = "DELETE FROM Student c WHERE c.newtonClassId=:classId"
        ),
        @NamedQuery(
                name = "getteststudents",
                query = "SELECT c.persNumber FROM Student c JOIN c.testsToTake e WHERE e.id =:testId"
        ),
        @NamedQuery(
                name = "getteststudentsfull",
                query = "SELECT c FROM Student c JOIN c.testsToTake e WHERE e.id =:testId"
        ),
        @NamedQuery(
                name = "getstudentanswerfromtest",
                query = "SELECT e FROM Student c JOIN c.answersSubmited e WHERE e.questionId=:questionId AND c.persNumber=:pNumber"
        )
})


public class Student {

    @Id
    @NotNull
    private long persNumber;
    @NotNull
    private String firstName;
    @NotNull
    private String surName;
    private String password;
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    private List<AnswerSubmited> answersSubmited;
    @OneToMany(cascade = CascadeType.PERSIST)
    private List<SchoolTest> testsToTake;
    private int newtonClassId;

    public Student() {
        answersSubmited = new ArrayList<AnswerSubmited>();
    }

    /**
     * Constructor when no password has been submitted. Sets password to "password".
     *
     * @param persNumber long
     * @param firstName  String
     * @param surName    String
     */
    public Student(long persNumber, String firstName, String surName) {
        answersSubmited = new ArrayList<AnswerSubmited>();
        testsToTake = new ArrayList<>();
        this.persNumber = persNumber;
        this.firstName = firstName;
        this.surName = surName;
        password = "password";
    }

    /**
     * Constructor when all arguments is supplied.
     *
     * @param persNumber long
     * @param firstName  String
     * @param surName    String
     * @param password   String
     */
    public Student(long persNumber, String firstName, String surName, String password) {
        answersSubmited = new ArrayList<AnswerSubmited>();
        testsToTake = new ArrayList<>();
        this.persNumber = persNumber;
        this.firstName = firstName;
        this.surName = surName;
        this.password = password;
    }

    public Student(long persNumber, String firstName, String surName, String password, int newtonClass) {
        answersSubmited = new ArrayList<AnswerSubmited>();
        testsToTake = new ArrayList<>();
        this.newtonClassId = newtonClass;
        this.persNumber = persNumber;
        this.firstName = firstName;
        this.surName = surName;
        this.password = password;
    }

    // Getters and setters

    public long getPersNumber() {
        return persNumber;
    }

    public void setPersNumber(long persNumber) {
        this.persNumber = persNumber;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getSurName() {
        return surName;
    }

    public void setSurName(String surName) {
        this.surName = surName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<AnswerSubmited> getAnswersSubmited() {
        return answersSubmited;
    }

    public void setAnswersSubmited(List<AnswerSubmited> answersSubmited) {
        this.answersSubmited = answersSubmited;
    }

    public List<SchoolTest> getTestsToTake() {
        return testsToTake;
    }

    public void setTestsToTake(List<SchoolTest> testsToTake) {
        this.testsToTake = testsToTake;
    }

    public int getNewtonClassId() {
        return newtonClassId;
    }

    public void setNewtonClassId(int newtonClass) {
        this.newtonClassId = newtonClass;
    }

    // Methods to add and remove tests to take.

    public void addTest(SchoolTest currTest) {
        testsToTake.add(currTest);
    }

    public void removeTest(SchoolTest currTest) {
        testsToTake.remove(currTest);
    }

    // Methods to add and remove answers.

    public void addAnswer(AnswerSubmited currAnswer) {
        answersSubmited.add(currAnswer);
    }

    public void removeAnswer(AnswerSubmited currAnswer) {
        answersSubmited.remove(currAnswer);
    }

    /**
     * Checks submitted login credentials against the ones stored in the entity. Returns boolean with result.
     *
     * @param submittedPassword String
     * @return boolean
     */

    public boolean checkLogin(String submittedPassword) {
        boolean loginOk = false;
        if (password.equals(submittedPassword)) {
            loginOk = true;
        }
        return loginOk;
    }
}
