package baseProgram.java;
/**
 * @author robertkovbasiuk
 *Date: 07/05/18
 *Purpose of program: To create a University data management system
 */
//basic class for supervisor which are assigned to PostGraduateResearch students
public class Supervisor {
	Name name;

	public Supervisor(Name name) {
		this.name = name;
	}
// returns the name of the supervisor which is used for PostGraduateResearch class
	public Name getName() {
		return name;
	}

}