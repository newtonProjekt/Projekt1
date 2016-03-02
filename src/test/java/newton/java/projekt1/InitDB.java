package newton.java.projekt1;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import server.datamodel.Answer;
import server.datamodel.AnswerSubmited;
import server.datamodel.NewtonClass;
import server.datamodel.Question;
import server.datamodel.SchoolTest;
import server.datamodel.Student;

/**
 * Class to init db tables
 * 
 * @author Johan Lindström (jolindse@hotmail.com)
 *
 */

public class InitDB {

	public static void main(String[] args) {
		
		Answer answer = new Answer();
		AnswerSubmited answerSubmited = new AnswerSubmited();
		NewtonClass newtonClass = new NewtonClass();
		Question question = new Question();
		SchoolTest schoolTest = new SchoolTest();
		Student student = new Student();
		
		EntityManagerFactory emFactory = Persistence.createEntityManagerFactory("Projekt1");
		EntityManager em = emFactory.createEntityManager();
		
		EntityTransaction tx = em.getTransaction();
		
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
