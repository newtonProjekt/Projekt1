package server.datamodel;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.ArrayList;
import java.util.List;

/**
 * The entity class that represents the tests. Contains the type of test and the questions in the test.
 *
 * id 			= Auto generated id value.
 * name 		= Name of test.
 * subject		= The subject of the test.
 * dateCreated	= Date of test creation.
 * questions	= List of questions in the test
 * testTime		= Timecap to complete the test.
 * gThreshold	= The percentage threshold for a G
 * vgThreshold	= The percentage threshold for a VG
 *
 */

@Entity
@NamedQueries({
@NamedQuery(
		name="getTest",
		query = "select c from SchoolTest c where c.id=:testId"),
@NamedQuery(
		name="getAllTests",
		query = "select c from SchoolTest c"),
@NamedQuery(
		name = "deleteSchoolTest",
		query = "DELETE FROM SchoolTest c WHERE c.id=:testId")
})

public class SchoolTest {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer id;
	@NotNull
	private String name;
	private String subject;
	@Temporal(TemporalType.DATE)
	private Date dateCreated;
	@OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Question> questions;
	@NotNull
	private int testTime;
	private int gThreshold;
	private int vgThreshold;

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

	public Date getDateCreated() {
		return dateCreated;
	}

	public void setDateCreated(Date dateCreated) {
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

	public int getgThreshold() {
		return gThreshold;
	}

	public void setgThreshold(int gThreshold) {
		this.gThreshold = gThreshold;
	}

	public int getVgThreshold() {
		return vgThreshold;
	}

	public void setVgThreshold(int vgThreshold) {
		this.vgThreshold = vgThreshold;
	}

	public int getTestTime() {
		return testTime;
	}

	public void setTestTime(int testTime) {
		this.testTime = testTime;
	}
}
