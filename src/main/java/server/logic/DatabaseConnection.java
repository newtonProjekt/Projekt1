package server.logic;

import server.datamodel.Student;

import javax.persistence.*;

/**
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

	private void closeDb(){
		emf.close();
		em.close();
	}

	// QUERIES

	public Student getStudent(String persNumber){

	}
}
