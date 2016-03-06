package server.logic;

import org.eclipse.persistence.annotations.Properties;
import server.datamodel.*;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

/**
 * Class to init db tables
 * 
 * @author Johan LindstrÃ¶m (jolindse@hotmail.com)
 *
 */

public class InitDB {

	public static void main(String[] args) {

		initManual();

	}

	public static void initAuto(){

		Persistence.generateSchema("jpa",null);
	}

	public static void initManual(){
		EntityManagerFactory emFactory;
		emFactory = Persistence.createEntityManagerFactory("jpa");
		EntityManager em = emFactory.createEntityManager();

		// Create dummy entries
		Answer answer = new Answer("Test",true);
		Answer answer2 = new Answer("Test2",false);
		AnswerSubmited answerSubmited = new AnswerSubmited("Test2",false);
		NewtonClass newtonClass = new NewtonClass("Java1");
		Question question = new Question("Testet test tetstst",2);
		SchoolTest schoolTest = new SchoolTest("Retest");
		Student student = new Student(454545,"Johan Lindström","password");

		schoolTest.addQuestion(question);
		question.addAnswer(answer);
		question.addAnswer(answer2);

		student.addAnswer(answerSubmited);
		newtonClass.addStudent(student);
		newtonClass.addTest(schoolTest);

		EntityTransaction tx = em.getTransaction();



		tx.begin();

		em.persist(question);
		em.persist(answer);
		em.persist(answer2);
		em.persist(answerSubmited);
		em.persist(schoolTest);
		em.persist(student);
		em.persist(newtonClass);

		tx.commit();

		em.close();
		emFactory.close();

		System.out.println("All test objects should be persisted.");
	}

}
