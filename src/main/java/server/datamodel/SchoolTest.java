package server.datamodel;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

/**
 * The entity class that represents the tests. Contains the type of test and the questions in the test.
 */
@Entity
@NamedQueries({
@NamedQuery(
		name="getTest",
		query = "select c from SchoolTest c where SchoolTest.id=:testId"),
@NamedQuery(
		name="getAllTests",
		query = "select c from SchoolTest c")
})
public class SchoolTest {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
	@NotNull
	private String type;
	@OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Question> questions;
	
	public SchoolTest(){
		questions = new ArrayList<Question>();
	}

	public SchoolTest(String type){
		this.type = type;
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
