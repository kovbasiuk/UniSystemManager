package baseProgram.java;
/**
 * @author robertkovbasiuk
 *Date: 07/05/18
 *Purpose of program: To create a University data management system
 */

import java.util.Date;
import java.util.Random;


public class PostGraduateResearch extends Student {
	final int MIN_AGE = 20;
	private Supervisor supervisor;
	
	//There cant be an object of PostGraduate thats younger MIN_AGE. Age determined by getAge() method from the superclass.
	//supervisor is assigned as soon as a PostGraduateResearch student object is made.
	public PostGraduateResearch(Name name, Date dob) {
		super(name, dob);
		this.setSupervisor(assignSupervisor());
		if(this.getAge()<MIN_AGE) {
			throw new IllegalArgumentException("The minumum valid age for a Post Graduate student is 20!");
		}
		
	}
	
	//uses a random number with a boundry of the supervisor list size to select a random supervisor
	private Supervisor assignSupervisor() {
		Random r = new Random();
		int randomIndex=r.nextInt(UniversityManagement.supervisors.size());
		return UniversityManagement.supervisors.get(randomIndex);
		
	}

	public void setSupervisor(Supervisor supervisor) {
		this.supervisor = supervisor;
	}
	
	//returns the name of the supervisor.
	public String getSupervisorName() {
		return supervisor.getName().toString();
	}

	
}
