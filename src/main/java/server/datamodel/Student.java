package server.datamodel;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Student {

	@Id
	private int persNumber;
	private String name, login, password;
	@OneToMany
	private List<AnswerSubmited> answersSubmited;
	
	public Student(){
		answersSubmited = new ArrayList<AnswerSubmited>();
	}
	
	public Student(int persNumber, String name, String login, String password) {
		answersSubmited = new ArrayList<AnswerSubmited>();
		this.persNumber = persNumber;
		this.name = name;
		this.login = login;
		this.password = password;
	}

	public int getPersNumber() {
		return persNumber;
	}

	public void setPersNumber(int persNumber) {
		this.persNumber = persNumber;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
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
	
	
}
