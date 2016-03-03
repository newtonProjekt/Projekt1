package server.logic;

import server.datamodel.*;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

/**
 * Class to init db tables
 * 
 * @author Johan Lindstr�m (jolindse@hotmail.com)
 *
 */

public class InitDB {

	public static void main(String[] args) {


		EntityManagerFactory emFactory;
		emFactory = Persistence.createEntityManagerFactory("jpa");
		EntityManager em = emFactory.createEntityManager();
		
		EntityTransaction tx = em.getTransaction();

		Answer answer = new Answer();
		AnswerSubmited answerSubmited = new AnswerSubmited();
		NewtonClass newtonClass = new NewtonClass();
		Question question = new Question();
		SchoolTest schoolTest = new SchoolTest();
		Student student = new Student();

		tx.begin();
		
		em.persist(answer);
		em.persist(answerSubmited);
		em.persist(newtonClass);
		em.persist(question);
		em.persist(schoolTest);
		em.persist(student);
		
		tx.commit();
		
		em.close();
		emFactory.close();
		
		System.out.println("All test objects should be persisted.");
		
	}

}
