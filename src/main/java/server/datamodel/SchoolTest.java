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
		query = "select c from SchoolTest c where c.id=:testId"),
@NamedQuery(
		name="getAllTests",
		query = "select c from SchoolTest c")
})
public class SchoolTest {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
	@NotNull
	private String name;
	private String subject;
	private String dateCreated;
	@OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Question> questions;
	@NotNull
	private int testTime;

	public SchoolTest(){
		questions = new ArrayList<>();
	}

	public SchoolTest(String name){
		this.name = name;
		questions = new ArrayList<>();
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getDateCreated() {
		return dateCreated;
	}

	public void setDateCreated(String dateCreated) {
		this.dateCreated = dateCreated;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String type) {
		this.name = type;
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

	public int getTestTime() {
		return testTime;
	}

	public void setTestTime(int testTime) {
		this.testTime = testTime;
	}
}
