package server.datamodel;

import javax.persistence.*;
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
	@OneToOne(cascade = CascadeType.ALL)
	private SchoolTest test;
	@OneToOne(cascade = CascadeType.ALL)
	private Question question;
	private String answerString;
	private boolean correctAnswer;
	
	public AnswerSubmited(){
	}

	public AnswerSubmited(String answerString, Question question) {
		this.question = question;
		this.answerString = answerString;
		this.correctAnswer = false;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public SchoolTest getTestId() {
		return test;
	}

	public void setTestId(SchoolTest testId) {
		this.test = test;
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

	public Question getQuestion() {
		return question;
	}

	public void setQuestion(Question question) {
		this.question = question;
	}
}
