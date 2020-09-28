package baseProgram.java;
/**
 * @author robertkovbasiuk
 *Date: 07/05/18
 *Purpose of program: To create a University data management system
 */

import java.util.Date;


public class PostGraduate extends Student {
	//unique constants
	final int MIN_AGE = 20;
	final int MAX_MOD_CREDITS=180;
	
	//There cant be an object of PostGraduate thats younger MIN_AGE. Age determined by getAge() method from the superclass
	public PostGraduate(Name name, Date dob) {
		super(name, dob);
		if (this.getAge() < MIN_AGE) {
			throw new IllegalArgumentException("The minumum valid age for a Post Graduate student is 20!");
		}
	}
}
