package baseProgram.java;
/**
 * @author robertkovbasiuk
 *Date: 07/05/18
 *Purpose of program: To create a University data management system
 */

import java.util.Date;

public class UnderGraduate extends Student {
	
	//UnderGraduate must be at least 17 years old and this value cant change
	final int MIN_AGE = 17;

	//checks the getAge() method from the superclass to see if this student is a valid age 
	public UnderGraduate(Name name, Date dob) {
		super(name, dob);
		if (this.getAge() < MIN_AGE) {
			throw new IllegalArgumentException("The minumum valid age for a UnderGraduate student is 17!");
		}

	}
	

}
