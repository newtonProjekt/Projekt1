package server.datamodel;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
public class NewtonClass {

	@Id
	@GeneratedValue
	private int id;
	private String name;
	private List<Student> students;
	private List<SchoolTest> tests;
	
	public NewtonClass(){
		
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

	public List<Student> getStudents() {
		return students;
	}

	public void setStudents(List<Student> students) {
		this.students = students;
	}

	public List<SchoolTest> getTests() {
		return tests;
	}

	public void setTests(List<SchoolTest> tests) {
		this.tests = tests;
	}
	
	
	
}
