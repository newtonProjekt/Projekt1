package server.beans;

import server.datamodel.AnswerSubmited;

import java.util.ArrayList;
import java.util.List;

/**
 * Bean to handle the list of client submitted answers.
 *
 * Created by Johan Lindström (jolindse@hotmail.com) on 2016-03-07.
 */
public class SubmittedTest {

	private int testId;
	private List<AnswerSubmited> answersSubmited;

	public SubmittedTest(){
		answersSubmited = new ArrayList<>();
	}

	public SubmittedTest(int testId, List<AnswerSubmited> answersSubmited){
		this.testId = testId;
		this.answersSubmited = answersSubmited;
	}

	public int getTestId() {
		return testId;
	}

	public void setTestId(int testId) {
		this.testId = testId;
	}

	public List<AnswerSubmited> getAnswersSubmited() {
		return answersSubmited;
	}

	public void setAnswersSubmited(List<AnswerSubmited> answersSubmited) {
		this.answersSubmited = answersSubmited;
	}
}

