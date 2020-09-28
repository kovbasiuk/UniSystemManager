package baseProgram.java;
/**
 * @author robertkovbasiuk
 *Date: 07/05/18
 *Purpose of program: To create a University data management system
 */

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Random;

//implements factory interface just like IDFactory
public class SmartCardNumberFactory implements Factory {

	
	private static Student student;
	Date year = new Date();
	private static String temp;
	public static List<String> SmartCardStringList = new ArrayList<String>();

	//has student in the parameter in order to access the name for the initials
	public static Object getNewInstance(Student student) {
		SmartCardNumberFactory.student = student;
		registerObj();
		return temp;
	}

	//puts  the SmartCard together (INITIALS+YEAR OF CREATION+RANDOM 2 DIGIT FORMATTED NUMBER)
	private static Object buildObj() {
		Calendar cal = Calendar.getInstance();
		int thisYear = cal.get(Calendar.YEAR);
		Random randomSerial = new Random();
		int serial;
		serial = (randomSerial.nextInt(99));
		String formattedSerial;
		formattedSerial = String.format("%02d", serial);

		return "" + student.getName().getFirstName().toUpperCase().charAt(0) + student.getName().getLastName().toUpperCase().charAt(0) + "-"
				+ thisYear + "-" + formattedSerial;

	}
	
	//Recursive method checks if the temporary String code already exists, if it does, the method calls itself again which calls
	//buildObj()which will make a new serial number
	private static void registerObj() {

		temp = String.valueOf(buildObj());
		if (temp != null) {
			if (!SmartCardStringList.contains(temp)) {
				SmartCardStringList.add(temp);
			} else {
				registerObj();
			}
		}
	}
}
