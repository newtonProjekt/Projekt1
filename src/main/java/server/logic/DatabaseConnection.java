package server.logic;

import server.datamodel.NewtonClass;
import server.datamodel.Question;
import server.datamodel.SchoolTest;
import server.datamodel.Student;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.List;

/**
 * Contains methods to get or save data from/to database
 *
 * Created by Johan on 2016-03-06.
 */
public class DatabaseConnection {

	private EntityManagerFactory emf;
	private EntityManager em;
	private EntityTransaction etx;

	// DB CONNECTIVITY METHODS

	public DatabaseConnection() {
		emf = Persistence.createEntityManagerFactory("jpa");
		em = emf.createEntityManager();
	}

	/**
	 * Close the database connection.
	 */
	public void closeDb() {
		emf.close();
		em.close();
	}

	// PERSIST ENTITY

	/**
	 * Updates a existing entity.
	 *
	 * @param entity EntityClass
	 * @param <T> Object
	 */
	public <T> void updateEntity(T entity){
		etx = em.getTransaction();
		etx.begin();
		em.merge(entity);
		etx.commit();
	}

	/**
	 * Creates a new managed entity.
	 *
	 * @param entity Entityclass
	 * @param <T> Object
     */
	public <T> void persistEntity(T entity){
		etx = em.getTransaction();
		etx.begin();
		em.persist(entity);
		etx.commit();
	}

	//  GET INFO FROM DATABASE QUERIES

	// From Student entity

	/**
	 * Returns the student with the persNumber.
	 *
	 * @param persNumber String
	 * @return Student
	 */
	public Student getStudent(String persNumber) {
		List result = em.createNamedQuery("getStudent").setParameter("pNumber", Long.parseLong(persNumber)).getResultList();
		if (result.size() > 0) {
			return (Student) result.get(0);
		} else {
			return null;
		}
	}

	/**
	 * Returns all students from specific class
	 *
	 * @param classId int
	 * @return List\<Student\>
     */
	public List<Student> getStudentFromClass(int classId){
		return em.createNamedQuery("getStudentsFromClass").setParameter("classId",classId).getResultList();
	}

	/**
	 * Returns all students in database
	 *
	 * @return List\<Student\>
	 */
	public List<Student> getStudents() {
		return em.createNamedQuery("getAllStudents").getResultList();
	}

	public void deleteStudent(long persNumber){
        em.getTransaction().begin();
		em.createNamedQuery("deleteStudent").setParameter("pNumber",persNumber).executeUpdate();
        em.getTransaction().commit();
	}

	// SchoolTest

	/**
	 * Returns all tests available for student
	 *
	 * @param persNumber String
	 * @return List\<SchoolTest\>
	 */
	public List<SchoolTest> getStudentTests(String persNumber) {
		return em.createNamedQuery("getStudentTests").setParameter("pNumber", Long.parseLong(persNumber)).getResultList();
	}

	/**
	 * Deletes a schooltest.
	 *
	 * @param testId int
     */
	public void deleteSchoolTest(int testId){
        em.getTransaction().begin();
		em.createNamedQuery("deleteSchoolTest").setParameter("testId",testId).executeUpdate();
        em.getTransaction().commit();
	}

	// From SchoolTest entity

	/**
	 * Returns the test with the given id.
	 *
	 * @param testId String
	 * @return SchoolTest
	 */
	public SchoolTest getTest(String testId) {
		List result = em.createNamedQuery("getTest").setParameter("testId", Integer.parseInt(testId)).getResultList();
		return (SchoolTest) result.get(0);
	}

	/**
	 * Get all tests from database.
	 *
	 * @return List\<SchoolTest\>
	 */
	public List<SchoolTest> getAllTests() {
		return em.createNamedQuery("getAllTests").getResultList();
	}

	// From Question entity

	/**
	 * Gets question specified with id.
	 *
	 * @param questionId String
	 * @return Question
	 */
	public Question getQuestion(String questionId) {
		List result = em.createNamedQuery("getQuestion").setParameter("questionId", Integer.parseInt(questionId)).getResultList();
		return (Question) result.get(0);
	}

	// NewtonClass entity

	/**
	 * Gets all classes.
	 *
	 * @return List\<NewtonClass\>
	 */
	public List<NewtonClass> getAllClasses() {
		return em.createNamedQuery("getallclasses").getResultList();
	}

	/**
	 * Deletes a class.
	 *
	 * @param classId int
     */
	public void deleteClass(int classId){
        em.getTransaction().begin();
		em.createNamedQuery("deleteClass").setParameter("classId",classId).executeUpdate();
        em.getTransaction().commit();
	}
}
