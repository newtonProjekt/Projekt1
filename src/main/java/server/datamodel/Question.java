package server.datamodel;



import javax.persistence.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

/**
 * The entity class for questions that contains the answers refering to the tests, question type (multi or text), question
 * text and optionally question image.
 */
@Entity
public class Question {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
	private boolean multiQuestion;
	//private Image questionImage;
	private String questionText;
	@OneToMany
	private List<Answer> answers;
	
	public Question(){
		answers = new ArrayList<Answer>();
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

	/*
	public ImageView getQuestionImage() {
		return questionImage;
	}

	public void setQuestionImage(Image questionImage) {
		this.questionImage = questionImage;
	}
	*/

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
