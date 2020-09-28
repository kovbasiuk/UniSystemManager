package baseProgram.java;
/**
 * @author robertkovbasiuk
 *Date: 07/05/18
 *Purpose of program: To create a University data management system
 */

public class Name {

	//2 components that make the name;
	private final String firstName;
	private final String lastName;

	public Name(String firstName, String lastName) {
		this.firstName = firstName;
		this.lastName = lastName;
	}

	//gets the 2 components using their getters
	public String getLastName() {
		return lastName;
	}

	public String getFirstName() {
		return firstName;
	}
	
	//equals method tailored for this class
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (!(obj instanceof Name)) {
			return false;
		}
		final Name name = (Name) obj;
		return firstName.equals(name.firstName) && lastName.equals(name.lastName);
	}

	public int hashCode() {
		int hc = 17;

		hc = 37 * hc + firstName.hashCode();
		return 37 * hc + lastName.hashCode();
	}
	//toString method tailored for this class, returns the 2 components separated by a space
	public String toString() {
		return firstName + " " + lastName;
	}
//returns a new name based on a String input
	public static Name valueOf(String name) {
		final String[] parts = name.split(" ");
		return new Name(parts[0], parts[1]);
	}
}
