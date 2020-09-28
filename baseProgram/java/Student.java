package baseProgram.java;

/**
 * @author robertkovbasiuk
 *Date: 07/05/18
 *Purpose of program: To create a University data management system
 */

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.text.SimpleDateFormat;

public abstract class Student implements UniversityPerson {
	// all fields and components of abstract class student

	private StudentID ID;
	private SmartCard smartCard;
	private Name name;
	private int age;
	private Date dob;
	SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");// format to make adjust the date to print in a certain
																	// style
	int maxModCredits;
	int currentCredits = 0;
	final int MAX_UNDER_GRAD_CREDS = 120;
	final int MAX_POST_GRAD_CREDS = 180;
	final int MAX_POST_GRAD_RES_CREDS = 0;
	float passMark;
	final float MAX_UNDER_GRAD_PASSMARK = 40;
	final float MAX_POST_GRAD_PASSMARK = 50;
	public Map<String, Module> studentsModules = new HashMap<String, Module>();// map of individual student's modules

	// constructor automatically assigns a StudentID object and A new
	// SmartCard which has "this" in the parameters
	public Student(Name name, Date dob) {
		setMaxCreds(this);
		this.name = name;
		this.dob = dob;
		this.ID = IDFactory.getNewInstance();
		this.smartCard = new SmartCard((String) SmartCardNumberFactory.getNewInstance(this), dob, this.getName());

	}

	// method which puts modules in the map only if the current credits is less than
	// max credits
	// if eligible to add a module, it adds module and adds the value of module
	// credits to the current credits
	public boolean put(String code, Module module) {
		if (currentCredits >= maxModCredits || studentsModules.containsKey(code)
				|| (UniversityManagement.allModules.get(code).getCredits()) > (maxModCredits - currentCredits)) {
			return false;
		} else {
			studentsModules.put(code, module);
			currentCredits += module.getCredits();
			return true;
		}

	}

	// returns information about the student
	public String toString() {

		return "\nName: " + name.toString() + "\nStudent Type: " + getType() + "\nAge: " + getAge() + "\nID: "
				+ ID.toString() + "\nDate of Birth: " + this.getFormatDob() + "\nSmart Card Code: "
				+ smartCard.getSmartCardCode() + "\nSmart Card Issue Date: " + smartCard.getCreationDate()
				+ "\nDate of Birth on Smart Card: " + smartCard.getDob() + "\nCurrent module credits: " + currentCredits
				+ "\n\nModules:" + printModuleMap(studentsModules)
				+ "\n========================================================";
	}

	// works out the age by taking away date of birth from current date
	public int getAge() {
		Calendar now = Calendar.getInstance();
		Calendar birthday = Calendar.getInstance();
		birthday.setTime(dob);

		// calculates base number for age without further checks
		age = now.get(Calendar.YEAR) - birthday.get(Calendar.YEAR);
		// further checks (if the birthday month and day of the birthday month has
		// passed
		if (now.get(Calendar.MONTH) < birthday.get(Calendar.MONTH)) {
			age--;
		} else if (now.get(Calendar.MONTH) == birthday.get(Calendar.MONTH)
				&& now.get(Calendar.DATE) == birthday.get(Calendar.DATE)) {
			age--;
		}
		return age;

	}

	// determines what the pass mark is by getting the student type
	public void setPassMark(Student student) {
		String type = student.getType().toLowerCase().trim();
		passMark = type.equalsIgnoreCase("undergraduate") ? MAX_UNDER_GRAD_PASSMARK
				: type.equalsIgnoreCase("postgraduate") ? MAX_POST_GRAD_PASSMARK : null;
	}

	// determines what the max credits is by getting the student type
	private void setMaxCreds(Student student) {
		String type = student.getType().toLowerCase().trim();
		maxModCredits = type.equalsIgnoreCase("undergraduate") ? MAX_UNDER_GRAD_CREDS
				: type.equalsIgnoreCase("postgraduate") ? MAX_POST_GRAD_CREDS : MAX_POST_GRAD_RES_CREDS;
	}

	// returns type of student
	public String getType() {
		return String.valueOf(this.getClass()).split("\\.")[2];

	}

	// returns a String of custom birth day format
	public String getFormatDob() {
		return format.format(dob);
	}

	// returns a Date dob in contrast to the String dob
	public Date getDob() {
		return (Date) dob.clone();
	}

	// Important getters and setters
	public int getCurrentCredits() {
		return currentCredits;
	}

	public Name getName() {
		return name;
	}

	public StudentID getID() {
		return ID;
	}

	public SmartCard getSmartCard() {
		return smartCard;
	}

	public int getMaxModCredits() {
		return maxModCredits;
	}

	// the only place used is in university management amend method
	public void setName(Name name) {
		this.name = name;
	}

	public void setSmartCard(SmartCard smartCard) {
		this.smartCard = smartCard;
	}

	// iterates through a hashmap and adds each module to a string Builder which
	// appends the module
	// then the string full of modules seperated on a new line gets returned and
	// printed out in the toString method
	//If student doesnt have any modules then it should return "No module(s) assigned"
	public String printModuleMap(Map<String, Module> map) {
		StringBuilder sb = new StringBuilder("");
		Iterator<Module> it = map.values().iterator();
		if (studentsModules.size() != 0) {
			for (int i = 0; i < studentsModules.size(); i++) {
				sb.append("		" + String.valueOf(it.next()) + "\n");
			}
			return String.valueOf(sb);
		} else {
			return "No module(s) assigned";
		}
	}
}