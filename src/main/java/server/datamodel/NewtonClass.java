package server.datamodel;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

/**
 * Entity class that consists of name of class, students in class and the tests the class has access to.
 */
@Entity
@NamedQuery(
		name = "getallclasses",
		query = "select c from NewtonClass c"
)
public class NewtonClass {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer id;
	@NotNull
	private String name;

	public NewtonClass(){
	}

	public NewtonClass(String name){
		this.name = name;
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


}
