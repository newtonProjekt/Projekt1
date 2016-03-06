package server.datamodel;

import org.eclipse.persistence.jpa.config.Cascade;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

/**
 * Entity class for the students.
 *
 * Contains login (personal number), name, password and the answers student has submitted to questions.
 */
@Entity
@NamedQueries({
@NamedQuery(name = "getUser",
		query ="SELECT c FROM Student WHERE c.persNumber = :persNumber"),
@NamedQuery(name = "getAllUsers",
		query = "SELECT c FROM Student")
})
public class Student {

	@Id
	@NotNull
	private long persNumber;
	@NotNull
	private String name;
	private String password;
	@OneToMany (cascade = CascadeType.ALL,orphanRemoval = true)
	private List<AnswerSubmited> answersSubmited;
	
	public Student(){
		answersSubmited = new ArrayList<AnswerSubmited>();
	}

	/**
	 * Constructor when no password has been submitted. Sets password to "password".
	 *
	 * @param persNumber long
	 * @param name String
     */
	public Student(long persNumber, String name){
		answersSubmited = new ArrayList<AnswerSubmited>();
		this.persNumber = persNumber;
		this.name = name;
		password = "password";
	}

	/**
	 * Constructor when all arguments is supplied.
	 *
	 * @param persNumber long
	 * @param name String
	 * @param password String
     */
	public Student(long persNumber, String name, String password) {
		answersSubmited = new ArrayList<AnswerSubmited>();
		this.persNumber = persNumber;
		this.name = name;
		this.password = password;
	}

	// Getters and setters

	public long getPersNumber() {
		return persNumber;
	}

	public void setPersNumber(long persNumber) {
		this.persNumber = persNumber;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public List<AnswerSubmited> getAnswersSubmited() {
		return answersSubmited;
	}

	public void setAnswersSubmited(List<AnswerSubmited> answersSubmited) {
		this.answersSubmited = answersSubmited;
	}

	// Methods to add and remove answers.

	public void addAnswer(AnswerSubmited currAnswer){
		answersSubmited.add(currAnswer);
	}

	public void removeAnswer(AnswerSubmited currAnswer){
		answersSubmited.remove(currAnswer);
	}

	/**
	 * Checks submitted login credentials against the ones stored in the entity. Returns boolean with result.
	 *
	 * @param submittedPassword String
	 * @return
	 */

	public boolean checkLogin(String submittedPassword){
		boolean loginOk = false;
		if (password.equals(submittedPassword)){
			loginOk = true;
		}
		return loginOk;
	}
}
