package server.datamodel;



import javax.persistence.*;
import javax.validation.constraints.NotNull;
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
	@NotNull
	private boolean multiQuestion;
	@NotNull
	private int points;
	//private Image questionImage;
	@NotNull
	private String questionText;
	@OneToMany(cascade = CascadeType.ALL,orphanRemoval = true)
	private List<Answer> answers;
	
	public Question(){
		answers = new ArrayList<Answer>();
	}

	public Question(String questionText, int points){
		this.points = points;
		this.questionText = questionText;
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

	public int getPoints() {
		return points;
	}

	public void setPoints(int points) {
		this.points = points;
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

	public void addAnswer(Answer currAnswer){
		answers.add(currAnswer);
	}

	public void removeAnswer(Answer currAnswer){
		answers.remove(currAnswer);
	}
	
}
