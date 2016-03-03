package server.datamodel;

import javax.persistence.*;
/**
 * The entity class that stores the usersubmited answers.
 * 
 * @author Johan Lindstr�m (jolindse@hotmail.com)
 *
 */
@Entity
public class AnswerSubmited {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
	@OneToOne
	private int testId;
	private String answerString;
	private boolean correctAnswer;
	
	public AnswerSubmited(){
	}

	public AnswerSubmited(int testId, String answerString, boolean correctAnswer) {
		this.testId = testId;
		this.answerString = answerString;
		this.correctAnswer = correctAnswer;
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
