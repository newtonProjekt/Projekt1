package server.datamodel;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class SchoolTest {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
	private String type;
	@OneToMany	
	private List<Question> questions;
	
	public SchoolTest(){
		questions = new ArrayList<Question>();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public List<Question> getQuestions() {
		return questions;
	}

	public void setQuestions(List<Question> questions) {
		this.questions = questions;
	}

	public void addQuestion(Question currQuestion){
		questions.add(currQuestion);
	}

	public void removeQuestion(Question currQuestion){
		questions.remove(currQuestion);
	}
}
