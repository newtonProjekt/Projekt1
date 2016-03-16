package server.beans;

/**
 * Bean to store info on tests that needs manual correction.
 *
 * Created by Johan Lindström (jolindse@hotmail.com) on 2016-03-16.
 */
public class TestsToCorrect {

    private int testId;
    private long testUserNumber;
    private String testName;
    private String testUser;

    public TestsToCorrect(){
    }

    public TestsToCorrect(int testId, long testUserNumber, String testName, String testUser) {
        this.testId = testId;
        this.testUserNumber = testUserNumber;
        this.testName = testName;
        this.testUser = testUser;
    }

    public int getTestId() {
        return testId;
    }

    public void setTestId(int testId) {
        this.testId = testId;
    }

    public long getTestUserNumber() {
        return testUserNumber;
    }

    public void setTestUserNumber(long testUserNumber) {
        this.testUserNumber = testUserNumber;
    }

    public String getTestName() {
        return testName;
    }

    public void setTestName(String testName) {
        this.testName = testName;
    }

    public String getTestUser() {
        return testUser;
    }

    public void setTestUser(String testUser) {
        this.testUser = testUser;
    }
}
