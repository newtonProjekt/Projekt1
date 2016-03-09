package server.datamodel;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

/**
 * The entity class that stores the usersubmited answers.
 * 
 * @author Johan (jolindse@hotmail.com)
 *
 */
@Entity
public class AnswerSubmited {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
	@NotNull
	private int testId;
	@NotNull
	private int questionId;
	private String answerString;
	private boolean correctAnswer;
	
	public AnswerSubmited(){
	}

	public AnswerSubmited(String answerString, int testId, int questionId) {
		this.testId = testId;
		this.questionId = questionId;
		this.answerString = answerString;
		this.correctAnswer = false;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getTestId() {
		return testId;
	}

	public void setTestId(int testId) {
		this.testId = testId;
	}

	public int getQuestionId() {
		return questionId;
	}

	public void setQuestionId(int questionId) {
		this.questionId = questionId;
	}

	public String getAnswerString() {
		return answerString;
	}

	public void setAnswerString(String answerString) {
		this.answerString = answerString;
	}

	public boolean isCorrectAnswer() {
		return correctAnswer;
	}

	public void setCorrectAnswer(boolean correctAnswer) {
		this.correctAnswer = correctAnswer;
	}

}
