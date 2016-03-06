package server.logic;

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

	public DatabaseConnection(){
		emf = Persistence.createEntityManagerFactory("jpa");
		em = emf.createEntityManager();
	}

	public void closeDb(){
		emf.close();
		em.close();
	}

	// QUERIES

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
	 * Returns all students
	 */

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
}
