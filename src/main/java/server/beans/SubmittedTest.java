package server.beans;

import server.datamodel.Answer;
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

	/**
	 * Adds an SubmittedAnswer on the array position corresponding to the Question array position in SchoolTest.
	 * @param questionNumber int
	 * @param currAnswer SubmittedAnswer
	 */
	public void addAnswer(int questionNumber,AnswerSubmited currAnswer){
		answersSubmited.add(questionNumber, currAnswer);
	}

	/**
	 * Get the submitted answer on the specified position in the array.
	 *
	 * @param questionNumber int
	 * @return
	 */
	public AnswerSubmited getAnswer(int questionNumber){
		return answersSubmited.get(questionNumber);
	}
}

