package server.datamodel;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class NewtonClass {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
	private String name;
	@OneToMany
	private List<Student> students;
	@OneToMany
	private List<SchoolTest> tests;
	
	public NewtonClass(){
		students = new ArrayList<Student>();
		tests = new ArrayList<SchoolTest>();
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

	public void addTest(SchoolTest currTest){
		tests.add(currTest);
	}

	public void removeTest(SchoolTest currTest){
		tests.remove(currTest);
	}

	public void addStudent(Student currStudent){
		students.add(currStudent);
	}

	public void removeStudent(Student currStudent){
		students.remove(currStudent);
	}
	
}
