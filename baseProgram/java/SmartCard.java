package baseProgram.java;
/**
 * @author robertkovbasiuk
 *Date: 07/05/18
 *Purpose of program: To create a University data management system
 */


import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;


public class SmartCard {
	//Necessary variables and fields 
	Calendar cal = Calendar.getInstance();
	private String smartCardCode;
	private final Date dob;
	private final Date creationDate;
	SimpleDateFormat format = new SimpleDateFormat(
            "dd/MM/yyyy");
	//list of all smartcards in static list
	public static List<SmartCard> SmartCardList = new ArrayList<SmartCard>();
	
	//once a SmartCard object is created its automatically added to the list and if any String s, Date dateOfBirth, Name nameOfStudent  is empty,
	//an exceptopn is thrown
	
	public SmartCard(String s, Date dateOfBirth, Name nameOfStudent ) {
		this.dob=dateOfBirth;
		smartCardCode = s;
		creationDate = cal.getTime();
		SmartCardList.add(this);
		if(s==null||dateOfBirth==null||nameOfStudent==null) {
			throw new IllegalArgumentException("Failed to create a valid smartcard");
		}
		
	}
	
	//returns a formatted String version of dob
	public String getDob() {
		return format.format(dob);
	}

	public String getCreationDate() {
		return format.format(creationDate);
	}
	//returns a raw Date version of dob
	public String getSmartCardCode() {
		return smartCardCode;
	}

	
	
	
}