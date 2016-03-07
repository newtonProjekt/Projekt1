package server.logic;

import server.datamodel.Question;
import server.datamodel.SchoolTest;
import server.datamodel.Student;

import javax.persistence.*;
import java.util.List;

/**
 * Contains methods to get data from database
 *
 * Created by Johan on 2016-03-06.
 */
public class DatabaseConnection {

	EntityManagerFactory emf;
	EntityManager em;
	EntityTransaction etx;

	// DB CONNECTIVITY METHODS

	public DatabaseConnection(){
		emf = Persistence.createEntityManagerFactory("jpa");
		em = emf.createEntityManager();
	}

	public void closeDb(){
		emf.close();
		em.close();
	}

	//  GET INFO FROM DATABASE QUERIES

	// From Student entity

	/**
	 * Returns the student with the persNumber.
	 *
	 * @param persNumber String
	 * @return Student
	 */
	public Student getStudent(String persNumber){
		List result = em.createNamedQuery("getStudent").setParameter("pNumber",Long.parseLong(persNumber)).getResultList();
		if (result.size() > 0) {
			return (Student) result.get(0);
		} else {
			return null;
		}
	}

	/**
	 * Returns all students in database
	 *
	 * @return List\<Student\>
	 */
	public List<Student> getStudents(){
		return em.createNamedQuery("getAllStudents").getResultList();
	}

	/**
	 * Returns all tests available for student
	 *
	 * @param persNumber String
	 * @return List\<SchoolTest\>
	 */
	public List<SchoolTest> getStudentTests(String persNumber){
		return em.createNamedQuery("getStudentTests").setParameter("pNumber", Long.parseLong(persNumber)).getResultList();
	}

	// From SchoolTest entity

	/**
	 * Returns the test with the given id.
	 *
	 * @param testId String
	 * @return SchoolTest
	 */
	public SchoolTest getTest(String testId){
		List result = em.createNamedQuery("getTest").setParameter("testId",Integer.parseInt(testId)).getResultList();
		return (SchoolTest)result.get(0);
	}

	/**
	 * Get all tests from database.
	 *
	 * @return List\<SchoolTest\>
	 */
	public List<SchoolTest> getAllTests(){
		return em.createNamedQuery("getAllTests").getResultList();
	}

	// From Question entity

	/**
	 * Gets question specified with id.
	 *
	 * @param questionId String
	 * @return Question
	 */
	public Question getQuestion(String questionId){
		List result = em.createNamedQuery("getQuestion").setParameter("questionId",Integer.parseInt(questionId)).getResultList();
		return (Question) result.get(0);
	}

}
