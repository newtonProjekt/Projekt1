package newton.java.projekt1;

import server.datamodel.*;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

/**
 * Class to init db tables and fill with test data.
 * 
 * @author Johan Lindström (jolindse@hotmail.com)
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
		Answer answer1 = new Answer("Make",true);
		Answer answer2 = new Answer("Test2",false);
		Answer answer3 = new Answer("Lök",false);
		Answer answer4 = new Answer("Sopa",false);
		Answer answer5 = new Answer("Allt",false);
		Answer answer6 = new Answer("Kossa",false);
		Answer answer7 = new Answer("Handgranat",false);
		Answer answer8 = new Answer("Lussebulle",true);
		Answer answer9 = new Answer("Kaffe",false);
		Answer answer10 = new Answer("Älg",false);
		Answer answer11 = new Answer("Mindre potent",false);
		Answer answer12 = new Answer("Kanonmat",false);
		Answer answer13 = new Answer("Turturduva",false);
		Answer answer14 = new Answer("Plånbok",false);
		Answer answer15 = new Answer("Smögen",false);

		NewtonClass newtonClass = new NewtonClass("Java1");
		NewtonClass newtonClass1 = new NewtonClass(".NET 1");

		Question question = new Question("Testet test tetstst",1,true,false);
		Question question1 = new Question("Grötlök med smör",1,true,false);
		Question question2 = new Question("Alla har kul",2,true,true);
		Question question3 = new Question("Med ögon i rött",4,true,false);
		Question question4 = new Question("Nils Poppe",2,true,true);
		Question question5 = new Question("Tänk på döden",1,true,false);
		Question question6 = new Question("Öbor är galna",2,true,true);
		Question question7 = new Question("Hisingen. Varför Hisingen?",1,true,false);

		SchoolTest schoolTest = new SchoolTest("Retest");
		SchoolTest schoolTest1 = new SchoolTest("Hejsan");
		SchoolTest schoolTest2 = new SchoolTest("Hoppsan");
		SchoolTest schoolTest3 = new SchoolTest("Muppen");

		AnswerSubmited answerSubmited = new AnswerSubmited("Test675",schoolTest.getId(),question.getId());
		AnswerSubmited answerSubmited1 = new AnswerSubmited("Test2452",schoolTest.getId(),question.getId());
		AnswerSubmited answerSubmited2 = new AnswerSubmited("Test2615361",schoolTest1.getId(),question1.getId());
		AnswerSubmited answerSubmited3 = new AnswerSubmited("Test26785",schoolTest1.getId(),question1.getId());
		AnswerSubmited answerSubmited4 = new AnswerSubmited("Test25654",schoolTest2.getId(),question2.getId());
		AnswerSubmited answerSubmited5 = new AnswerSubmited("Test252654",schoolTest2.getId(),question2.getId());

		Student student = new Student(454545,"Johan","Lindström","password");
		Student student1 = new Student(545454, "Karl","Fagher","lösen");
		Student student2 = new Student(212121,"Lisa","Nilsson","passord");


		schoolTest.addQuestion(question);
		schoolTest.addQuestion(question1);
		question.addAnswer(answer);
		question.addAnswer(answer1);
		question1.addAnswer(answer2);
		question1.addAnswer(answer3);

		schoolTest1.addQuestion(question2);
		schoolTest1.addQuestion(question3);
		question2.addAnswer(answer4);
		question2.addAnswer(answer5);
		question3.addAnswer(answer6);
		question3.addAnswer(answer7);

		schoolTest2.addQuestion(question4);
		schoolTest2.addQuestion(question5);
		question4.addAnswer(answer8);
		question4.addAnswer(answer9);
		question5.addAnswer(answer10);
		question5.addAnswer(answer11);

		schoolTest3.addQuestion(question6);
		schoolTest3.addQuestion(question7);
		question6.addAnswer(answer12);
		question6.addAnswer(answer13);
		question7.addAnswer(answer14);
		question7.addAnswer(answer15);


		student.addAnswer(answerSubmited);
		student.addAnswer(answerSubmited1);

		student.addTest(schoolTest);
		student.addTest(schoolTest2);
		student.addTest(schoolTest3);


		student1.addAnswer(answerSubmited2);
		student1.addAnswer(answerSubmited3);

		student1.addTest(schoolTest1);
		student1.addTest(schoolTest2);


		student2.addAnswer(answerSubmited4);
		student2.addAnswer(answerSubmited5);

		student2.addTest(schoolTest);
		student2.addTest(schoolTest3);

		newtonClass.addStudent(student);
		newtonClass.addStudent(student1);

		newtonClass1.addStudent(student2);

		newtonClass.addTest(schoolTest);
		newtonClass1.addTest(schoolTest2);

		EntityTransaction tx = em.getTransaction();



		tx.begin();

		/*
		em.persist(question);
		em.persist(answer);
		em.persist(answer2);
		em.persist(answerSubmited);
		em.persist(schoolTest);
		em.persist(student);
		em.persist(newtonClass);
		*/

		em.persist(student);
		em.persist(student1);
		em.persist(student2);

		em.persist(newtonClass);
		em.persist(newtonClass1);

		tx.commit();

		em.close();
		emFactory.close();

		System.out.println("All test objects should be persisted.");
	}

}
