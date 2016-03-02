package server.datamodel;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import javafx.scene.image.Image;

@Entity
public class Question {

	@Id
	@GeneratedValue
	private int id;
	private boolean multiQuestion;
	private Image questionImage;
	private String questionText;
	private List<Answer> answers;
	
	public Question(){
		
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public boolean isMultiQuestion() {
		return multiQuestion;
	}

	public void setMultiQuestion(boolean multiQuestion) {
		this.multiQuestion = multiQuestion;
	}

	public Image getQuestionImage() {
		return questionImage;
	}

	public void setQuestionImage(Image questionImage) {
		this.questionImage = questionImage;
	}

	public String getQuestionText() {
		return questionText;
	}

	public void setQuestionText(String questionText) {
		this.questionText = questionText;
	}

	public List<Answer> getAnswers() {
		return answers;
	}

	public void setAnswers(List<Answer> answers) {
		this.answers = answers;
	}
	
}
