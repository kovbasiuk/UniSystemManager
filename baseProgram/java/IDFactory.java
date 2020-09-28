package baseProgram.java;

/**
 * @author robertkovbasiuk
 *Date: 07/05/18
 *Purpose of program: To create a University data management system
 */

import java.util.ArrayList;
import java.util.List;

public class IDFactory implements Factory {

	// chars to place at the beginning of the ID
	static char[] alphabet = "abcdefghijklmnopqrstuvwxyz".toCharArray();

	// num is a counter and letter is the index of the char array
	// temp is used to check if it exists in registerObj() however, it's almost
	// impossible that 2 of the same ID's will be made due to the counter system

	static int num = 0;
	static int letter = 0;
	private static StudentID temp;
	public static List<StudentID> IDlist = new ArrayList<StudentID>();

	// static factory new instance method
	public static StudentID getNewInstance() {
		registerObj();
		return temp;
	}

	// builds account ID number using string builder
	public static String buildObj() {

		String result = (alphabet[letter] + String.format("%04d", num));
		monitor();
		return result;

	}

	// controls  num before the letter index has to increment and num reset
	private static void monitor() {
		
		final int MAX_NUM = 10000;

		if (num == MAX_NUM) {
			letter += 1;
			num = 0;
		} else {
			num += 1;
		}
		if (num == MAX_NUM && letter == 25) {
			throw new IllegalArgumentException("Every ID is assigned!");
		}
	}

	//makes sure ID thats made isnt already registered
	public static void registerObj() {
		temp = new StudentID(buildObj());
		if (!IDlist.contains(temp)) {
			IDlist.add(temp);
		} else {
			throw new IllegalArgumentException("This ID is not available");
		}

	}
}