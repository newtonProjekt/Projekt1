package server.datamodel;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Student {

	@Id
	@GeneratedValue
	private int id;
	private String name, login, password;
	private List<AnswerSubmited> answersSubmited;
	
	public Student(){
		
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
