package baseProgram.java;
/**
 * @author robertkovbasiuk
 *Date: 07/05/18
 *Purpose of program: To create a University data management system
 */

import java.util.Date;

public interface UniversityPerson {

	public String toString();
	public int getAge();
	public Date getDob();
	public Name getName();
	public StudentID getID();
	public String getType();
	public String getFormatDob();
}
