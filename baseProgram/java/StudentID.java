package baseProgram.java;
/**
 * @author robertkovbasiuk
 *Date: 07/05/18
 *Purpose of program: To create a University data management system
 */



public class StudentID {

	private final String IDNum;
	private final char letter;
	//once a new ID instance is made it is automatically added to the hashmap
	public StudentID(String ID) {
		this.IDNum = ID.substring(1, ID.length());
		this.letter = ID.charAt(0);
		
		
	}

	public String getIDNum() {
		return IDNum;
	}

	public char getletter() {
		return letter;

	}
	
	@Override
	public String toString() {
		return String.valueOf(letter)+IDNum;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((IDNum == null) ? 0 : IDNum.hashCode());
		result = prime * result + letter;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		StudentID other = (StudentID) obj;
		if (IDNum == null) {
			if (other.IDNum != null)
				return false;
		} else if (!IDNum.equals(other.IDNum))
			return false;
		if (letter != other.letter)
			return false;
		return true;
	}

	
	

	
}