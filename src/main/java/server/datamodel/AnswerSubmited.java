package server.datamodel;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

/**
 * The entity class that stores the usersubmited answers.
 * 
 * @author Johan (jolindse@hotmail.com)
 *
 * id 				= Auto generated id value.
 * testId			= Reference to the test id.
 * questionId		= Reference to the question id.
 * answerString		= The string containing the selected/given answer.
 * correctAnswer	= Boolean referencing if the supplied answer was deemed correct.
 * corrected		= Boolean referencing if the submitted answer has been corrected.
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
	private boolean corrected;
	
	public AnswerSubmited(){
	}

	public AnswerSubmited(String answerString, int testId, int questionId) {
		this.testId = testId;
		this.questionId = questionId;
		this.answerString = answerString;
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

	public boolean isCorrected() {
		return corrected;
	}

	public void setCorrected(boolean corrected) {
		this.corrected = corrected;
	}
}
